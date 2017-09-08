package com.java.response;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//在servlet中，用outputstream输出中文的问题
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  
    public ResponseDemo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//servlet指定的码表是UTF-8，但是浏览器默认的码表是GB2312，这行代码表示控制浏览器的码表为UTF-8
		//程序以什么码表输出，程序就一定要控制浏览器以什么码表打开
		//response.setHeader("Content-type", "text/html,charset=UTF-8");若这样写错，浏览器会提示下载文件
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String data="中国";
		OutputStream out=response.getOutputStream();
		out.write(data.getBytes("UTF-8"));
	}
protected void test2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//html:  <meta>标签可以模拟一个http响应头.
		//故用html技术中的meta标签模拟了一个http响应头，来控制浏览器的行为
		
		String data="中国";
		OutputStream out=response.getOutputStream();
		
		out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>".getBytes());
		out.write(data.getBytes("UTF-8"));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
