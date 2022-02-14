package com.likc.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
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
 * @Description: Swagger3配置类
 */

@EnableKnife4j // Knife4j 增强版访问地址: http://localhost:8088/doc.html。
@EnableOpenApi // Swagger3 访问地址: http://localhost:8082/swagger-ui/#/
@Configuration
public class SwaggerConfig {

    /**
     *  我们这里选择的参数是 DocumentationType.OAS_30 ,这是一个Swagger实例的接口文档版本
     * @return
     */
    @Bean
    public Docket docket(Environment environment){
        //设置要配置的Swagger环境
        Profiles profiles = Profiles.of("dev");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                //.groupName("likc") // 配置Api文档分组
                .enable(flag) // enable()是否启用Swagger，默认是true
                // RequestHandlerSelectors,配置要扫描接口的方式
                // basePackage指定要扫描的包
                // any()扫描所有，项目中的所有接口都会被扫描到
                // none()不扫描
                // withClassAnnotation()扫描类上的注解
                // withMethodAnnotation()扫描方法上的注解
                .select() // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                /**
                 * apis():指定扫描的接口
                 *  RequestHandlerSelectors:配置要扫描接口的方式
                 *       basePackage:指定要扫描的包
                 *       any:扫面全部
                 *       none:不扫描
                 *       withClassAnnotation:扫描类上的注解(参数是类上注解的class对象)
                 *       withMethodAnnotation:扫描方法上的注解(参数是方法上的注解的class对象)
                 */
                .apis(RequestHandlerSelectors.basePackage("com.likc.controller"))
                /**
                 * paths():过滤路径
                 *  PathSelectors:配置过滤的路径
                 *      any:过滤全部路径
                 *      none:不过滤路径
                 *      ant:过滤指定路径:按照按照Spring的AntPathMatcher提供的match方法进行匹配
                 *      regex:过滤指定路径:按照String的matches方法进行匹配
                 */
                .paths(PathSelectors.any()) // paths()过滤某个路径
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "likc的文档", // 项目
                "likc的的Swagger API文档", // 描述
                "1.0", // 版本
                "https://blog.csdn.net/weixin_47357245?spm=1000.2115.3001.5343", // 团队信息
                new Contact("likc", "https://blog.csdn.net/weixin_47357245?spm=1000.2115.3001.5343", "1165624857@qq.com"), // 作者信息
                "Apache 2.0", // 许可
                "http://www.apache.org/licenses/LICENSE-2.0", // 许可连接
                new ArrayList() // 扩展
        );
    }
}
