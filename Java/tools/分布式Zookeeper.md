# 分布式Zookeeper

https://www.cnblogs.com/wwjj4811/p/12963554.html

@Reference:将方法从zookeeper上获取下来

@Service:将方法放到zookeeper上

@EnableDubbo整合了三个注解@EnableDubboConfig、@DubboComponentScan、@EnableDubboLifecycle。

​	@EnableDubboConfig引入类DubboConfigConfigurationRegistrar，将用于解析配置相关的类注册到spring容器；

​	@DubboComponentScan引入类DubboComponentScanRegistrar，用于指定@Service扫描路径；

​	@EnableDubboLifecycle引入类DubboLifecycleComponentRegistrar，注册了两个监听器到spring容器。


## 使用:

1.下载Dubbo Admin(选择下载)

https://github.com/apache/dubbo-admin,点击该网址，下载并解压。

2.解压后，是这样的目录。进入dubbo-admin-ui,命令行运行npm install(前提已经安装node环境)，安装依赖。然后npm run dev,这样前端环境就搭建和运行成功了。

F:\Environment\dubbo-admin-develop

![image-20220316091352835](G:\Document\mdNote\Typora\image-20220316091352835.png)

启动(大概要两分钟):

![image-20220316091752386](G:\Document\mdNote\Typora\image-20220316091752386.png)



2.下载zookeeper

http://archive.apache.org/dist/zookeeper/

https://mirrors.cloud.tencent.com/apache/zookeeper/

选择一个版本相对较新的

3.解压到 F:\Environment\dubbo\zookeeper-3.6.3 下

4.运行F:\Environment\dubbo\zookeeper-3.6.3\apache-zookeeper-3.6.3-bin\bin下的

zkService.cmd

zkCli.cmd

![image-20220316171323776](G:\Document\mdNote\Typora\image-20220316171323776.png)



5.运行程序

F:\Java\Dubbo\springboot-dubbo-learn-master

先运行提供者,在运行客户端
测试地址:http://localhost:8086//hello/tom





完....



SpringBoot项目初始化:

新建一个空项目,在空项目里新建dubbo-service和dubbo-web模块.

![image-20220316103557830](G:\Document\mdNote\Typora\image-20220316103557830.png)

首先将service的依赖注入到web模块的pom配置文件中

这样,web模块就能用service里的文件.

注意:web模块要想用使用service模块里的bean,要修改配置类参数

如,service模块配置类默认代码:

![image-20220316104105942](G:\Document\mdNote\Typora\image-20220316104105942.png)

它,默认扫描的是配置文件所在包及以下即(com.example.dubboservice)

而web模块默认扫描com.example.dubboweb包,当把service模块导入到web模块时,会出现bean注入失败问题.

解决:https://www.cnblogs.com/liyhbk/p/14230975.html

方法一:将默认扫描包路径设为com.example

```java
@SpringBootApplication(scanBasePackages = {"com.example"})
```

方法二:将com.example.dubboservice路径添加到配置类中

```java
@SpringBootApplication(scanBasePackages = {"com.example.dubboservice","com.example.dubboweb"})
```

# Ubuntu下

## 1.安装,

1.即解压就行

tar -zxvf 压缩文件目录 -C 解压到文件的目录

2.将conf下的zoo_sample.cfg文件复制一份,并重命名为zoo.cfg

3.修改zoo.cfg的dataDir=为

/opt/zooKeeper/apache-zookeeper-3.5.6-bin并保存

## 2.服务端命令

打开终端,并定位到/opt/zooKeeper/apache-zookeeper-3.5.6-bin

开启:

sudo ./zkServer.sh start

查看状态:

sudo ./zkServer.sh status

关闭:

sudo ./zkServer.sh stop

![image-20220316192512204](G:\Document\mdNote\Typora\image-20220316192512204.png)

## 2.客户端操作

gec@ubuntu:/opt/zooKeeper/apache-zookeeper-3.5.6-bin/bin$ 

**打开:**

sudo ./zkCli.sh -server localhost:2181

localhost:2181 其中localhost指的是本机ip,2181是zookeeper默认的端口

当连接其它服务器时,localhost要改为相应的ip

连接本机的话,可以直接写 sudo ./zkCli.sh 即可 

![image-20220316192958845](G:\Document\mdNote\Typora\image-20220316192958845.png)

**退出:quit**

目录结构:

![image-20220316193612868](G:\Document\mdNote\Typora\image-20220316193612868.png)

**查看目录: ls /**

查看指定的目录: ls /目录

![image-20220316194055671](G:\Document\mdNote\Typora\image-20220316194055671.png)

**增删改查**



增(持久化节点):create /文件名 内容(可为空)

删:delete /文件名

改:set /文件名 内容

查:get /文件名

![image-20220316194717757](G:\Document\mdNote\Typora\image-20220316194717757.png)

![image-20220316195505739](G:\Document\mdNote\Typora\image-20220316195505739.png)

![image-20220316195716591](G:\Document\mdNote\Typora\image-20220316195716591.png)

如果不知道命令,可以输入help

创建临时节点:create -e /名称 退出时会消失

创建连续节点:create -s /名称  会自动在名称后加十位序号

创建临时连续节点:create -es /名称

![image-20220316201624877](G:\Document\mdNote\Typora\image-20220316201624877.png)





查看一个目录的详细信息: ls -s /目录名





















## JavaAPI操作zookeeper

项目目录:F:\Java\Dubbo\linux\curatorOperate

建立连接,增删改查.

![image-20220317094419707](G:\Document\mdNote\Typora\image-20220317094419707.png)

监听器:ZK服务观察者事件(监听器)当某一个节点的信息被更改,会通知其它节点的订阅者,决定是否要更改相应的信息.

分布式锁:与多线程锁原理相似,对数据进行管制,保证稳定性,采用zookeeper

​		核心思想:当客户端要获取锁,则创建节点,使用完锁,则删除该节点

​			1.客户端获取锁时,在lock(自己取的)节点下创建临时顺序节点(防止宕机等突发情况)

​			2.然后获取到lock下面的所有子节点,客户端获取到所有的子节点之后,如果发现自己创建子节点的序号最小,则认为该客户端获取到了锁.

​			3.如果发现自己创建的节点并非lock所有子节点中最小的,说明自己还没有获取到锁,此时哭护短需要找到比自己小的那个节点,同时对其注册监听器,监听删除事件.

​			4.如果发现比自己小的那个节点被删除,则客户端的Watcher会收到相应的通知,此时再判断自己创建的节点是否是lock子节点中序号最小的,如果是则获取到了锁,如果不是则重复以上步骤

