package com.java;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;


public class Xpathuser {
	public static void main(String[] args) throws DocumentException {
		String username="aaa";
		String password="123";
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/user.xml")); 
		
		//注意文档的书写方法
		Node node=(Node) document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
		if(node==null){
			System.out.println("用户名或密码错误");
		}else{
			System.out.println("登陆成功!");
		}
		
	}
}
