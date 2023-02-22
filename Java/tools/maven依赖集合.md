# maven依赖

```xml
<!--        shiro依赖-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.5.3</version>
        </dependency>
            
            
```

```xml
<!--        thymeleaf模板-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

```xml
<!--热启动-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
```

```xml
<!--实体类构建工具-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

```xml
<!--日志启动工具-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```

```xml
<!--        数据库依赖-->

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.49</version>
        </dependency>
```

```xml
        <!--        导入mybatis-plus依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3.4</version>
        </dependency>

```

dubbo:F:\Java\tools\Dubbo\springboot-dubbo-learn-master

```xml
        <!--spring-boot-starter-dubbo-->
        <dependency>
            <groupId>com.gitee.reger</groupId>
            <artifactId>spring-boot-starter-dubbo</artifactId>
            <version>1.1.1</version>
        </dependency>
        <!-- 引入zookeeper，去除其中的log4j，否则会因为日志原因导致堆栈溢出 -->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.10</version>
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-log4j12</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```



rabbitmq

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
```

```xml
<!--引入官方的druid-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.17</version>
        </dependency>
```

缓存redis

F:\Java\SpringBoot\Study\P4AdminTest

```xml
<!--        导入redis依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!--        导入jedis-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
```

逆向工程:

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

build配置:

```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
          <!--<version>2.6.5</version>-->
      </plugin>
    </plugins>
<!--静态资源解析-->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
        </resources>

  </build>
```

spring security

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
          </dependency>
```





# 配置文件

## application.yml

数据库,thymeleaf,mybatis-plus,rabbitmq

```yaml
spring:

  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    # driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/shirostudy?useUnicode=true&characterEncoding=utf8&useSSL=false
    
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    
mybatis-plus:
  mapper-locations: classpath:mapper/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
#    开启驼峰命名
    map-underscore-to-camel-case: true
    #    打印sql语句
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    
  #给项目来个名字
  application:
    name: rabbitmq-consumer
  #配置rabbitMq 服务器
  rabbitmq:
    host: http://192.168.31.147
    port: 15672
    username: root
    password: 123456
  redis:
    host: 127.0.0.1
    port: 6379
    password:
#    client-type: lettuce
    jedis:
      pool:
        max-active: 10
```









## application.properties





# 数据库文件

mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.study.mapper.AccountMap">
<!--   useGeneratedKeys="true" keyProperty="id"
        将自增组件的值重新放回City中,不至于出现返回的City没有id值
-->
<!--    <insert id="insert" useGeneratedKeys="true" keyProperty="id" parameterType="com.hrs.boot.bean.City">-->
<!--        insert into city(`name`,`state`,`country`) values(#{name},#{state},#{country})-->
<!--    </insert>-->
</mapper>
```

代码生成器,相当于逆向工程:

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

代码:

F:\Java\PracticeProject\楠哥\2022毕业设计\mmall\src\main\java\com\southwind\MmallApplication.java

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
