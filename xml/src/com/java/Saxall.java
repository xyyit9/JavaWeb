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

public class Saxall {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// 1.创建解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.得到解析器
		SAXParser sp = factory.newSAXParser();
		// 3.得到读取器
		XMLReader reader = sp.getXMLReader();
		// 4.设置内容处理器
		reader.setContentHandler(new ListHandler());
		// 5.读取xml文档内容
		reader.parse("src/1.xml");
	}
}

// 得到xml文档所有内容
class ListHandler implements ContentHandler {

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println(new String(ch, start, length));

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("<" + qName + ">");
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		System.out.println("<" + qName + ">");
		// 有些标签是没有属性值的，此时atts是null,故还需要限定条件atts!=null
		for (int i = 0; atts != null && i < atts.getLength(); i++) {
			String attName = atts.getQName(i);
			String attValue = atts.getValue(i);
			System.out.println(attName + "=" + attValue);
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

}
