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

import java.io.FileNotFoundException;
import java.util.Map;


public interface ProblemData extends Map<String, String> {
	void put(ProblemDataKey key, String value);
	void put(ProblemDataKey key, Throwable t);
	void put(String key, Throwable t);
	String get(ProblemDataKey key);
	void putFile(String filename) throws FileNotFoundException;
}
