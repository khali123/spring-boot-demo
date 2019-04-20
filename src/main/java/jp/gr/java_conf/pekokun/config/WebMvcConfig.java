package jp.gr.java_conf.pekokun.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.gr.java_conf.pekokun.web.interceptor.MultipartFileInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${app.file.max-file-size:-1}")
	private String fileMaxFileSize;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MultipartFileInterceptor(fileMaxFileSize))
			.addPathPatterns("/file");
	}

}
