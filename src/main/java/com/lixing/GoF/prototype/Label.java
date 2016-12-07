package com.lixing.GoF.prototype;

import java.io.Serializable;

public class Label implements Serializable {
	
	private static final long serialVersionUID = 54465156L;
	
	private String labelName;
	
	public Label(String labelName) {
		this.labelName = labelName;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@Override
	public String toString() {
		return "Label [labelName=" + labelName + "]";
	}
	
}
