/*
    Copyright (C) ABRT Team
    Copyright (C) RedHat inc.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/

package com.redhat.abrt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.util.Map.Entry;

import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel;

public class ProblemDataServer {

	//TODO: read this from a config file?
	private UnixSocketAddress address = null;
	UnixSocketChannel channel = null;
	OutputStreamWriter writer = null;
	InputStreamReader reader = null;
	private final File socketFile = new File("/var/run/abrt/abrt.socket");

	public ProblemDataServer() {
	}


	private void connect() {
		try {
			address = new UnixSocketAddress(socketFile);
		} catch (Exception e) {
			System.out.println("Can't open socket: " + e.getMessage());
		}
		try {
			channel = UnixSocketChannel.open(address);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* unbuffered writer */
		writer = new OutputStreamWriter(Channels.newOutputStream(channel));
	}

	private void disconnect() {
		try {
			writer.close();
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(ProblemData problemData) throws IOException {
		connect();
		writer.write("PUT / HTTP/1.1\r\n\r\n");
		for (Entry<String, String> item: problemData.entrySet()) {
			String key = null;

			/* use just the last part of the path as key */
			int lastSeparator = item.getKey().lastIndexOf("/");
			key = item.getKey().toUpperCase().substring(lastSeparator+1);
			writer.write(String.format("%s=%s\0", key, item.getValue()));
		}
		writer.flush();
		channel.shutdownOutput();

		reader = new InputStreamReader(Channels.newInputStream(channel));
		CharBuffer ret = CharBuffer.allocate(1024);
		reader.read(ret);
		ret.flip();
		disconnect();
	}

	public void close() {
		try {
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test() {
		System.out.println("Sending testing data to abrt");
		ProblemData pd = new ProblemDataAbrt();
		pd.add("BACKTRACE", "backtrace content");
		pd.add("TYPE", "java");
		pd.add("ANALYZER", "java");
		pd.add("PID", "12345");
		pd.add("EXECUTABLE", "/bin/eclipse");
		pd.add("REASON", "tesing java problem data");
		String filename = "/etc/hosts";
		try {
			pd.addFile(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't add file: " + filename);
		}

		try {
			send(pd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't send data to ABRT: " + e.getMessage());
		}
	}


	/* just for testing */
	public static void main(String[] args) {
		ProblemDataServer ps = new ProblemDataServer();
		ps.test();
	}

}
