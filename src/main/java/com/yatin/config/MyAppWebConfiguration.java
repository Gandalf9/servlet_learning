package com.yatin.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * 
 * By using the Spring WebApplicationInitializer we have removed the need for the web.xml
 * 
 * @author yatinmistry
 *
 */
public class MyAppWebConfiguration implements WebApplicationInitializer {

	private static final String REST_API_PREFIX = "/rest";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		registerParams(servletContext);
		registerListners(servletContext, getContext());
		registerServlets(servletContext);
	}

	private void registerServlets(ServletContext servletContext) {
		Dynamic restEasyServlet = servletContext.addServlet("Resteasy", new HttpServletDispatcher());
		restEasyServlet.addMapping(REST_API_PREFIX + "/*");
	}
	
	private void registerParams(ServletContext servletContext) {
		/*
		 * NOTE: As servlet-mapping for the Resteasy servlet has a url-pattern other than '/*' we need to set
		 * the 'resteasy.servlet.mapping.prefix'
		 */
        servletContext.setInitParameter("resteasy.servlet.mapping.prefix", REST_API_PREFIX);
        servletContext.setInitParameter("first_context_param_name", "first_context_param_value");
    }
	
	private void registerListners(ServletContext servletContext, AnnotationConfigWebApplicationContext rootContext) {
		servletContext.addListener(new ResteasyBootstrap());
		servletContext.addListener(new MyAppResteasyConfig(rootContext));
	}
	
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		
		/* 
		 * The below code is interchangeable you would use register() to register individual spring config classes
		 * and you can use setConfigLocation() to set where to scan for spring config classes
		 */
		ctx.register(MyAppSpringConfig.class);
		ctx.setConfigLocation("com.yatin.config");
		
		return ctx;
	}
}
