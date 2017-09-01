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

//使用dom方式对xml文档进行crud
public class Dom {
	@Test
	// 打印出标签的内容值
	public void read1() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");

		NodeList list = document.getElementsByTagName("书名");
		Node node = list.item(0);
		String content = node.getTextContent();
		System.out.println(content);
	}

	@Test
	// 得到xml文档中的标签
	public void read2() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");

		// 得到根节点
		Node root = document.getElementsByTagName("书架").item(0);
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

	// 得到xml文档中标签属性的值
	@Test
	public void read3() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");

		Element bookname = (Element) document.getElementsByTagName("书名").item(0);
		String value = bookname.getAttribute("name");
		System.out.println(value);
	}
	@Test
	// 向xml文档中添加节点：<售价>89.00元</售价>
	public void add() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//创建节点
		Element price=document.createElement("售价");
		price.setTextContent("89.00元");
		//把创建的节点挂到第一本书上
		Element book=(Element)document.getElementsByTagName("书").item(0);
		book.appendChild(price);
		//把更新后的内存写到xml文档
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
		
	}
	@Test
	// 向xml文档中添加节点：<售价>89.00元</售价>
	public void add2() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//创建节点
		Element price=document.createElement("售价");
		price.setTextContent("89.00元");
		//得到参考节点
		Element refNode=(Element)document.getElementsByTagName("售价").item(0);
		//得到要挂崽的节点
		Element book=(Element)document.getElementsByTagName("书").item(0);
		//往book节点的指定位置插入崽
		book.insertBefore(price,refNode);
		//把更新后的内存写到xml文档
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
		
	}
	@Test
	// 向xml文档节点上添加属性：<书名 >Java就业培训</书名>上添加name="javaweb"属性
	public void addAttr() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		
		Element bookname=(Element)document.getElementsByTagName("书名").item(0);
		bookname.setAttribute("name", "javaweb");
		//把更新后的内存写到xml文档
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
	
	@Test
	public void delete() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//得到要删除的节点
		Element e=(Element)document.getElementsByTagName("售价").item(0);
		//得到要删除的节点的爸爸
		Element book=(Element)document.getElementsByTagName("书").item(0);
		//爸爸再删崽
		book.removeChild(e);
		//把更新后的内存写到xml文档
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
	@Test
	public void delete2() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		//得到要删除的节点
		Element e=(Element)document.getElementsByTagName("售价").item(0);
		//得到要删除的节点的爸爸的爸爸，爸爸的爸爸再删爸爸
		e.getParentNode().getParentNode().removeChild(e.getParentNode());
		//把更新后的内存写到xml文档
		TransformerFactory tffactory=TransformerFactory.newInstance();
		Transformer tf=tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
	@Test
	//更新价格
	public void update() throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("src/1.xml");
		
		Element e=(Element)document.getElementsByTagName("售价").item(0);
	    e.setTextContent("109元");
	  //把更新后的内存写到xml文档
	  	TransformerFactory tffactory=TransformerFactory.newInstance();
	  	Transformer tf=tffactory.newTransformer();
	  	tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/1.xml")));
	}
}
