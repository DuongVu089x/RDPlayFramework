package controllers;

import javax.inject.Inject;

import akka.stream.javadsl.FileIO;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

// TODO: Auto-generated Javadoc
/**
 * The Class StreamDemoController.
 */
public class StreamDemoController extends Controller {

	/**
	 * Instantiates a new stream demo controller.
	 *
	 * @param httpExecutionContext the http execution context
	 */
	@Inject
	public StreamDemoController(HttpExecutionContext httpExecutionContext) {
	}

	/**
	 * Index.
	 *
	 * @return the result
	 */
	public Result index() {
		return ok(views.html.demoHttpAsyns.render());
	}

	/**
	 * Index comet.
	 *
	 * @return the result
	 */
	public Result indexComet() {
		java.io.File file = new java.io.File(play.Play.application().path().getAbsoluteFile()
				+ "\\public\\files\\tmp\\clazz.js");
		java.nio.file.Path path = file.toPath();
		Source<ByteString, ?> source = FileIO.fromPath(path);
		return ok().chunked(source).as(Http.MimeTypes.JAVASCRIPT);
	}

	/**
	 * Demo.
	 *
	 * @return the result
	 */
	public Result demo() {
		java.io.File file = new java.io.File(play.Play.application().path().getAbsoluteFile()
				+ "\\public\\files\\tmp\\_TopTaiLieu.Com_Lap_Trinh_Kotlin_Toan_Tap_Cua_Tac_Gia_Tran_Duy_Thanh.pdf");
		java.nio.file.Path path = file.toPath();
		Source<ByteString, ?> source = FileIO.fromPath(path);
		return ok().chunked(source);
	}
}
