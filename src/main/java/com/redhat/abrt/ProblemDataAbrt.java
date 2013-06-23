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

	@Override
	public void add(String key, String value) {
		problemData.put(key, new ProblemDataItemString(value));
	}

	@Override
	public void addFile(String filename) throws FileNotFoundException {
		add(filename, new ProblemDataItemFile(filename));
	}

	@Override
	public void remove(String key) {
		problemData.remove(key);
	}

	@Override
	public void clear() {
		problemData.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return problemData.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return problemData.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<String, String>> entrySet() {
		Map<String, String> retval = new HashMap<String, String>();
		for (Entry<String, ProblemDataItem> item: problemData.entrySet()) {
			System.out.println("processing: " + item.getKey());
			retval.put(item.getKey(), item.getValue().getString());
		}

		return retval.entrySet();
	}

	@Override
	public String get(Object key) {
		return problemData.get(key).getString();
	}

	@Override
	public boolean isEmpty() {
		return problemData.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return problemData.keySet();
	}

	@Override
	public String put(String key, String value) {
		problemData.put(key, new ProblemDataItemString(value));

		return value;
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> origMap) {
		for (Entry<? extends String, ? extends String> item: origMap.entrySet()) {
			problemData.put(item.getKey(), new ProblemDataItemString(item.getValue()));
		}
	}

	@Override
	public String remove(Object key) {
		return problemData.remove(key).getString(false);
	}

	@Override
	public int size() {
		return problemData.size();
	}

	@Override
	public Collection<String> values() {
		Collection<String> retval = new ArrayList<String>();
		for(ProblemDataItem item: problemData.values()) {
			retval.add(item.getString());
		}

		return retval;
	}

}
