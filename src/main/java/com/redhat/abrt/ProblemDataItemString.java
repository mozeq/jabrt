package com.redhat.abrt;

public class ProblemDataItemString implements ProblemDataItem {
	private String value = null;

	ProblemDataItemString(String value) {
		this.value = value;
	}

	public String getString() {
		return value;
	}

	public String getString(boolean populate) {
		return getString();
	}

}
