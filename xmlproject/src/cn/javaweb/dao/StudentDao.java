package cn.javaweb.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.javaweb.domain.Student;
import cn.javaweb.exception.StudentNotExistException;
import cn.javaweb.utils.XmlUtils;

public class StudentDao {
	public void add(Student s){
		try {
			Document document=XmlUtils.getDocument();
			//��������װѧ����Ϣ�ı�ǩ
			Element student_tag=document.createElement("student");
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());
			//�������ڷ�װѧ�����������ڵء��ɼ��ı�ǩ
			Element name=document.createElement("name");
			Element location=document.createElement("location");
			Element grade=document.createElement("grade");
			
			name.setTextContent(s.getName());
			location.setTextContent(s.getLocation());
			//grade��double���͵����ݣ�setTextContent�еı�����String���͵ģ����ⶫ�����ַ�����Ӿ����ַ���
			grade.setTextContent(s.getGrade()+"");
			
			student_tag.appendChild(name);
			student_tag.appendChild(location);
			student_tag.appendChild(grade);
			
			//�ѷ�װ����Ϣѧ����ǩ���ҵ��ĵ���
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//�����ڴ�
			XmlUtils.write2Xml(document);
		} catch (Exception e) {
			//ÿһ���쳣����������û������ģ�����ת����unchecked exception(����ʱ�쳣)
			throw new RuntimeException(e);
		} //checked exception(����ʱ�쳣)
		
	}
	public Student find(String examid){
		try {
			Document document=XmlUtils.getDocument();
			NodeList list=document.getElementsByTagName("student");
			for(int i=0;i<list.getLength();i++){
				Element student_tag=(Element)list.item(i);
				if(student_tag.getAttribute("examid").equals(examid)){
					//�ҳ���examid��ƥ���ѧ����new��һ��student�����װ���ѧ������Ϣ����
					Student s=new Student();
					s.setExamid(examid);
					s.setIdcard(student_tag.getAttribute("idcard"));
					s.setName(student_tag.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	public void delete(String name) throws StudentNotExistException{
		try {
			Document document=XmlUtils.getDocument();
			NodeList list=document.getElementsByTagName("name");
			for(int i=0;i<list.getLength();i++){
				if(list.item(i).getTextContent().equals(name)){
					list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
					XmlUtils.write2Xml(document);
					return;
				}
			}
			throw new StudentNotExistException(name+"�����ڣ�");
		}catch(StudentNotExistException e){
			throw e;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
