package com.redhat.abrt;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProblemDataAbrt implements ProblemData {
	private Map<String, ProblemDataItem> problemData = new HashMap<String, ProblemDataItem>();

	private void add(String key, ProblemDataItem value) {
		problemData.put(key, value);
	}

	public void add(String key, String value) {
		problemData.put(key, new ProblemDataItemString(value));
	}

	public void addFile(String filename) throws FileNotFoundException {
		add(filename, new ProblemDataItemFile(filename));
	}

	public void remove(String key) {
		problemData.remove(key);
	}

	public void clear() {
		problemData.clear();
	}

	public boolean containsKey(Object key) {
		return problemData.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return problemData.containsValue(value);
	}

	public Set<java.util.Map.Entry<String, String>> entrySet() {
		Map<String, String> retval = new HashMap<String, String>();
		for (Entry<String, ProblemDataItem> item: problemData.entrySet()) {
			retval.put(item.getKey(), item.getValue().getString());
		}

		return retval.entrySet();
	}

	public String get(Object key) {
		return problemData.get(key).getString();
	}

	public boolean isEmpty() {
		return problemData.isEmpty();
	}

	public Set<String> keySet() {
		return problemData.keySet();
	}

	public String put(String key, String value) {
		problemData.put(key, new ProblemDataItemString(value));

		return value;
	}

	public void putAll(Map<? extends String, ? extends String> origMap) {
		for (Entry<? extends String, ? extends String> item: origMap.entrySet()) {
			problemData.put(item.getKey(), new ProblemDataItemString(item.getValue()));
		}
	}

	public String remove(Object key) {
		return problemData.remove(key).getString(false);
	}

	public int size() {
		return problemData.size();
	}

	public Collection<String> values() {
		Collection<String> retval = new ArrayList<String>();
		for(ProblemDataItem item: problemData.values()) {
			retval.add(item.getString());
		}

		return retval;
	}

}
