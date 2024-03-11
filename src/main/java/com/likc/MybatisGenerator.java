package com.likc;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @Author: likc
 * @Date: 2022/02/19/20:36
 * @Description: mybatis-plus代码生成器
 */
public class MybatisGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://192.168.31.30:3306/template_boot?serverTimezone=Asia/Shanghai", "root", "lkc8658759@!")
            .globalConfig(builder -> {
                builder.author("likc") // 设置作者
                        //.enableSwagger() // 开启 swagger 模式
                        .commentDate("yyyy-MM-dd")
                        .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com") // 设置父包名
                        .moduleName("likc")  //设置父包模块名
                        .entity("po")   //entity实体类包名
                        .service("service") //Service 包名
                        .serviceImpl("service.impl") // ***ServiceImpl 包名
                        .mapper("mapper")   //Mapper 包名
                        .xml("mapper.xml")  //Mapper XML 包名
                        .controller("controller") //Controller 包名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper"));
            })
            .strategyConfig(builder -> {
                builder.addInclude("t_role_permission") // 设置需要生成的表名
                        .addTablePrefix("t_", "c_", "m_") // 设置过滤表前缀

                        //Mapper策略
                        .mapperBuilder()
                        .superClass(BaseMapper.class)   //设置父类
                        .formatMapperFileName("%sMapper")   //格式化 mapper 文件名称
                        .enableBaseResultMap() // 启用 BaseResultMap 生成。 // 会在mapper.xml文件生成[通用查询映射结果]配置。
                        .enableBaseColumnList() // 启用 BaseColumnList。  // 会在mapper.xml文件生成[通用查询结果列 ]配置
                        .formatXmlFileName("%sMapper") //格式化 Xml 文件名称

                        // Service策略
                        .serviceBuilder()
                        .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                        .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

                        // 实体类策略
                        .entityBuilder()
                        // .superClass(BaseEntity.class)  // 设置父类。会在生成的实体类名后：extends BaseEntity
                        .enableChainModel() // 开启链式模型。  // 会在实体类前添加 [@Accessors(chain = true)] 注解。用法如 [User user=new User().setAge(31).setName("snzl");]（这是Lombok的注解，需要添加Lombok依赖）
                        .enableLombok() //开启 Lombok  // 会在实体类前添加 [@Getter] 和 [@Setter] 注解。（这是Lombok的注解，需要添加Lombok依赖）
                        //.disableSerialVersionUID()   //不实现 Serializable 接口，不生产 SerialVersionUID (不推荐使用)
                        .enableTableFieldAnnotation() // 开启生成实体时生成字段注解。  // 会在实体类的属性前，添加[@TableField("nickname")]
                        .logicDeleteColumnName("is_deleted")   //逻辑删除字段名
                        .logicDeletePropertyName("isDelete") // 逻辑删除属性名(实体)。 // 会在实体类的该字段属性前加注解[@TableLogic]
                        .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                        .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE)
                        )   //添加表字段填充，"create_time"字段自动填充为插入时间，"update_time"字段自动填充为插入修改时间
                         .idType(IdType.AUTO) // 全局主键类型。  //如果MySQL主键设置为自增，则不需要设置此项。

                        // Controller策略配置
                        .controllerBuilder()
                        .enableHyphenStyle() // 开启驼峰转连字符
                        .enableRestStyle();  //开启生成 @RestController 控制器  // 会在控制类中加[@RestController]注解。

            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}
