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
			System.out.println("����û�����a��  ɾ���û�����b��  �����û�����c��");
			System.out.print("������������ͣ�");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String type = br.readLine();
			if ("a".equals(type)) {
				System.out.print("�������û�������");
				String name = br.readLine();

				System.out.print("�������û�׼��֤�ţ�");
				String examid = br.readLine();

				System.out.print("�������û����֤�ţ�");
				String idcard = br.readLine();

				System.out.print("�������û����ڵأ�");
				String location = br.readLine();

				System.out.print("�������û��ɼ���");
				String grade = br.readLine();

				Student s = new Student();
				s.setExamid(examid);
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);
				s.setGrade(Double.parseDouble(grade));

				StudentDao dao = new StudentDao();
				dao.add(s);

				System.out.println("��ӳɹ���");
			} else if ("b".equals(type)) {
				System.out.println("������Ҫɾ����ѧ��������");
				String name = br.readLine();
				//
				try {
					StudentDao dao = new StudentDao();
					dao.delete(name);
					System.out.println("ɾ���ɹ�!");
				} catch (StudentNotExistException e) {
					//�Լ����ù����쳣Ҫ������try/catchһ��
					System.out.println("��Ҫɾ�����û������ڣ�");
				}

			} else if ("c".equals(type)) {
				System.out.print("������ҪѰ�ҵ��û�׼��֤�ţ�");
				String examid=br.readLine();
				StudentDao dao=new StudentDao();
				Student s=dao.find(examid);
				System.out.println("������"+s.getName());
				
			} else {
				System.out.println("��֧�����Ĳ���");
			}
		} catch (IOException e) {
			// �����Ĵ���Ҫ�׸��û��������Ѻ���ʾ�����Ҽ�¼�´�����Ϣ
			System.out.println("�Բ��𣬳����ˣ�");
			e.printStackTrace();
		}

	}

}
