package org.huaqiu.config;

import lombok.extern.slf4j.Slf4j;
import org.huaqiu.interceptor.JwtTokenAdminInterceptor;
import org.huaqiu.properties.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {


    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    /**
     * 对 /admin/** 下的所有接口启用 JWT 校验拦截器，但 /admin/employee/login 接口除外。
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");

    }

    /**
     * 默认的文档
     * @return
     */
    @Bean
    public Docket docket(){
        ApiInfo apiInfo = new ApiInfoBuilder().title("苍穹外卖项目接口文档").version("2.0").description("苍穹外卖项目接口文档").build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.huaqiu.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;

    }

    /**
     * 设置管理端客户接口
     * @return
     */
    @Bean
    public Docket docket1(){
        ApiInfo apiInfo = new ApiInfoBuilder().title("苍穹外卖项目接口文档").version("2.0").description("苍穹外卖项目接口文档").build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .groupName("管理端接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.huaqiu.controller.admin"))
                .paths(PathSelectors.any())
                .build();
        return docket;

    }

    /**
     * 设置客户端接口文档
     * @return
     */
//    @Bean
//    public Docket docket2(){
//        ApiInfo apiInfo = new ApiInfoBuilder().title("苍穹外卖项目接口文档").version("2.0").description("苍穹外卖项目接口文档").build();
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
//                .groupName("用户端接口")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.huaqiu.controller.user"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//
//    }

    /**
     * 设置静态资源映射
     *
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
