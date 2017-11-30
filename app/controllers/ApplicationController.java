package controllers;

import javax.inject.Inject;

import bean.LoginFormBean;
import entities.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationController.
 */
public class ApplicationController extends Controller{

	/** The form factory. */
	private FormFactory formFactory;
	
	/** The user service. */
	private UserService userService;

	/**
	 * Instantiates a new application controller.
	 *
	 * @param formFactory the form factory
	 * @param userService the user service
	 */
	@Inject
	public ApplicationController(FormFactory formFactory, UserService userService) {
		this.formFactory = formFactory;
		this.userService = userService;
	}
	
	/**
	 * Login.
	 *
	 * @return the result
	 */
	public Result login(){
		return ok(views.html.login.render(formFactory.form(LoginFormBean.class).fill(new LoginFormBean())));
	}
	
	/**
	 * Authenticate.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result authenticate() throws Exception{
	    Form<LoginFormBean> loginForm = formFactory.form(LoginFormBean.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(views.html.login.render(loginForm));
		}
		
		User user = userService.checkLogin(loginForm.get());
		if(user == null){
			flash("message", "Invalid user or password");
			return redirect(routes.ApplicationController.login());
		}
		session().clear();
		session("username", user.getUsername());
		session("role", user.getRole());
	    return redirect(routes.StudentController.index());
	}
	
	/**
	 * Logout.
	 *
	 * @return the result
	 */
	public Result logout(){
		session().clear();
	    flash("success", "You've been logged out");
	    return redirect(routes.ApplicationController.login());
	}
}
