package controllers;

import java.io.File;

import javax.inject.Inject;

import bean.UpdateInfoFormBean;
import entities.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import securities.SecuredAction;
import securities.SecuredSecurity;
import services.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@Security.Authenticated(SecuredSecurity.class)
@With(SecuredAction.class)
public class UserController extends Controller {

	/** The form factory. */
	private FormFactory formFactory;
	
	/** The user service. */
	private UserService userService;

	/**
	 * Instantiates a new user controller.
	 *
	 * @param userService the user service
	 * @param formFactory the form factory
	 */
	@Inject
	public UserController(UserService userService, FormFactory formFactory) {
		this.userService = userService;
		this.formFactory = formFactory;
	}

	/**
	 * Index.
	 *
	 * @param username the username
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result index(String username) throws Exception {
		if (!session().get("username").equals(username)) {
			return redirect(routes.ApplicationController.login());
		}
		User user = userService.findByUsername(session().get("username"));
		UpdateInfoFormBean updateInfoFormBean = new UpdateInfoFormBean();
		updateInfoFormBean.setUsername(username);
		updateInfoFormBean.setPassword(user.getPassword());
		updateInfoFormBean.setConfirmPassword(user.getPassword());
		return ok(views.html.user.index.render(formFactory.form(UpdateInfoFormBean.class).fill(updateInfoFormBean), user.getAvatar()));
	}

	/**
	 * Update info.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result updateInfo() throws Exception {
		Form<UpdateInfoFormBean> form = formFactory.form(UpdateInfoFormBean.class).bindFromRequest();
		if(form.hasErrors()){
			return badRequest(views.html.user.index.render(form, null));
		}
		String username = session().get("username");
		if(userService.updateUser(form.get())){
			flash("success", "Update success");
			return redirect(routes.UserController.index(username));
		}
		flash("error", "Update not success");
		return redirect(routes.UserController.index(username));
	}
	
	/**
	 * Change avatar.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result changeAvatar() throws Exception{
		Http.MultipartFormData<File> body = request().body().asMultipartFormData();
		Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");

		String username = session().get("username");
		String result = userService.changeAvatar(picture, username);
		if (result != null && !result.equals("")) {
			flash("successImage", "Change avatar success");
			flash("img", result);
			return redirect(routes.UserController.index(username));
		}
		flash("errorImage", "Missing file");
		return redirect(routes.UserController.index(username));
	}
}
