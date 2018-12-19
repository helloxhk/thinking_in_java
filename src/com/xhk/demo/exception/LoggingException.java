package com.xhk.demo.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * @author xhk
 * @time 2018-12-03 18:07
 */
public class LoggingException extends Exception{

	private static Logger logger = Logger.getLogger("LoggingException");

	public LoggingException() {
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw));
		logger.severe(sw.toString());
	}

	public static void main(String[] args) {
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Caught " + e);
		}
		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Caught " + e);
		}
	}
}
