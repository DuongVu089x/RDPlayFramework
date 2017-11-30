package loggers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import controllers.routes;
import play.Logger;
import play.Logger.ALogger;
import play.http.HttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorHandler.
 */
@Singleton
public class ErrorHandler implements HttpErrorHandler {

	/** The Constant logger. */
	private static final ALogger logger = Logger.of(ErrorHandler.class);

	/* (non-Javadoc)
	 * @see play.http.HttpErrorHandler#onClientError(play.mvc.Http.RequestHeader, int, java.lang.String)
	 */
	public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
		logger.error("Error Client method={} uri={} remote-address={} message={}", request.method(), request.uri(),
				request.remoteAddress(), message);
		return CompletableFuture.completedFuture(Results.ok());
	}

	/* (non-Javadoc)
	 * @see play.http.HttpErrorHandler#onServerError(play.mvc.Http.RequestHeader, java.lang.Throwable)
	 */
	public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
		logger.error("Error Server method={} uri={} remote-address={} message={}", request.method(), request.uri(),
				request.remoteAddress(), exception.getMessage());
		return CompletableFuture.completedFuture(Results.redirect(routes.ApplicationController.login()));
	}
}