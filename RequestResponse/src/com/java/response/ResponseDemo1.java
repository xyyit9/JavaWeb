package com.java.response;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//��servlet�У���outputstream������ĵ�����
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
  
    public ResponseDemo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//servletָ���������UTF-8�����������Ĭ�ϵ������GB2312�����д����ʾ��������������ΪUTF-8
		//������ʲô�������������һ��Ҫ�����������ʲô����
		//response.setHeader("Content-type", "text/html,charset=UTF-8");������д�����������ʾ�����ļ�
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		String data="�й�";
		OutputStream out=response.getOutputStream();
		out.write(data.getBytes("UTF-8"));
	}
protected void test2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//html:  <meta>��ǩ����ģ��һ��http��Ӧͷ.
		//����html�����е�meta��ǩģ����һ��http��Ӧͷ�����������������Ϊ
		
		String data="�й�";
		OutputStream out=response.getOutputStream();
		
		out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>".getBytes());
		out.write(data.getBytes("UTF-8"));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
