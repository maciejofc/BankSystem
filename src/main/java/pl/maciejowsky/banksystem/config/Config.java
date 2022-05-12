package pl.maciejowsky.banksystem.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
// Initialize servlet container. This class is autodected by servlet container
// on startup
public class Config extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    // returned class define shared resources visible to all other web components
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
