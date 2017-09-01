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
		// 1.������������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.�õ�������
		SAXParser sp = factory.newSAXParser();
		// 3.�õ���ȡ��
		XMLReader reader = sp.getXMLReader();
		// 4.�������ݴ�����
		reader.setContentHandler(new TagValueHandler());
		// 5.��ȡxml�ĵ�����
		reader.parse("src/1.xml");
	}
}

//�õ��ض���ǩ������
class TagValueHandler extends DefaultHandler{
	
	private String currentTag; //��ס��ǰ����������ʲô��ǩ
	private int needNumber=2;//��ס���ȡ�ڼ������߱�ǩ��ֵ
	private int currentNumber;//��ǰ���������ǵڼ���
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if("����".equals(currentTag)&&currentNumber==needNumber){
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
		if(currentTag.equals("����")){
			currentNumber++;
		}
	}
	
}
