package junit.test;

import org.junit.Test;

import cn.javaweb.dao.StudentDao;
import cn.javaweb.domain.Student;
import cn.javaweb.exception.StudentNotExistException;

public class StudentDaoTest {
	@Test
	public void testAdd(){
		StudentDao dao=new StudentDao();
		Student s=new Student();
		s.setExamid("121");
		s.setGrade(89);
		s.setIdcard("121");
		s.setLocation("±±æ©");
		s.setName("–ª‘√‹ø");
		dao.add(s);
	}
	@Test
	public void testFind(){
		StudentDao dao=new StudentDao();
		dao.find("121");
	}
	@Test
	public void testDelete() throws StudentNotExistException{
		StudentDao dao=new StudentDao();
		dao.delete("–ª‘√‹ø");
	}
	
	

}
