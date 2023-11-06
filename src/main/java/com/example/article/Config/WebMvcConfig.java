package com.example.article.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  //사용자가 작성한 클래스, 외부 라이브러리를 bean에 등록
//내가 만들었지만 springboot에서 제공하는 것처럼 사용하고 싶을 때
//파일 업로드를 위한 Bean 작업
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")  //application에 선언한 변수값을 읽어올 때
    String uploadPath;
    //resource영역, 즉, 파일 저장경로 등을 변경하고 싶을 때 해당 메소드 오버라이딩
    //resource 정보를 사용자가 추가
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);

        /*registry.addResourceHandler("/css/")
                .addResourceLocations("file:///c:/css/");*/
        //이런 식으로 css나 javascript도 추가 가능
    }
    //resource/static/images... 폴더를 file:///c:/image 폴더로 대체하겠다는 뜻
    // ** : 폴더 안에 있는 모든 것(폴더포함)
    //addResourceHandler : 접근할 주소 /(반드시 표기) 접근할 폴더명/**(모든 것을 사용)
}
