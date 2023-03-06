package com.xworkz.finalproject.loggers;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class ProjectLogger {
	private static final Logger logger;

	public static Logger getLogger() {
		return logger;
	}

	static {
		logger = Logger.getLogger(ProjectLogger.class.getName());

		try {
			// Creating Console Handler and FileHandler
			Handler fileHandler = new FileHandler("E:\\LoggerInfo.log");
			Handler consoleHandler = new ConsoleHandler();

			// Assigning Handlers to logging object
			logger.addHandler(consoleHandler);
			logger.addHandler(fileHandler);

			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);

			// Setting levels To Handlers and Loggers
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			logger.setLevel(Level.ALL);

			logger.config("Configuration Done");

			// Console handler removed
			logger.removeHandler(consoleHandler);
			logger.log(Level.FINE, "Finer Logged");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Exception in File Handler" + e);
		}
		logger.finer("Finer Example on Logger Handler Completed");
	}

}
