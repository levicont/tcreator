package com.lvg.tcreator.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class Initializer implements WebApplicationInitializer{

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext ctxConfig = new AnnotationConfigWebApplicationContext();
		ctxConfig.register(WebAppConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(ctxConfig));
		
		ctxConfig.setServletContext(servletContext);
		
		ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctxConfig));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		System.out.println("---------------------*****************LOADING************---");
	}
	
}
