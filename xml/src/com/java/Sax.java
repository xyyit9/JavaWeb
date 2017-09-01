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

//SaxTag和Saxall是练手的代码，正式运行是本代码，运用的是面向对象的思想
public class Sax {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// 1.创建解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.得到解析器
		SAXParser sp = factory.newSAXParser();
		// 3.得到读取器
		XMLReader reader = sp.getXMLReader();
		// 4.设置内容处理器
		BeanListHander handle=new BeanListHander();
		reader.setContentHandler(handle);
		// 5.读取xml文档内容
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
		if("书".equals(currentTag)){
			book=new Book();
		}
	}
	public void characters(char[] ch, int start, int length) throws SAXException {
		if("书名".equals(currentTag)){
			String name=new String(ch,start,length);
			book.setName(name);
		}
		if("作者".equals(currentTag)){
			String author=new String(ch,start,length);
			book.setAuthor(author);
		}
		if("售价".equals(currentTag)){
			String price=new String(ch,start,length);
			book.setPrice(price);
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//currentTag必须置空，否则会出现空指针异常
		currentTag=null;
		if(qName.equals("书")){
			list.add(book);
			book=null;
		}
	}
	public List<Book> getBooks(){
		return list;
	}
	
	
}


