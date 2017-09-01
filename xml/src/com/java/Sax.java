package com.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

//SaxTag��Saxall�����ֵĴ��룬��ʽ�����Ǳ����룬���õ�����������˼��
public class Sax {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// 1.������������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.�õ�������
		SAXParser sp = factory.newSAXParser();
		// 3.�õ���ȡ��
		XMLReader reader = sp.getXMLReader();
		// 4.�������ݴ�����
		BeanListHander handle=new BeanListHander();
		reader.setContentHandler(handle);
		// 5.��ȡxml�ĵ�����
		reader.parse("src/1.xml");
		
		List<Book> list=handle.getBooks();
		System.out.println(list);
	}
}

class BeanListHander extends DefaultHandler{

	private List<Book> list=new ArrayList<Book>();
	private String currentTag;
	private Book book;
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentTag=qName;
		if("��".equals(currentTag)){
			book=new Book();
		}
	}
	public void characters(char[] ch, int start, int length) throws SAXException {
		if("����".equals(currentTag)){
			String name=new String(ch,start,length);
			book.setName(name);
		}
		if("����".equals(currentTag)){
			String author=new String(ch,start,length);
			book.setAuthor(author);
		}
		if("�ۼ�".equals(currentTag)){
			String price=new String(ch,start,length);
			book.setPrice(price);
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//currentTag�����ÿգ��������ֿ�ָ���쳣
		currentTag=null;
		if(qName.equals("��")){
			list.add(book);
			book=null;
		}
	}
	public List<Book> getBooks(){
		return list;
	}
	
	
}


