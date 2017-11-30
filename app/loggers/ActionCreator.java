package loggers;

import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class ActionCreator.
 */
public class ActionCreator implements play.http.ActionCreator {

	/* (non-Javadoc)
	 * @see play.http.ActionCreator#createAction(play.mvc.Http.Request, java.lang.reflect.Method)
	 */
	@Override
	public Action<Void> createAction(Request request, Method actionMethod) {
		return new Action.Simple() {
			@Override
			public CompletionStage<Result> call(Context ctx) {
				LogUtils.logControllerStart(actionMethod.getDeclaringClass().getName(), actionMethod.getName());
				CompletionStage<Result> result = delegate.call(ctx);
				LogUtils.logControllerEnd(actionMethod.getDeclaringClass().getName(), actionMethod.getName());
				return result;
			}
		};
	}

}
