package com.redhat.abrt;

public class ProblemDataItemString implements ProblemDataItem {
	private String value = null;

	ProblemDataItemString(String value) {
		this.value = value;
	}

	@Override
	public String getString() {
		return value;
	}

	@Override
	public String getString(boolean populate) {
		return getString();
	}

}
