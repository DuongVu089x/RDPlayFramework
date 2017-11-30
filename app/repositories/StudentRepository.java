package repositories;

import java.util.List;

import com.google.inject.ImplementedBy;

import entities.Student;
import repositories.impl.StudentRepositoryImpl;

// TODO: Auto-generated Javadoc
/**
 * This interface provides a non-blocking API for possibly blocking operations.
 */
@ImplementedBy(StudentRepositoryImpl.class)
public interface StudentRepository {
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the student
	 */
	Student findById(Integer id);

	/**
	 * Find by page and keyword.
	 *
	 * @param page the page
	 * @param keyword the keyword
	 * @return the list
	 */
	List<Student> findByPageAndKeyword(int page, String keyword);

	/**
	 * Gets the total page.
	 *
	 * @param keyword the keyword
	 * @return the total page
	 * @throws Exception the exception
	 */
	Integer getTotalPage(String keyword) throws Exception;
	
	/**
	 * Insert student.
	 *
	 * @param student the student
	 * @throws Exception the exception
	 */
	void insertStudent(Student student) throws Exception;

	/**
	 * Update student.
	 *
	 * @param student the student
	 * @throws Exception the exception
	 */
	void updateStudent(Student student) throws Exception;

	/**
	 * Delete student.
	 *
	 * @param student the student
	 * @throws Exception the exception
	 */
	void deleteStudent(Student student) throws Exception;


}
