package com.java.request;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.java.User;

/**
 * Servlet implementation class RequestDemo2
 */
@WebServlet("/RequestDemo2")
// 获取请求头和请求数据
public class RequestDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestDemo2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	// 获取数据时，一定要先检查再使用
	private void test2(HttpServletRequest request) throws IOException {
		// 获得单个数据
		String value = request.getParameter("name");
		if (value != null && value.trim().equals("")) {
			System.out.println(value);
		}

		System.out.println("----------------------");

		// 获得提交的所有数据
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			value = request.getParameter(name);
			System.out.println(name + "=" + value);
		}

		System.out.println("----------------------");

		// 一次性获得username的所有数据
		String[] values = request.getParameterValues("username");
		for (int i = 0; values != null && i < values.length; i++) {
			System.out.println(values[i]);
		}
		
		System.out.println("----------------------");
		
		//这边是String[]数组，因为可能封装的是同名的数据，这一点要非常注意。这样的方式在开发中会经常应用到
		Map<String,String[]> map=request.getParameterMap();
		User user=new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(user);
		
		System.out.println("----------------------");
		
		//如果要下载文件，则用流的方式
		InputStream  in=request.getInputStream();
		int len=0;
		byte buffer[]=new byte[1024];
		while((len=in.read(buffer))>0){
			System.out.println(len);
			System.out.println(new String(buffer,0,len));
		}
		
	}

	// 获取头相关的方法
	private void text1(HttpServletRequest request) {
		String headValue = request.getHeader("Accept-Encoding");
		System.out.println(headValue);

		System.out.println("----------------------");

		Enumeration e = request.getHeaders("Accept-Encoding");
		while (e.hasMoreElements()) {
			String value = (String) e.nextElement();
			System.out.println(value);
		}

		System.out.println("----------------------");

		e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = request.getHeader(name);
			System.out.println(name + "=" + value);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
