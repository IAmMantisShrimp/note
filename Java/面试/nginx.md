https://blog.csdn.net/qq_58467694/article/details/125191080

# 为什么要使用nginx

- 跨平台、配置简单、反向代理、高并发连接：处理2-3万并发连接数，官方监测能支持5万并发，内存消耗小：开启10个nginx才占150M内存 ，nginx处理静态文件好，耗费内存少

- 而且Nginx内置的健康检查功能：如果有一个服务器宕机，会做一个健康检查，再发送的请求就不会发送到宕机的服务器了。重新将请求提交到其他的节点上。

- 使用Nginx的话还能：

          1、节省宽带：支持GZIP压缩，可以添加浏览器本地缓存
          2、稳定性高：宕机的概率非常小
          3、接收用户请求是异步的

# **为什么Nginx性能这么高？**

因为他的事件处理机制：异步非阻塞事件处理机制：运用了epoll模型，提供了一个队列，排队解决



# ***\*为什么不使用多线程？\**** 

Nginx:采用单线程来异步非阻塞处理请求（管理员可以配置Nginx主进程的工作进程的数量），不会为每个请求分配cpu和内存资源，节省了大量资源，同时也减少了大量的CPU的上下文切换，所以才使得Nginx支持更高的并发。



# **Nginx怎么处理请求的？**

nginx接收一个请求后，首先由listen和server_name指令匹配server模块，再匹配server模块里的location，location就是实际地址

```nginx
server {            						# 第一个Server区块开始，表示一个独立的虚拟主机站点
    listen       80；      					# 提供服务的端口，默认80
    server_name  localhost；       			# 提供服务的域名主机名
    location / {            				# 第一个location区块开始
        root   html；       				# 站点的根目录，相当于Nginx的安装目录
        index  index.html index.htm；      	# 默认的首页文件，多个用空格分开
    }          								
}
```

# **正向代理和反向代理？**

正向代理就是一个人发送一个请求直接就到达了目标的服务器

