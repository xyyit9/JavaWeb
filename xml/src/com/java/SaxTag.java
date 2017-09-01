package com.java;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SaxTag {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// 1.创建解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.得到解析器
		SAXParser sp = factory.newSAXParser();
		// 3.得到读取器
		XMLReader reader = sp.getXMLReader();
		// 4.设置内容处理器
		reader.setContentHandler(new TagValueHandler());
		// 5.读取xml文档内容
		reader.parse("src/1.xml");
	}
}

//得到特定标签的内容
class TagValueHandler extends DefaultHandler{
	
	private String currentTag; //记住当前解析到的是什么标签
	private int needNumber=2;//记住想获取第几个作者标签的值
	private int currentNumber;//当前解析到的是第几个
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if("作者".equals(currentTag)&&currentNumber==needNumber){
			System.out.println(new String(arg0,arg1,arg2));
		}
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2) throws SAXException {
		currentTag=null;
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
		currentTag =arg2;
		if(currentTag.equals("作者")){
			currentNumber++;
		}
	}
	
}
