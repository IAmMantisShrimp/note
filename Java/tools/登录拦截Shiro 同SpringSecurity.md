# Shiro

什么是 Shiro

官网：http://shiro.apache.org/

是一款主流的 Java 安全框架，不依赖任何容器，可以运行在 Java SE 和 Java EE 项目中，它的主要作用是对访问系统的用户进行身份认证、授权、会话管理、加密等操作。

Shiro 就是用来解决安全管理的系统化框架。



# Shiro 核心组件

用户、角色、权限

会给角色赋予权限，给用户赋予角色

1、UsernamePasswordToken，Shiro 用来封装用户登录信息，使用用户的登录信息来创建令牌 Token。

2、SecurityManager，Shiro 的核心部分，负责安全认证和授权。

3、Suject，Shiro 的一个抽象概念，包含了用户信息。

4、Realm，开发者自定义的模块，根据项目的需求，验证和授权的逻辑全部写在 Realm 中。

5、AuthenticationInfo，用户的角色信息集合，认证时使用。

6、AuthorzationInfo，角色的权限信息集合，授权时使用。

7、DefaultWebSecurityManager，安全管理器，开发者自定义的 Realm 需要注入到 DefaultWebSecurityManager 进行管理才能生效。

8、ShiroFilterFactoryBean，过滤器工厂，Shiro 的基本运行机制是开发者定制规则，Shiro 去执行，具体的执行操作就是由 ShiroFilterFactoryBean 创建的一个个 Filter 对象来完成。

Shiro 的运行机制如下图所示。





# Spring Boot 整合 Shiro

1、创建 Spring Boot 应用，集成 Shiro 及相关组件，pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-spring</artifactId>
        <version>1.5.3</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.3.1.tmp</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

2、自定义 Shiro 过滤器

```java
public class AccoutRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Account account = accountService.findByUsername(token.getUsername());
        if(account != null){
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }
        return null;
    }
}
```

3、配置类

```java
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        return factoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accoutRealm") AccoutRealm accoutRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(accoutRealm);
        return manager;
    }

    @Bean
    public AccoutRealm accoutRealm(){
        return new AccoutRealm();
    }
}
```

编写认证和授权规则：

> 认证过滤器

anon：无需认证。

authc：必须认证。

authcBasic：需要通过 HTTPBasic 认证。

user：不一定通过认证，只要曾经被 Shiro 记录即可，比如：记住我。



> 授权过滤器

perms：必须拥有某个权限才能访问。

role：必须拥有某个角色才能访问。

port：请求的端口必须是指定值才可以。

rest：请求必须基于 RESTful，POST、PUT、GET、DELETE。

ssl：必须是安全的 URL 请求，协议 HTTPS。



创建 3 个页面，main.html、manage.html、administrator.html

访问权限如下：

1、必须登录才能访问 main.html

2、当前用户必须拥有 manage 授权才能访问 manage.html

3、当前用户必须拥有 administrator 角色才能访问 administrator.html



> Shiro 整合 Thymeleaf

1、pom.xml 引入依赖

```xml
<dependency>
    <groupId>com.github.theborakompanioni</groupId>
    <artifactId>thymeleaf-extras-shiro</artifactId>
    <version>2.0.0</version>
</dependency>
```

2、配置类添加 ShiroDialect

```java
@Bean
public ShiroDialect shiroDialect(){
    return new ShiroDialect();
}
```

3、index.html

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.thymeleaf.org/thymeleaf-extras-shiro">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="shortcut icon" href="#"/>
</head>
<body>
    <h1>index</h1>
    <div th:if="${session.account != null}">
        <span th:text="${session.account.username}+'欢迎回来！'"></span><a href="/logout">退出</a>
    </div>
    <a href="/main">main</a> <br/>
    <div shiro:hasPermission="manage">
        <a href="manage">manage</a> <br/>
    </div>
    <div shiro:hasRole="administrator">
        <a href="/administrator">administrator</a>
    </div>
</body>
</html>
```

# 总结,步骤

项目地址:

F:\Java\tools\Shiro\NanGeStudy\Study

## 1.新建一个springboot项目

,选取web,devtools,thymeleaf场景启动器.

然后导入shiro,mysql,mybatis plus依赖

pom.xml

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

        <!--        导入mybatis-plus依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3.4</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
<!--        shiro依赖-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.5.3</version>
        </dependency>
```

## 2.配置数据库连接和thymeleaf:

application.yaml