![img](https://img-blog.csdnimg.cn/b6a4a7f746144f7eac853d3c10a26aeb.png)



反方代理就是请求统一被Nginx接收，nginx反向代理服务器接收到之后，按照一定的规则分发给了后端的业务处理服务器进行处理了

![img](https://img-blog.csdnimg.cn/4fe1b13058d24914884e55a2f40e051d.png)

正向代理服务器代理的是客户端，而反向代理服务器代理的是服务端



# **使用反向代理服务器的优点是什么?**

优点：

1. 占内存小，可实现高并发连接，处理响应快
1. 可实现http服务器、虚拟主机、方向代理、负载均衡
1.  Nginx配置简单
1.  可以不暴露正式的服务器IP地址

缺点：
    动态处理差：nginx处理静态文件好,耗费内存少，但是处理动态页面则很鸡肋，现在一般前端用nginx作为反向代理抗住压力

#     **Nginx应用场景？**

- http服务器。Nginx是一个http服务可以独立提供http服务。可以做网页静态服务器。
- 虚拟主机。可以实现在一台服务器虚拟出多个网站，例如个人网站使用的虚拟机。
- 反向代理，负载均衡。当网站的访问量达到一定程度后，单台服务器不能满足用户的请求时，需要用多台服务器集群可以使用nginx做反向代理。并且多台服务器可以平均分担负载，不会应为某台服务器负载高宕机而某台服务器闲置的情况。
- nginx 中也可以配置安全管理、比如可以使用Nginx搭建API接口网关,对每个接口服务进行拦截。



# 限流怎么做的？

- Nginx限流就是限制用户请求速度，防止服务器受不了
- 限流有3种
  1. 正常限制访问频率（正常流量）
  1. 突发限制访问频率（突发流量）
  1. 限制并发连接数
- Nginx的限流都是基于漏桶流算法，底下会说道什么是桶铜流

##  **实现三种限流算法**

### **正常限制访问频率（正常流量）：**

- 限制一个用户发送的请求，我Nginx多久接收一个请求。
- Nginx中使用ngx_http_limit_req_module模块来限制的访问频率，限制的原理实质是基于漏桶算法原理来实现的。在nginx.conf配置文件中可以使用limit_req_zone命令及limit_req命令限制单个IP的请求处理频率。

```nginx
	#定义限流维度，一个用户一分钟一个请求进来，多余的全部漏掉
	limit_req_zone $binary_remote_addr zone=one:10m rate=1r/m;
 
	#绑定限流维度
	server{
		
		location/seckill.html{
			limit_req zone=zone;	
			proxy_pass http://lj_seckill;
		}
 
	}
```

 1r/s代表1秒一个请求，1r/m一分钟接收一个请求， 如果Nginx这时还有别人的请求没有处理完，Nginx就会拒绝处理该用户请求



### **突发限制访问频率（突发流量）：**

- 限制一个用户发送的请求，我Nginx多久接收一个。
- 上面的配置一定程度可以限制访问频率，但是也存在着一个问题：如果突发流量超出请求被拒绝处理，无法处理活动时候的突发流量，这时候应该如何进一步处理呢？Nginx提供burst参数结合nodelay参数可以解决流量突发的问题，可以设置能处理的超过设置的请求数外能额外处理的请求数。我们可以将之前的例子添加burst参数以及nodelay参数：

```nginx
#定义限流维度，一个用户一分钟一个请求进来，多余的全部漏掉
limit_req_zone $binary_remote_addr zone=one:10m rate=1r/m;
 
#绑定限流维度
server{
	
	location/seckill.html{
		limit_req zone=zone burst=5 nodelay;
		proxy_pass http://lj_seckill;
	}
 
}
```

为什么就多了一个 burst=5 nodelay; 呢，多了这个可以代表Nginx对于一个用户的请求会立即处理前五个，多余的就慢慢来落，没有其他用户的请求我就处理你的，有其他的请求的话我Nginx就漏掉不接受你的请求

### **限制并发连接数**

Nginx中的ngx_http_limit_conn_module模块提供了限制并发连接数的功能，可以使用limit_conn_zone指令以及limit_conn执行进行配置。接下来我们可以通过一个简单的例子来看下：

```nginx
	http {
		limit_conn_zone $binary_remote_addr zone=myip:10m;
		limit_conn_zone $server_name zone=myServerName:10m;
	}
 
    server {
        location / {
            limit_conn myip 10;
            limit_conn myServerName 100;
            rewrite / http://www.lijie.net permanent;
        }
    }
```

上面配置了单个IP同时并发连接数最多只能10个连接，并且设置了整个虚拟服务器同时最大并发数最多只能100个链接。当然，只有当请求的header被服务器处理后，虚拟服务器的连接数才会计数。刚才有提到过Nginx是基于漏桶算法原理实现的，实际上限流一般都是基于漏桶算法和令牌桶算法实现的。



# **为什么要做动静分离？**

- Nginx是当下最热的Web容器，网站优化的重要点在于静态化网站，网站静态化的关键点则是是动静分离，动静分离是让动态网站里的动态网页根据一定规则把不变的资源和经常变的资源区分开来，动静资源做好了拆分以后，我们则根据静态资源的特点将其做缓存操作。
- 让静态的资源只走静态资源服务器，动态的走动态的服务器
- Nginx的静态处理能力很强，但是动态处理能力不足，因此，在企业中常用动静分离技术。
- 对于静态资源比如图片，js，css等文件，我们则在反向代理服务器nginx中进行缓存。这样浏览器在请求一个静态资源时，代理服务器nginx就可以直接处理，无需将请求转发给后端服务器tomcat。
- 若用户请求的动态文件，比如servlet,jsp则转发给Tomcat服务器处理，从而实现动静分离。这也是反向代理服务器的一个重要的作用。

**Nginx怎么做的动静分离？**

只需要指定路径对应的目录。location/可以使用正则表达式匹配。并指定对应的硬盘中的目录。如下：（操作都是在Linux上）

```nginx
 server{
     listen 80;
     server_name ws.licaidie.top;
        
     location /image/ {
         root   /usr/local/static/;
         autoindex on;
    }
}
```

```txt
（1）创建目录

mkdir /usr/local/static/image
（2）进入目录

cd  /usr/local/static/image
（3）放一张照片上去

1.jpg
（4）重启 nginx

sudo nginx -s reload
```

打开浏览器 输入 server_name/image/1.jpg 就可以访问该静态图片了 



# **Nginx负载均衡的算法?策略有哪些?**

- 为了避免服务器崩溃，大家会通过负载均衡的方式来分担服务器压力。将对台服务器组成一个集群，当用户访问时，先访问到一个转发服务器，再由转发服务器将访问分发到压力更小的服务器。
- Nginx负载均衡实现的策略有以下五种：

## **轮询(默认)**

每个请求按时间顺序逐一分配到不同的后端服务器，如果后端某个服务器宕机，能自动剔除故障系统。

```nginx
upstream backserver { 
    server 192.168.0.12; 
    server 192.168.0.13; 
} 
```

## **权重 weight**

weight的值越大分配到的访问概率越高，主要用于后端每台服务器性能不均衡的情况下。其次是为在主从的情况下设置不同的权值，达到合理有效的地利用主机资源。

```nginx
upstream backserver { 
    server 192.168.0.12 weight=2; 
    server 192.168.0.13 weight=8; 
} 
```

- 权重越高，在被访问的概率越大，如上例，分别是20%，80%。

## **ip_hash( IP绑定)**

每个请求按访问IP的哈希结果分配，使来自同一个IP的访客固定访问一台后端服务器，并且可以有效解决动态网页存在的session共享问题

```nginx
upstream backserver { 
    ip_hash; 
    server 192.168.0.12:88; 
    server 192.168.0.13:80; 
} 
```

# **fair(第三方插件)**

- 必须安装upstream_fair模块。
- 对比 weight、ip_hash更加智能的负载均衡算法，fair算法可以根据页面大小和加载时间长短智能地进行负载均衡，响应时间短的优先分配。

```nginx
upstream backserver { 
    server server1; 
    server server2; 
    fair; 
} 
```

# ***\*nginx是如何实现高并发的？\****

```nginx
简单来讲，就是： 异步，非阻塞，使用了epoll和大量的底层代码优化
nginx采用一个master进程，多个woker进程的模式。
1. master进程主要负责收集、分发请求。当一个请求过来时，master拉起一个worker进程负责处理这个请求。
2. master进程也要负责监控woker的状态，保证高可靠性
3. woker进程一般设置为跟cpu核心数一致。nginx的woker进程跟apache不一样。apche的进程在同一时间只能处理一个请求，所以它会开很多个进程，几百甚至几千个。而nginx的woker进程在同一时间可以处理额请求数只受内存限制，因此可以处理多个请求。
```



# ***\*nginx的四大功能是什么？\****

- 正向代理    在客户端(浏览器)配置代理服务器，通过代理服务器进行互联网访问。

- 反向代理    

  我们只需要将请求发送到反向代理服务器，由反向代理服务器去选择目标服务器获取数据后，在返回给客户端

  此时反向代理服务器和目标服务器对外就是一个服务器,暴露的是代理服务器地址，隐藏了真实服务器IP地址。

- 负载均衡    

  单个服务器解决不了，我们增加服务器的数量，然后将请求分发到各个服务器上，将原先请求集中到单个服务器

  上的情况改为将请求分发到多个服务器上，将负载分发到不同的服务器，也就是我们所说的负载均衡。

- 动静分离    为了加快网站的解析速度，可以把动态页面和静态页面由不同的服务器来解析，加快解析速度。降低原来单个服务器的压力









