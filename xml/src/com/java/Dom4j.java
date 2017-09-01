package com.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4j {
	// 读取xml文档第二本书的书名
	@Test
	public void read() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));

		Element root = document.getRootElement();
		Element book = (Element) root.elements("书").get(1);
		String value = book.element("书名").getText();
		String valuename = book.element("书名").attributeValue("name");
		System.out.println(value + valuename);
	}

	// 在第一本数上添加一个新的售价
	// 这样运行会有乱码，sun io流有问题，所以要在里面加一个format
	@Test
	public void add() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));
		Element book = document.getRootElement().element("书");
		book.addElement("售价").setText("200元");
		// 默认FileWriter是用GB2312码表，但是xml中用的是UTF-8码表.所以要设置一个格式化码表
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		// 不能是FileWriter,一定要是字节流，因为只有字节流才能指定编码格式
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/1.xml"), format);
		// XMLWriter writer = new XMLWriter(new FileWriter("src/1.xml"));
		writer.write(document);// document默认是UTF-8
		writer.close();
	}

	// 在第一本指定位置上添加一个新的售价
	@Test
	public void add2() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));

		Element book = document.getRootElement().element("书");
		List list = book.elements();
		Element price = DocumentHelper.createElement("售价");
		price.setText("309元");
		list.add(2, price);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/1.xml"), format);
		writer.write(document);
		writer.close();
	}

	@Test
	// 删除上面添加的售价节点
	public void delete() throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));

		Element price = document.getRootElement().element("书").element("售价");
		price.getParent().remove(price);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/1.xml"), format);
		writer.write(document);
		writer.close();
	}

	@Test
	public void update() throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));
		
		Element book=(Element)document.getRootElement().elements("书").get(1);
		book.element("作者").setText("仙女");
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/1.xml"), format);
		writer.write(document);
		writer.close();

	}
}
