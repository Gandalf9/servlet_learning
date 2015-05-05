package com.yatin.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


/**
 * 
 * By using the Spring WebApplicationInitializer we have removed the need for the web.xml
 * 
 * @author yatinmistry
 *
 */
public class MyAppWebConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		WebApplicationContext ctx = getContext();
		servletContext.addListener(new ContextLoaderListener(ctx));
		
		servletContext.setInitParameter("first_context_param_name", "first_context_param_value");
	}
	
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setConfigLocation("com.yatin.config");
		return ctx;
	}

}
