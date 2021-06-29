package org.java.sb_swagger2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的接口配置
 * 
 * @author gxd
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    /** 设置在yml配置是否开启swagger,使用该配置后一定要在yml中设置该属性，否则报错 */
    @Value("${swagger.enabled}")
    private boolean enabled;
    
    /**
     * 创建Swagger2实例Bean
     */
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                //设置不同分组
                .groupName("group1")
                // 是否启用Swagger
                .enable(enabled)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示,select()方法返回一个ApiSelectorBuilder对象，对扫描相关进行配置
                .select()
                /**设置swagger扫描的api范围
                * RequestHandlerSelectors.any()  扫描所有api
                * RequestHandlerSelectors.none()  不扫描任何api
                * RequestHandlerSelectors.withMethodAnnotation( ApiOperation.class)  根据方法注解扫描api
                * RequestHandlerSelectors.withClassAnnotation( RestController.class)  根据类注解扫描api
                * RequestHandlerSelectors.basePackage("org.java.sb_swagger2.controller")  根据包路径扫描api*/
                .apis(RequestHandlerSelectors.basePackage("org.java.sb_swagger2.controller"))
                /**配置对上方接口扫描到的接口进行过滤，有四种过滤方式如下：
                 * PathSelectors.any() // 任何请求都扫描
                 * PathSelectors.none() // 任何请求都不扫描
                 * PathSelectors.regex(final String pathRegex) // 通过正则表达式控制
                 * PathSelectors.ant("/test/**") // 通过ant()控制,例如只扫描test路径前缀的接口

                 * ant path路径表达式写法如下
                 *   ?    匹配任何单字符
                 *   *     匹配0或者任意数量的字符
                 *   **    匹配0或者更多的目录
                 */
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 创建不同名称的Docker分组，groupName()方法中赋予不同的分组名即可
     */
    @Bean
    public Docket createRestApi2()
    {
        return new Docket(DocumentationType.SWAGGER_2)//省略分组信息设置...
                .groupName("group2")
                .select()
                .apis(RequestHandlerSelectors.none())
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * 添加文档信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("标题：SpringBoot整合Swagger2")
                // 描述
                .description("描述：用于XXX,具体包括XXX,XXX模块...")
                // 作者信息
                .contact(new Contact("Java", "http://localhost:9090/swagger-ui.html", "http://localhost:9090/swagger-ui.html"))
                // 版本
                .version("版本号:" + "v1.0")
                //许可证
                .license("MIT")
                //许可证地址
                .licenseUrl("http://localhost:9090/swagger-ui.html")
                //服务条款url
                .termsOfServiceUrl("http://localhost:9090/swagger-ui.html")
                .build();
    }
}
