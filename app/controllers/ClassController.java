package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import bean.BaseViewModel;
import entities.Clazz;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import securities.SecuredAction;
import securities.SecuredSecurity;
import services.ClassService;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassController.
 */
@Security.Authenticated(SecuredSecurity.class)
@With(SecuredAction.class)
public class ClassController extends Controller {

	/** The class service. */
	private ClassService classService;
	
	/** The http execution context. */
	private HttpExecutionContext httpExecutionContext;
	
	/** The form factory. */
	private FormFactory formFactory;

	/**
	 * Instantiates a new class controller.
	 *
	 * @param httpExecutionContext the http execution context
	 * @param classService the class service
	 * @param formFactory the form factory
	 */
	@Inject
	public ClassController(HttpExecutionContext httpExecutionContext, ClassService classService,
			FormFactory formFactory) {
		this.httpExecutionContext = httpExecutionContext;
		this.classService = classService;
		this.formFactory = formFactory;
	}

	/**
	 * Index.
	 *
	 * @return the completion stage
	 * @throws Exception the exception
	 */
	public CompletionStage<Result> index() throws Exception {
		Integer pageClass = 1;
		String keywordClass = "";

		if (request().getQueryString("pageClass") != null) {
			pageClass = Integer.parseInt(request().getQueryString("pageClass"));
			keywordClass = request().getQueryString("keywordClass");
			session("pageClass", pageClass + "");
			session("keywordClass", keywordClass);
		} else if (session().get("pageClass") != null) {
			pageClass = Integer.parseInt(session().get("pageClass"));
			keywordClass = session().get("keywordClass");
		} else {
			session("pageClass", pageClass + "");
			session("keywordClass", keywordClass);
		}
		BaseViewModel<Clazz> baseViewModel = new BaseViewModel<Clazz>();
		baseViewModel.setKeyword(keywordClass);
		baseViewModel.setCurrentPage(pageClass);

		return classService.findByPageAndKeyword(baseViewModel).thenApplyAsync(result -> {
			return ok(views.html.clazz.index.render(result.getListEntity(), result.getTotalPage(),
					result.getCurrentPage(), result.getKeyword()));
		}, httpExecutionContext.current());
	}

	/**
	 * Gets the class by id.
	 *
	 * @param id the id
	 * @return the class by id
	 * @throws Exception the exception
	 */
	public CompletionStage<Result> getClassById(Integer id) throws Exception {
		return classService.getById(id).thenApplyAsync(result -> {
			return ok(views.html.clazz.edit.render(formFactory.form(Clazz.class).fill(result)));
		}, httpExecutionContext.current());
	}

	/**
	 * Gets the class by page and keyword.
	 *
	 * @return the class by page and keyword
	 * @throws Exception the exception
	 */
	public CompletionStage<Result> getClassByPageAndKeyword() throws Exception {
		return classService.getClassByPageAndKeyword(1, "").thenApplyAsync(list -> {
			return ok(Json.toJson(list));
		}, httpExecutionContext.current());
	}

	/**
	 * Update class.
	 *
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result updateClass() throws Exception {
		Form<Clazz> form = formFactory.form(Clazz.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(views.html.clazz.edit.render(form));
		}
		classService.update(form.get());
		flash("message", "Update class success");
		return redirect(routes.ClassController.index());
	}

	/**
	 * Insert class.
	 *
	 * @return the completion stage
	 * @throws Exception the exception
	 */
	public CompletionStage<Result> insertClass() throws Exception {
		return CompletableFuture
				.completedFuture(ok(views.html.clazz.insert.render(formFactory.form(Clazz.class).fill(new Clazz()))));
	}

	/**
	 * Insert class post.
	 *
	 * @return the completion stage
	 * @throws Exception the exception
	 */
	public CompletionStage<Result> insertClassPost() throws Exception {
		Form<Clazz> form = formFactory.form(Clazz.class).bindFromRequest();
		if (form.hasErrors()) {
			return CompletableFuture.completedFuture(badRequest(views.html.clazz.insert.render(form)));
		}
		return classService.insertClass(form.get()).thenApplyAsync(result -> {
			if (result) {
				flash("message", "Insert class success");
				return redirect(routes.ClassController.index());
			}
			flash("error", "Insert class not success");
			return badRequest(views.html.clazz.insert.render(form));
		}, httpExecutionContext.current());
	}

	/**
	 * Delete class.
	 *
	 * @param id the id
	 * @return the completion stage
	 * @throws Exception the exception
	 */
	public CompletionStage<Result> deleteClass(Integer id) throws Exception {
		return classService.deleteClass(id).thenApplyAsync(result ->{
			if (result) {
				flash("message", "Delete class success");
			}else{
				flash("error", "Delete class not success");	
			}
			return redirect(routes.ClassController.index());
		}, httpExecutionContext.current());
	}

	/**
	 * Demo.
	 *
	 * @return the completion stage
	 */
	public CompletionStage<Result> demo() {
		// Use a different task with explicit EC
		return calculateResponse().thenApplyAsync(answer -> {
			// uses Http.Context
			ctx().flash().put("info", "Response updated!");
			return ok(Json.toJson("answer was " + answer));
		}, httpExecutionContext.current());
	}

	/**
	 * Calculate response.
	 *
	 * @return the completion stage
	 */
	private static CompletionStage<String> calculateResponse() {
		return CompletableFuture.completedFuture("42");
	}
}