```yaml
#Mysql5配置文件
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/shirostudy?useUnicode=true&characterEncoding=utf8&useSSL=false
#    url: jdbc:mysql://localhost:3306/customproject
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
  jdbc:
    template:
      query-timeout: 5
#Mysql6以上配置文件
#spring:
#  datasource:
#    driver-class-name: com.mysql.cj.jdbc.Driver
#    url: jdbc:mysql://localhost:3306/ceshi?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
#    username: root
#    password: root
mybatis:
#  config文件和configuration配置只能存在一种
#  config-location: classpath:mybatis/mybatis-config.xml
  mapper-locations: classpath:mapper/*.xml
  configuration:
#    开启驼峰命名
    map-underscore-to-camel-case: true
```

## 3.测试数据库

首先,建表

![image-20220330213446439](G:\Document\mdNote\Typora\image-20220330213446439.png)

新建对应实体类

```java
import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
```

编写操作数据库的接口

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.study.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}

```

测试

```java
@SpringBootTest
class StudyApplicationTests {
    @Autowired
    private AccountMapper accountM;
    @Test
    void contextLoads() {
        accountM.selectList(null).forEach(System.out::println);

    }
}
```

## 3.编写service层代码

返回查询到的结果:

```java
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        //条件查询,通过用户名查询
        wrapper.eq("username",username);
        return accountMapper.selectOne(wrapper);
    }
```

## 4.编写自己的Realm类

使其继承AuthorizingRealm,并实现两个方法(登录和认证)

```java
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private AccountService accountService;
    /**授权,登入
     * AuthorzationInfo，角色的权限信息集合，授权时使用。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
        info.addStringPermission(account.getPerms());
        return info;
    }

    /**认证
     * AuthenticationInfo，用户的角色信息集合，认证时使用。
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken是前端传过来封装好的的账户密码,需要和数据库的账户密码进行对比
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        Account account = accountService.findByUsername(token.getUsername());
        if (account!=null){
            //与正确密码进行对比
            return new SimpleAuthenticationInfo(account,account.getPassword(),getName());
        }
        return null;
    }
}
```



## 5.编写配置类

里面配置需要拦截的页面

![](G:\Document\mdNote\Typora\image-20220330185611923.png)

```java
@Configuration
public class ShiroConfig {

    @Bean
    public AccountRealm accountRealm(){
        return new 
            ();
    }
    /**
     * 从IOC容器中取出public AccountRealm accountRealm()返回的对象,并作为参数,再放入容器中
     * @Qualifier("accountRealm") 找到名字为accountRealm的bean
     * @param accountRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        //注入Realm
        manager.setRealm(accountRealm);
        return manager;
    }

    /**
     * 需要传入DefaultWebSecurityManager对象
     * ShiroFilterFactoryBean，过滤器工厂，Shiro 的基本运行机制是开发者定制规则，Shiro 去执行，具体的执行操作就是由 ShiroFilterFactoryBean 创建的一个个 Filter 对象来完成。
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //权限设置
        Map<String,String> map = new Hashtable<>();
        /*认证过滤器
        anon：无需认证。
        authc：必须认证。
        authcBasic：需要通过 HTTPBasic 认证。
        user：不一定通过认证，只要曾经被 Shiro 记录即可，比如：记住我。
         */
        //设置main页面需要登录(认证)才能请求
        map.put("/main","authc");


        /**
         * perms：必须拥有某个权限才能访问。
         * role：必须拥有某个角色才能访问。
         * port：请求的端口必须是指定值才可以。
         * rest：请求必须基于 RESTful，POST、PUT、GET、DELETE。
         * ssl：必须是安全的 URL 请求，协议 HTTPS。
         */
        //设置manage页面需要授予manage权限才能访问
        map.put("/manage","perms[manage]");
        //设置administrator页面需要administrator身份才能访问
        map.put("/administrator","roles[administrator]");
        factoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面
        factoryBean.setLoginUrl("/login");
        //设置未授权页面
        factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
    }
}
```

## 6.在controller层应shiro

```java
    @PostMapping("/login")
    public String login(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
//        将用户密码封装到UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
//            尝试登录
            subject.login(token);
//            登录成功,获取账户身份
            Account account = (Account)  subject.getPrincipal();
//            将用户身份放入到session中
            subject.getSession().setAttribute("account",account);
            return "index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("msg","用户名错误！");
            return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            e.printStackTrace();
            return "login";
        }
    }
```

