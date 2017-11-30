package loggers;

import play.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class LogUtils.
 */
public class LogUtils {
	
	/** The Constant logger. */
	public static final Logger.ALogger logger = Logger.of("application");
	
	/**
	 * Log start.
	 *
	 * @param method the method
	 */
	public static void logStart(String method){
		logger.info("********** START METHOD {} **********", method);
	}
	
	/**
	 * Log end.
	 *
	 * @param method the method
	 */
	public static void logEnd(String method){
		logger.info("********** END METHOD {} **********", method);
	}
	
	/**
	 * Log controller end.
	 *
	 * @param className the class name
	 * @param method the method
	 */
	public static void logControllerEnd(String className, String method){
		logger.info("********** END CLASS {} METHOD {} **********", className, method);
	}
	
	/**
	 * Log controller start.
	 *
	 * @param className the class name
	 * @param method the method
	 */
	public static void logControllerStart(String className, String method){
		logger.info("********** START CLASS {} METHOD {} **********", className, method);
	}
}
