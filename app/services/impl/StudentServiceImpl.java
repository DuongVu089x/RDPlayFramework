package services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import entities.Student;
import io.ebean.Ebean;
import loggers.LogUtils;
import repositories.ClassRepository;
import repositories.StudentRepository;
import services.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private ClassRepository classRepository;

	@Inject
	public StudentServiceImpl(StudentRepository studentRepository, ClassRepository classRepository) {
		this.studentRepository = studentRepository;
		this.classRepository = classRepository;
	}

	@Override
	public Student findById(Integer id) {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		Student student = studentRepository.findById(id);
		LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		return student;
	}

	@Override
	public List<Student> findByPageAndKeyword(int page, String keyword) {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		List<Student> students = studentRepository.findByPageAndKeyword(10 * (page - 1), keyword);
		LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		return students;
	}

	@Override
	public Integer getTotalPage(String keyword) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		int totalPage = (int) Math.ceil((float) studentRepository.getTotalPage(keyword) / 10);
		LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		return totalPage;
	}

	@Override
	public void insertStudent(Student student, String[] classIds) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ebean.beginTransaction();
		try {
			for(int i = 0;i< classIds.length; i++){
				student.getClasses().add(classRepository.findById(Integer.parseInt(classIds[i])));	
			}
			studentRepository.insertStudent(student);
			Ebean.commitTransaction();
		} catch (Exception e) {
			Ebean.rollbackTransaction();
			throw e;
		} finally {
			Ebean.endTransaction();
			LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void updateStudent(Student student, String[] classIds) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ebean.beginTransaction();
		try {
			student.setClasses(new ArrayList<>());
			for(int i = 0;i< classIds.length; i++){
				student.getClasses().add(classRepository.findById(Integer.parseInt(classIds[i])));	
			}
			studentRepository.updateStudent(student);
			Ebean.commitTransaction();
		} catch (Exception e) {
			Ebean.rollbackTransaction();
			throw e;
		} finally {
			Ebean.endTransaction();
			LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	@Override
	public void deleteStudent(Integer id) throws Exception {
		LogUtils.logStart(Thread.currentThread().getStackTrace()[1].getMethodName());
		Ebean.beginTransaction();
		try {
			Student student = studentRepository.findById(id);
			if (student == null) {
				return;
			}
			studentRepository.deleteStudent(student);
			Ebean.commitTransaction();
		} catch (Exception e) {
			Ebean.rollbackTransaction();
			throw e;
		} finally {
			Ebean.endTransaction();
			LogUtils.logEnd(Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}
}
