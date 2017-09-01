package com.java;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

//应用Xpath提取xml文档的教程
public class Xpath {
public static void main(String[] args) throws DocumentException {
	SAXReader reader = new SAXReader();
	Document document = reader.read(new File("src/1.xml"));

	String value=document.selectSingleNode("//作者").getText();
	System.out.println(value);
}
}
