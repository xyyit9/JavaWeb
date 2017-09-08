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
// ��ȡ����ͷ����������
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

	// ��ȡ����ʱ��һ��Ҫ�ȼ����ʹ��
	private void test2(HttpServletRequest request) throws IOException {
		// ��õ�������
		String value = request.getParameter("name");
		if (value != null && value.trim().equals("")) {
			System.out.println(value);
		}

		System.out.println("----------------------");

		// ����ύ����������
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			value = request.getParameter(name);
			System.out.println(name + "=" + value);
		}

		System.out.println("----------------------");

		// һ���Ի��username����������
		String[] values = request.getParameterValues("username");
		for (int i = 0; values != null && i < values.length; i++) {
			System.out.println(values[i]);
		}
		
		System.out.println("----------------------");
		
		//�����String[]���飬��Ϊ���ܷ�װ����ͬ�������ݣ���һ��Ҫ�ǳ�ע�⡣�����ķ�ʽ�ڿ����лᾭ��Ӧ�õ�
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
		
		//���Ҫ�����ļ����������ķ�ʽ
		InputStream  in=request.getInputStream();
		int len=0;
		byte buffer[]=new byte[1024];
		while((len=in.read(buffer))>0){
			System.out.println(len);
			System.out.println(new String(buffer,0,len));
		}
		
	}

	// ��ȡͷ��صķ���
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
