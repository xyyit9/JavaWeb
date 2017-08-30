package cn.javaweb.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.javaweb.dao.StudentDao;
import cn.javaweb.domain.Student;
import cn.javaweb.exception.StudentNotExistException;

public class Main {
	public static void main(String[] args) {
		try {
			System.out.println("添加用户：（a）  删除用户：（b）  查找用户：（c）");
			System.out.print("请输入操作类型：");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String type = br.readLine();
			if ("a".equals(type)) {
				System.out.print("请输入用户姓名：");
				String name = br.readLine();

				System.out.print("请输入用户准考证号：");
				String examid = br.readLine();

				System.out.print("请输入用户身份证号：");
				String idcard = br.readLine();

				System.out.print("请输入用户所在地：");
				String location = br.readLine();

				System.out.print("请输入用户成绩：");
				String grade = br.readLine();

				Student s = new Student();
				s.setExamid(examid);
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);
				s.setGrade(Double.parseDouble(grade));

				StudentDao dao = new StudentDao();
				dao.add(s);

				System.out.println("添加成功！");
			} else if ("b".equals(type)) {
				System.out.println("请输入要删除的学生姓名：");
				String name = br.readLine();
				//
				try {
					StudentDao dao = new StudentDao();
					dao.delete(name);
					System.out.println("删除成功!");
				} catch (StudentNotExistException e) {
					//自己设置过得异常要重新再try/catch一次
					System.out.println("您要删除的用户不存在！");
				}

			} else if ("c".equals(type)) {
				System.out.print("请输入要寻找的用户准考证号：");
				String examid=br.readLine();
				StudentDao dao=new StudentDao();
				Student s=dao.find(examid);
				System.out.println("姓名："+s.getName());
				
			} else {
				System.out.println("不支持您的操作");
			}
		} catch (IOException e) {
			// 界面层的错误不要抛给用户看，作友好提示，并且记录下错误信息
			System.out.println("对不起，出错了！");
			e.printStackTrace();
		}

	}

}
