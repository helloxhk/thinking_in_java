package com.xhk.demo.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * @author xhk
 * @time 2018-12-03 18:23
 */
public class LoggingException2 {

	private static Logger logger = Logger.getLogger("LoggingException2");

	static void logException(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logger.severe(sw.toString());
	}

	public static void main(String[] args) {
		try {
			throw new NullPointerException();
		} catch (Exception e) {
			logException(e);
		}
	}
}
