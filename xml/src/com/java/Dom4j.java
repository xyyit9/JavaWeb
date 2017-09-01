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
	// ��ȡxml�ĵ��ڶ����������
	@Test
	public void read() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));

		Element root = document.getRootElement();
		Element book = (Element) root.elements("��").get(1);
		String value = book.element("����").getText();
		String valuename = book.element("����").attributeValue("name");
		System.out.println(value + valuename);
	}

	// �ڵ�һ���������һ���µ��ۼ�
	// �������л������룬sun io�������⣬����Ҫ�������һ��format
	@Test
	public void add() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));
		Element book = document.getRootElement().element("��");
		book.addElement("�ۼ�").setText("200Ԫ");
		// Ĭ��FileWriter����GB2312�������xml���õ���UTF-8���.����Ҫ����һ����ʽ�����
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		// ������FileWriter,һ��Ҫ���ֽ�������Ϊֻ���ֽ�������ָ�������ʽ
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/1.xml"), format);
		// XMLWriter writer = new XMLWriter(new FileWriter("src/1.xml"));
		writer.write(document);// documentĬ����UTF-8
		writer.close();
	}

	// �ڵ�һ��ָ��λ�������һ���µ��ۼ�
	@Test
	public void add2() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));

		Element book = document.getRootElement().element("��");
		List list = book.elements();
		Element price = DocumentHelper.createElement("�ۼ�");
		price.setText("309Ԫ");
		list.add(2, price);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/1.xml"), format);
		writer.write(document);
		writer.close();
	}

	@Test
	// ɾ��������ӵ��ۼ۽ڵ�
	public void delete() throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/1.xml"));

		Element price = document.getRootElement().element("��").element("�ۼ�");
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
		
		Element book=(Element)document.getRootElement().elements("��").get(1);
		book.element("����").setText("��Ů");
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileOutputStream("src/1.xml"), format);
		writer.write(document);
		writer.close();

	}
}
