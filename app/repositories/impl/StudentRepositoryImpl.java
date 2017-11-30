package repositories.impl;

import java.util.List;

import javax.inject.Singleton;

import entities.Student;
import io.ebean.Ebean;
import repositories.StudentRepository;

/**
 * Provide JPA operations running inside of a thread pool sized to the
 * connection pool
 */
@Singleton
public class StudentRepositoryImpl implements StudentRepository {

	public Student findById(Integer id) {
		return Student.find.byId(id);
	}

	public List<Student> findByPageAndKeyword(int page, String keyword) {
		return Student.find.query().where()
				.ilike("name","%"+keyword+"%")
				.orderBy("id desc")
				.setFirstRow(page)
				.setMaxRows(10)
				.findPagedList()
			    .getList();
	}
	
	public Integer getTotalPage(String keyword) throws Exception {
		String sql = "SELECT COUNT(*) as total "
				+ "FROM student s "
				+ "WHERE s.name LIKE :keyword";
		int total = Ebean.createSqlQuery(sql).setParameter("keyword", "%" + keyword + "%").findOne()
				.getInteger("total");
		return total;
	}
	
	public void insertStudent(Student student) throws Exception {
		student.save();
	}
	
	public void updateStudent(Student student) throws Exception {
		student.update();
	}
	
	public void deleteStudent(Student student) throws Exception {
		student.delete();
	}
}
