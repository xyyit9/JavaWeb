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

//Ӧ��Xpath��ȡxml�ĵ��Ľ̳�
public class Xpath {
public static void main(String[] args) throws DocumentException {
	SAXReader reader = new SAXReader();
	Document document = reader.read(new File("src/1.xml"));

	String value=document.selectSingleNode("//����").getText();
	System.out.println(value);
}
}
