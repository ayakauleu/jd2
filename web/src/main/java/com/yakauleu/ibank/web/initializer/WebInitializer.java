package com.yakauleu.ibank.web.initializer;

import com.yakauleu.ibank.web.config.SecurityConfig;
import com.yakauleu.ibank.web.config.WebConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String SERVLET_MAPPING = "/";

    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebConfiguration.class, SecurityConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[]{SERVLET_MAPPING};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}
