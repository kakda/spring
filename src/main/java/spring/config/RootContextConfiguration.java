package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import spring.config.intercepter.webmvc.ForwarderIntercepter;

@Configuration
@ComponentScan(
        basePackages = "spring.controller.web",
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
public class RootContextConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
//	@Bean
//	public ViewResolver viewResolver(){
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setViewClass(JstlView.class);
//		resolver.setPrefix("/WEB-INF/view/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new ForwarderIntercepter());
	//	super.addInterceptors(registry);
	}
}
