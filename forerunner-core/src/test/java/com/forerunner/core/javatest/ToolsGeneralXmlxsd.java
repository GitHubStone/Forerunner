package com.forerunner.core.javatest;

import com.forerunner.utils.JAXBUtils;

public class ToolsGeneralXmlxsd {

	public static void main(String[] args) {
		JAXBUtils.generateSchema(ToolsGeneralXmlxsd.class, System.out);
	}
}