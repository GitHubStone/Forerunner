package com.forerunner.web.listener;

import java.beans.Introspector;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.common.base.Stopwatch;

public class FRApplicationListener extends ContextLoaderListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(FRApplicationListener.class);

	public void contextInitialized(ServletContextEvent event) {
		Stopwatch watch = Stopwatch.createStarted().start();

		if (log.isDebugEnabled()) {
			log.debug("Initializing Spring web application context...");
		}

		super.contextInitialized(event);

		ServletContext servletContext = event.getServletContext();

		AbstractApplicationContext applicationContext = (AbstractApplicationContext) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

		watch.stop();

		if (log.isDebugEnabled())
			log.debug("Initializing Spring web application context... done in " + watch.elapsed(TimeUnit.MILLISECONDS) + ", Bean count: " + applicationContext.getBeanDefinitionCount());

		applicationContext.start();

		logSystemProperties();
	}

	@SuppressWarnings("rawtypes")
	public void contextDestroyed(ServletContextEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Destroying Spring web application context...");
		}

		super.contextDestroyed(event);

		try {
			Introspector.flushCaches();
			for (Enumeration e = DriverManager.getDrivers(); e.hasMoreElements();) {
				Driver driver = (Driver) e.nextElement();
				if (driver.getClass().getClassLoader() == getClass().getClassLoader()) {
					DriverManager.deregisterDriver(driver);
				}
			}
		} catch (Throwable e) {
			System.err.println("Failled to cleanup ClassLoader for webapp");
			e.printStackTrace();
		}
	}

	private void logSystemProperties() {
		if (log.isInfoEnabled()) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("\n\nForerunner (FR) \n\n");

			buffer.append("********** Start Display Current Environment **********\n");
			Locale defaultLocale = Locale.getDefault();
			buffer.append("Host Operating System: ").append(SystemUtils.OS_NAME).append(", version ").append(SystemUtils.OS_VERSION).append("\n");
			buffer.append("JVM name: ").append(SystemUtils.JAVA_VM_NAME).append(", version ").append(SystemUtils.JAVA_VM_VERSION).append("\n");
			buffer.append("Java Home = ").append(SystemUtils.JAVA_HOME).append("\n");
			buffer.append("Classpath = ").append(SystemUtils.JAVA_CLASS_PATH).append("\n");
			buffer.append("JVM's character encoding: " + System.getProperty("file.encoding")).append("\n");
			buffer.append("Default locale is " + defaultLocale + ": " + defaultLocale.getDisplayName()).append("\n");

			buffer.append("********** End Display Current Environment **********\n");

			log.info(buffer.toString());
		}
	}
}
