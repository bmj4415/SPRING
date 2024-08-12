package com.yedam.app.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {//부트가 가지고 있는 기본 설정을 우리가 Overide하겠다
//인터페이스 내부에 추상메소드 말고 일반 메소드도 가능하도록 되어서(jdk21) 강제성 없이 원하는 부분들만 구현할 수 있게 되었음.
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	//리소스 핸들링(static 폴더의 하위파일명과 중복되지 않도록 주의)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") // URL, 반드시 application.properties에 경로 끝이 /로 끝나야함(D:/upload/).
				.addResourceLocations("file:///" + uploadPath, ""); //해당 location에 접근할 수 있는 경로(실제경로)
																 	//경로는 같은데 폴더를 다르게하고 싶다면 , ""로 추가 가능
	} 

}
