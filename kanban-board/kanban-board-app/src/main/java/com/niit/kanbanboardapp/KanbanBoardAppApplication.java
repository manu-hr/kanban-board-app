package com.niit.kanbanboardapp;

import com.niit.kanbanboardapp.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableFeignClients
public class KanbanBoardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanBoardAppApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean registerUrl()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/auth/*");
		return filterRegistrationBean;
	}

//	@Bean
//	public FilterRegistrationBean filterRegistrationBean()
//	{
//		final CorsConfiguration configuration=new CorsConfiguration();
////		configuration.setAllowCredentials(true);
//		configuration.addAllowedOrigin("*");
//		configuration.addAllowedHeader("*");
//		configuration.addAllowedMethod("*");
//
//		final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**",configuration);
//
//		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>(new CorsFilter(source));
//		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return filterRegistrationBean;
//	}

}
