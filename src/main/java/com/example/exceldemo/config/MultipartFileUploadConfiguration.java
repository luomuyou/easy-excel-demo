package com.example.exceldemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * @Author：lmy
 * @Date：2023/06/02 14:17
 * @Version：1.0
 * @Description：
 */
@Configuration
public class MultipartFileUploadConfiguration {
	@Bean(name="multipartResolver")
	public MultipartResolver multipartResolver() {
		MultipartResolver multipartResolver = new StandardServletMultipartResolver();
		return multipartResolver;
	}
}