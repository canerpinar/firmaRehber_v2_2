package com.firmaRehber;



import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpEncodingProperties;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;


@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfig extends WebMvcConfigurerAdapter {
    
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
		
        registry.addViewController("/company-contact.html").setViewName("company-contact.html");
        registry.addViewController("/sample-page.html").setViewName("sample-page");
        registry.addViewController("/search-result.html").setViewName("search-result");
        registry.addViewController("/shortcodes.html").setViewName("shortcodes");
        registry.addViewController("/price-listing.html").setViewName("price-listing");
        registry.addViewController("/about-us.html").setViewName("about-us");
        registry.addViewController("/home-map-grid.html").setViewName("home-map-grid");
        registry.addViewController("/contact-us.html").setViewName("contact-us");
        registry.addViewController("/denemeTemplate.html").setViewName("denemeTemplate");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/**").addResourceLocations("");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/bootstrap-3.3.7/**").addResourceLocations("classpath:/static/bootstrap-3.3.7/");
		registry.addResourceHandler("/jquery-3.2.1/**").addResourceLocations("classpath:/static/jquery-3.2.1/");
		registry.addResourceHandler("/jpeg/**").addResourceLocations("classpath:/static/jpeg/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
	}

	
	@Autowired
	private HttpEncodingProperties httpEncodingProperties;

	@Bean
	public OrderedCharacterEncodingFilter characterEncodingFilter() {
	    OrderedCharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
	    filter.setEncoding(this.httpEncodingProperties.getCharset().name());
	    filter.setForceEncoding(this.httpEncodingProperties.isForce());
	    filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return filter;
	}
	
	@Bean
	  public SpringResourceTemplateResolver templateResolver() {
	    final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setCacheable(false);
	    templateResolver.setPrefix("classpath:/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setCharacterEncoding("UTF-8");
	    return templateResolver;
	  }

	  @Bean
	  public SpringTemplateEngine springTemplateEngine() {
	    SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
	    springTemplateEngine.setTemplateResolver(templateResolver());
	    springTemplateEngine.addDialect(new LayoutDialect());
	    return springTemplateEngine;
	  }

	  @Bean
	  public ThymeleafViewResolver viewResolver() {
	    ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
	    thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
	    thymeleafViewResolver.setCharacterEncoding("UTF-8");
	    return thymeleafViewResolver;
	  }

	@Bean
	public LocaleResolver localeResolver(){
	       SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	       sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("tr_TR"));
	       return sessionLocaleResolver;		
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
	

	
}
