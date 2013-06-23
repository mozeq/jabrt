package com.redhat.abrt;

import java.io.FileNotFoundException;
import java.util.Map;


public interface ProblemData extends Map<String, String> {
	abstract void add(String key, String value);
	abstract void addFile(String filename) throws FileNotFoundException;
	abstract void remove(String key);
}
