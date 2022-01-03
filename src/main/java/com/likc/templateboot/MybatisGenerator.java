package com.likc.templateboot;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 *  mybatis-plus代码生成器
 */
public class MybatisGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/templateboot", "root", "root")
            .globalConfig(builder -> {
                builder.author("likc") // 设置作者
                        .enableSwagger() // 开启 swagger 模式
                        .fileOverride() // 覆盖已生成文件
                        .commentDate("yyyy-MM-dd")
                        .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.likc") // 设置父包名
                        .moduleName("templateboot")  //设置模块包名
                        .entity("po")   //pojo 实体类包名
                        .service("service") //Service 包名
                        .serviceImpl("service.impl") // ***ServiceImpl 包名
                        .mapper("mapper")   //Mapper 包名
                        .xml("mapper.xml")  //Mapper XML 包名
                        .controller("controller") //Controller 包名
                        .other("other") //自定义文件包名
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/resources/mapper"));
            })
            .strategyConfig(builder -> {
                builder.addInclude("t_student") // 设置需要生成的表名
                        .addTablePrefix("t_", "c_") // 设置过滤表前缀

                        //Mapper策略
                        .mapperBuilder()
                        .superClass(BaseMapper.class)   //设置父类
                        .formatMapperFileName("%sMapper")   //格式化 mapper 文件名称
                        .enableMapperAnnotation()       //开启 @Mapper 注解
                        .formatXmlFileName("%sXml") //格式化 Xml 文件名称

                        // Service策略
                        .serviceBuilder()
                        .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                        .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

                        // 实体类策略
                        .entityBuilder()
                        .enableLombok() //开启 Lombok
                        .disableSerialVersionUID()  //不实现 Serializable 接口，不生产 SerialVersionUID
                        .logicDeleteColumnName("is_deleted")   //逻辑删除字段名
                        .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                        .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )   //添加表字段填充，"create_time"字段自动填充为插入时间，"update_time"字段自动填充为插入修改时间
                        .enableTableFieldAnnotation() // 开启生成实体时生成字段注解

                        // Controller策略配置
                        .controllerBuilder()
                        .formatFileName("%sController") //格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
                        .enableRestStyle();  //开启生成 @RestController 控制器

            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}
