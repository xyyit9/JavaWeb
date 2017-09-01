package com.java;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//ʹ��dom��ʽ��xml�ĵ�����crud
public class Dom {
	@Test
	// ��ӡ����ǩ������ֵ
	public void read1() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");

		NodeList list = document.getElementsByTagName("����");
		Node node = list.item(0);
		String content = node.getTextContent();
		System.out.println(content);
	}

	@Test
	// �õ�xml�ĵ��еı�ǩ
	public void read2() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");

		// �õ����ڵ�
		Node root = document.getElementsByTagName("���").item(0);
		list(root);
	}

	private void list(Node node) {
		if (node instanceof Element) {
			System.out.println(node.getNodeName());
		}
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			list(child);
		}
	}

	// �õ�xml�ĵ��б�ǩ���Ե�ֵ
	@Test
	public void read3() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");

		Element bookname = (Element) document.getElementsByTagName("����").item(0);
		String value = bookname.getAttribute("name");
		System.out.println(value);
	}
	@Test
	// ��xml�ĵ�����ӽڵ㣺<�ۼ�>89.00Ԫ</�ۼ�>
	public void add() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//�����ڵ�
		Element price=document.createElement("�ۼ�");
		price.setTextContent("89.00Ԫ");
		//�Ѵ����Ľڵ�ҵ���һ������
		Element book=(Element)document.getElementsByTagName("��").item(0);
		book.appendChild(price);
		//�Ѹ��º���ڴ�д��xml�ĵ�
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
		
	}
	@Test
	// ��xml�ĵ�����ӽڵ㣺<�ۼ�>89.00Ԫ</�ۼ�>
	public void add2() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//�����ڵ�
		Element price=document.createElement("�ۼ�");
		price.setTextContent("89.00Ԫ");
		//�õ��ο��ڵ�
		Element refNode=(Element)document.getElementsByTagName("�ۼ�").item(0);
		//�õ�Ҫ���̵Ľڵ�
		Element book=(Element)document.getElementsByTagName("��").item(0);
		//��book�ڵ��ָ��λ�ò�����
		book.insertBefore(price,refNode);
		//�Ѹ��º���ڴ�д��xml�ĵ�
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
		
	}
	@Test
	// ��xml�ĵ��ڵ���������ԣ�<���� >Java��ҵ��ѵ</����>�����name="javaweb"����
	public void addAttr() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		
		Element bookname=(Element)document.getElementsByTagName("����").item(0);
		bookname.setAttribute("name", "javaweb");
		//�Ѹ��º���ڴ�д��xml�ĵ�
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
	
	@Test
	public void delete() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//�õ�Ҫɾ���Ľڵ�
		Element e=(Element)document.getElementsByTagName("�ۼ�").item(0);
		//�õ�Ҫɾ���Ľڵ�İְ�
		Element book=(Element)document.getElementsByTagName("��").item(0);
		//�ְ���ɾ��
		book.removeChild(e);
		//�Ѹ��º���ڴ�д��xml�ĵ�
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
	@Test
	public void delete2() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//�õ�Ҫɾ���Ľڵ�
		Element e=(Element)document.getElementsByTagName("�ۼ�").item(0);
		//�õ�Ҫɾ���Ľڵ�İְֵİְ֣��ְֵİְ���ɾ�ְ�
		e.getParentNode().getParentNode().removeChild(e.getParentNode());
		//�Ѹ��º���ڴ�д��xml�ĵ�
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
	@Test
	//���¼۸�
	public void update() throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		
		Element e=(Element)document.getElementsByTagName("�ۼ�").item(0);
	    e.setTextContent("109Ԫ");
	  //�Ѹ��º���ڴ�д��xml�ĵ�
	  	TransformerFactory tffactory=TransformerFactory.newInstance();
	  	Transformer tf=tffactory.newTransformer();
	  	tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
}
