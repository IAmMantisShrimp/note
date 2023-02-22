# 方式一

F:\Java\PracticeProject\楠哥\2022毕业设计\mmall\src\main\java\com\southwind\Main.java

```xml
        <!--        导入mybatis-plus依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3.4</version>
        </dependency>

<!--逆向工程-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.0</version>
        </dependency>
```

```java

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");//8.0以下版本驱动
//        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");//8.0以上驱动
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");

        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mmall?useUnicode=true&characterEncoding=utf8&useSSL=false");
        autoGenerator.setDataSource(dataSourceConfig);
        GlobalConfig globalConfig = new GlobalConfig();
//是否自动打开
        globalConfig.setOpen(false);
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setAuthor("admin");
        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);
        PackageConfig packageConfig = new PackageConfig();
//包父路径
        packageConfig.setParent("com.example.component");
//        实体类
        packageConfig.setEntity("entity");
//        mapper类
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        autoGenerator.setPackageInfo(packageConfig);
        StrategyConfig strategyConfig = new StrategyConfig();
//        是否加入lombok
        strategyConfig.setEntityLombokModel(true);
//        开启下划线转驼峰,表名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        字段名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);

//        create_time，update_time这两个字段需要自动填充
        List<TableFill> list = new ArrayList<>();
        TableFill tableFill1 = new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("update_time",FieldFill.INSERT_UPDATE);
        list.add(tableFill1);
        list.add(tableFill2);

        strategyConfig.setTableFillList(list);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}

```

# 方式二:

https://www.cnblogs.com/youngyajun/p/14815788.html#2%E6%95%B4%E5%90%88%E6%96%B9%E5%BC%8F%E4%BD%BF%E7%94%A8

逆向工程项目路径:

F:\Java\Maven\MybatisGenerator

作用:用来生成pojo,mapper文件

记得直接将文件保存到工作项目中

配置myBatisGeneratorConfig.xml文件:



```
        <!-- 指定数据库哪张表需要逆向 -->
<!--        <table schema="数据库名" tableName="表名称"/>-->
	 <table tableName="表名" domainObjectName="java文件类名"/>
```

运行:

![image-20220329174008130](G:\Document\mdNote\Typora\image-20220329174008130.png)

myBatisGeneratorConfig.xml配置信息:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <!--
    1.配置mysql数据库驱动路径，使用本地绝对路径;windows下，绝对路径不建议直接复制鼠标右键-属性-安全下的绝对路径，建议手打
    2.如果已经在maven mybatis-generator-maven-plugin插件添加数据库驱动依赖，此处不需要指定<classPathEntry />了
    -->
    <!--<classPathEntry location="D:\mybatis-generator\mysql-connector-java-5.1.47.jar"/>-->

    <!-- 上下文环境配置 -->
    <context id="Mybatis3Context" targetRuntime="MyBatis3">
        <!-- 指定生成的java文件的编码方式 -->
        <property name="javaFileEncoding" value="UTF-8"/>
        <commentGenerator>
            <!-- 是否去除自动生成的注释;true(是)/false(否) -->
            <property name="suppressAllComments" value="true" />
        </commentGenerator>
        <!--数据库连接的信息：驱动类、连接地址、用户名、密码；注意driverClass的值，mysql驱动8版本的带cj包名称 -->
        <!--                        connectionURL="jdbc:mysql://ip:端口/数据库名称?useSSL=false"-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/logistics?useSSL=false"
                        userId="root"
                        password="123456">
        </jdbcConnection>

        <!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer;为true时把JDBC DECIMAL和
         NUMERIC类型解析为java.math.BigDecimal -->
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!-- 逆向生成的POJO类的配置；targetPackage(包名称)可以指定，也可以不指定；targetProject(逆向生成的文件保存目录) -->
        <javaModelGenerator targetPackage="pojo" targetProject="F:\Java\Maven\MybatisGenerator\src\main\java">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="true"/>
            <!-- 此属性用于选择MyBatis生成器是否添加代码以从数据库返回的字符字段中修剪空白。如果数据库将数据存储在CHAR字段而不是VARCHAR字段中，这将非常有用。如果为true，MyBatis生成器将插入代码来修剪字符字段。可以使用或元素中的trimStrings属性重写。
默认值为false。 -->
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>


        <!-- 逆向生成的xml文件的配置 -->
        <sqlMapGenerator targetPackage="mapper" targetProject="F:\Java\Maven\MybatisGenerator\src\main\java">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <!-- 逆向生成的api接口类(mapper接口)的配置 -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="mapper" targetProject="F:\Java\Maven\MybatisGenerator\src\main\java">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <!-- 指定数据库哪张表需要逆向 -->
<!--        <table schema="" tableName="表名称"/>-->
        <table tableName="t_basicdata" domainObjectName="BasicData"/>
        <table tableName="t_customer" domainObjectName="Customer"/>
        <table tableName="t_menu" domainObjectName="Menu"/>
        <table tableName="t_order" domainObjectName="Order"/>
        <table tableName="t_order_detail" domainObjectName="OrderDetail"/>
        <table tableName="t_role" domainObjectName="Role"/>

      <table tableName="t_user" domainObjectName="User"/>



    </context>
</generatorConfiguration>

```



