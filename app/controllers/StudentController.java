package controllers;

import java.util.List;

import javax.inject.Inject;

import entities.Student;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import securities.SecuredAction;
import securities.SecuredSecurity;
import services.ClassService;
import services.StudentService;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentController.
 */
@Security.Authenticated(SecuredSecurity.class)
@With(SecuredAction.class)
public class StudentController extends Controller {

	/** The form factory. */
	private FormFactory formFactory;
	
	/** The student service. */
	private StudentService studentService;
	
	/** The class service. */
	private ClassService classService;

	/**
	 * Instantiates a new student controller.
	 *
	 * @param formFactory the form factory
	 * @param studentService the student service
	 * @param classService the class service
	 */
	@Inject
	public StudentController(FormFactory formFactory, StudentService studentService, ClassService classService) {
		this.formFactory = formFactory;
		this.studentService = studentService;
		this.classService = classService;
	}

	/**
	 * Index.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result index() throws Exception {

		Integer page = 1;
		String keyword = "";

		if (request().getQueryString("page") != null) {
			page = Integer.parseInt(request().getQueryString("page"));
			keyword = request().getQueryString("keyword");
			session("page", page + "");
			session("keyword", keyword);
		} else if (session().get("page") != null) {
			page = Integer.parseInt(session().get("page"));
			keyword = session().get("keyword");
		} else {
			session("page", page + "");
			session("keyword", keyword);
		}

		List<Student> students = studentService.findByPageAndKeyword(page, keyword);
		if (page != 1 && students.size() == 0) {
			students = studentService.findByPageAndKeyword(--page, keyword);
			session("page", page + "");
		}
		return ok(views.html.index.render(students, studentService.getTotalPage(keyword), page, keyword));
	}

	/**
	 * Gets the student by id.
	 *
	 * @param id the id
	 * @return the student by id
	 * @throws Exception the exception
	 */
	public Result getStudentById(Integer id) throws Exception {
		Student student = studentService.findById(id);
		return ok(views.html.edit.render(formFactory.form(Student.class).fill(student), student.getClasses(),
				classService.findAll()));
	}

	/**
	 * Update student.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result updateStudent() throws Exception {
		Form<Student> form = formFactory.form(Student.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(views.html.edit.render(form, form.get().getClasses(), classService.findAll()));
		}
		String[] classIds = null;
		if (request().body().asFormUrlEncoded().containsKey("classId")) {
			classIds = request().body().asFormUrlEncoded().get("classId");
		}
		studentService.updateStudent(form.get(), classIds);
		flash("message", "Update student success");
		return redirect(routes.StudentController.index());
	}

	/**
	 * Insert student.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result insertStudent() throws Exception {
		return ok(views.html.insert.render(formFactory.form(Student.class).fill(new Student()), classService.findAll()));
	}

	/**
	 * Insert student post.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result insertStudentPost() throws Exception {

		Form<Student> form = formFactory.form(Student.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(views.html.insert.render(form, classService.findAll()));
		}
		String[] classIds = null;
		if (request().body().asFormUrlEncoded().containsKey("classId")) {
			classIds = request().body().asFormUrlEncoded().get("classId");
		}

		studentService.insertStudent(form.get(), classIds);
		flash("message", "Insert student success");
		return redirect(routes.StudentController.index());
	}

	/**
	 * Delete student.
	 *
	 * @param id the id
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result deleteStudent(Integer id) throws Exception {
		studentService.deleteStudent(id);
		flash("message", "Delete student success");
		return redirect(routes.StudentController.index());
	}

}
