package services;

import java.util.List;

import com.google.inject.ImplementedBy;

import entities.Student;
import services.impl.StudentServiceImpl;

@ImplementedBy(StudentServiceImpl.class)
public interface StudentService {
	Student findById(Integer id);

	List<Student> findByPageAndKeyword(int page, String keyword);

	Integer getTotalPage(String keyword) throws Exception;
	
	void insertStudent(Student student, String[] classIds) throws Exception;

	void updateStudent(Student student, String[] classIds) throws Exception;

	void deleteStudent(Integer id) throws Exception;

}
