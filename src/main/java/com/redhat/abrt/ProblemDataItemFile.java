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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProblemDataItemFile implements ProblemDataItem {
	private File file = null;
	private BufferedReader reader = null;
	/* cache for repeated request for the file content */
	private String fileContents = null;

	ProblemDataItemFile(String fullPath) {
		System.err.println("Opening file: " + fullPath);
		file = new File(fullPath);
        try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			/* This will be attached to the file contents and saved in the same file in problem dir
			 * so we can investigate it
			 * On the other hand it can be confusing for the maintainers, as it doesn't come from their code,
			 * so it should be somehow marked so it's obvious it's from abrt
			 */
			fileContents = String.format("Couldn't read file '%s' because '%s'", fullPath, e.getMessage());
		}
	}

	private String readFileToString(BufferedReader reader) {
		System.out.println("Reading file...");
        StringBuffer fileData = new StringBuffer();
        char[] buf = new char[1024];
        int numRead=0;
        try {
			while((numRead=reader.read(buf)) != -1){
			    fileData.append(String.valueOf(buf, 0, numRead));
			}
		} catch (IOException e) {
			/* This will be attached to the file contents ... ^ see the first comment */
			fileData.append(String.format("Reading the contents of file '%s' failed because '%s'", file.getPath(), e.getMessage()));
		}

        try {
			reader.close();
		} catch (IOException e) {
			/* Can't close the file? What can we do about it?? */
		}
        System.out.println("read: " + fileData);
        return fileData.toString();
    }

	public String getString() {
		if (fileContents == null)
			fileContents = readFileToString(reader);

		return fileContents;
	}

	public String getString(boolean populate) {
		if (populate == true)
			return getString();

		return file.getPath();
	}

}
