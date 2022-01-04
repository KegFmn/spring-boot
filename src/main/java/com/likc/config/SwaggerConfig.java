package com.likc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @Author: likc
 * @Date: 2022/01/04/23:28
 * @Description:
 */

@EnableOpenApi
@Configuration
public class SwaggerConfig {

    /**
     *  我们这里选择的参数是 DocumentationType.OAS_30 ,这是一个Swagger实例的接口文档版本
     * @return
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                //.groupName("likc") // 配置Api文档分组
                .enable(true) // enable()是否启用Swagger，默认是true
                .select() // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                // RequestHandlerSelectors,配置要扫描接口的方式
                // basePackage指定要扫描的包
                // any()扫描所有，项目中的所有接口都会被扫描到
                // none()不扫描
                // withClassAnnotation()扫描类上的注解
                // withMethodAnnotation()扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.likc.controller"))
                .paths(PathSelectors.any()) // paths()过滤某个路径
                .build();
    }

    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("likc", "https://www.cnblogs.com/tioxy/", "1369773052@qq.com");

        return new ApiInfo(
                "likc的接口文档", // 标题
                "项目描述", // 描述
                "1.0", // 版本
                "https://www.cnblogs.com/tioxy/", // 组织链接
                contact, // 联系人信息
                "Apache 2.0", // 许可
                "http://www.apache.org/licenses/LICENSE-2.0", // 许可连接
                new ArrayList() // 扩展
        );
    }
}
