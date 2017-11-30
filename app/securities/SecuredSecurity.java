package securities;

import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;

public class SecuredSecurity extends Security.Authenticator{
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("username");
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect("/login");
	}
}
