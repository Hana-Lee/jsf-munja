package com.ihanalee.config;

import com.sun.faces.config.ConfigureListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * @author Hana Lee
 * @since 2016-06-22 18:27
 */
public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		setupCharacterEncodingFilter(servletContext);
		setupSpringApplicationContext(servletContext);
		setupFaceContext(servletContext);
	}

	private void setupCharacterEncodingFilter(ServletContext servletContext) {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("charaterEncodingFilter", characterEncodingFilter);
		filterRegistration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

	private void setupSpringApplicationContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.setServletContext(servletContext);

		servletContext.addListener(new ContextLoaderListener(ctx));
		servletContext.addListener(new RequestContextListener());

		ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
	}

	private void setupFaceContext(ServletContext servletContext) {
		/** Faces Servlet */
		ServletRegistration.Dynamic facesServlet = servletContext.addServlet("Faces Servlet", FacesServlet.class);
		facesServlet.setLoadOnStartup(1);
		facesServlet.addMapping("*.xhtml");

		// Use JSF view templates saved as *.xhtml, for use with Facelets
		servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		// Enable special Facelets debug output during development
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		// Causes Facelets to refresh templates during development
		servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

		servletContext.setInitParameter("primefaces.THEME", "bootstrap");
		// Controls client side validation.
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "false");
		// Enabled font-awesome icons. (default : false)
		servletContext.setInitParameter("primefaces.FONT_AWESOME", "true");

		servletContext.addListener(ConfigureListener.class);
	}
}
