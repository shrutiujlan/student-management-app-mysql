package StudentManagementSystem.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration

public class ApplicationConfig {

	@Bean
	public WebMvcConfigurer getConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/static/**").addResourceLocations("/static/");
				
			}

			@Override
			public void configureViewResolvers(ViewResolverRegistry registry) {
				registry.viewResolver(new InternalResourceViewResolver("/", ".html"));
			}

		};
	}


}
