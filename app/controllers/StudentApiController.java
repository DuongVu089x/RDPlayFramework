package controllers;

import javax.inject.Inject;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.StudentService;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentApiController.
 */
public class StudentApiController extends Controller {

	/** The student service. */
	private StudentService studentService;

	/**
	 * Instantiates a new student api controller.
	 *
	 * @param studentService the student service
	 */
	@Inject
	public StudentApiController(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * Gets the student by keyword.
	 *
	 * @return the student by keyword
	 */
	public Result getStudentByKeyword() {
		String keyword = request().getQueryString("keyword");
		return ok(Json.toJson(studentService.findByPageAndKeyword(1, keyword)));
	}
}
