package com.java.response;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseDemo5
 */
@WebServlet("/ResponseDemo5")
public class ResponseDemo5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResponseDemo5() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message="<meta http-equiv='refresh' content='3;url=register.html'>��ϲ�㣬��½�ɹ���������������������ת����ҳ�����û����ת������<a href=''>������</a>";
		this.getServletContext().setAttribute("message", message);
		this.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void text2(HttpServletResponse response) throws IOException {
		//��������һ�����ڴ����¼��servlet
		
		//����������е��ˣ��û���¼�ɹ�
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("refresh", "3;url='register.html'");
		response.getWriter().write("��ϲ�㣬��½�ɹ���������������������ת����ҳ�����û����ת������<a href=''>������</a>");
	}

	//ÿ3�������ˢ��һ��
	private void text1(HttpServletResponse response) throws IOException {
		response.setHeader("refresh", "3");
		String data = new Random().nextInt(10000) + "";
		response.getWriter().write(data);
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
