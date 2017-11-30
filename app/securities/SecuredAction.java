package securities;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Result;

public class SecuredAction extends Action.Simple {
	
	@Override
	public CompletionStage<Result> call(Context ctx) {
		final Request request = ctx.request();
		String role = ctx.session().get("role");
		if (role != null) {
			String url = request.uri();
			CompletionStage<Result> result = null;
			switch (role) {
			case "ADMIN":
				result = delegate.call(ctx);
				break;
			case "MOD":
				if (url.contains("delete")) {
					result = CompletableFuture.supplyAsync(() -> redirect("/login"));
				} else {
					result = delegate.call(ctx);
				}
				break;
			case "USER":
				if (url.contains("delete") || url.contains("update") || url.contains("edit") || url.contains("insert")) {
					result = CompletableFuture.supplyAsync(() -> redirect("/login"));
				} else {
					result = delegate.call(ctx);
				}
				break;
			default:
				result = CompletableFuture.supplyAsync(() -> redirect("/login"));
				break;
			}
			return result;
		}

		return CompletableFuture.supplyAsync(() -> redirect("/login"));
	}

}
