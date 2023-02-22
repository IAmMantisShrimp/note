# 1.安装

https://blog.csdn.net/babyxue/article/details/80970526

ISO下载地址:[Index of /centos/7/isos/x86_64/](http://mirrors.sohu.com/centos/7/isos/x86_64/)

http://mirrors.sohu.com/centos/7/isos/x86_64/

本地ISO文件:E:\install_bag\ISO

# 2.操作

登录:账户:sheng

密码:123456

192.168.121.2



切换到root用su -

首先查看IP:

![image-20220323143645999](G:\Document\mdNote\Typora\image-20220323143645999.png)

再更改IP(管理员):

vi   /etc/sysconfig/network-scripts/ifcfg-ens33

```java
TYPE="Ethernet"   # 网络类型为以太网
BOOTPROTO="static"  # 手动分配ip
NAME="ens33"  # 网卡设备名，设备名一定要跟文件名一致
DEVICE="ens33"  # 网卡设备名，设备名一定要跟文件名一致
ONBOOT="yes"  # 该网卡是否随网络服务启动
IPADDR="192.168.220.101"  # 该网卡ip地址就是你要配置的固定IP，如果你要用xshell等工具连接，220这个网段最好和你自己的电脑网段一致，否则有可能用xshell连接失败
GATEWAY="192.168.220.2"   # 网关
NETMASK="255.255.255.0"   # 子网掩码
DNS1="8.8.8.8"    # DNS，8.8.8.8为Google提供的免费DNS服务器的IP地址


```

需要和原来的不同

# 3.上传文件

使用winscp

1.连接,使用ip连接

![image-20220323173100203](G:\Document\mdNote\Typora\image-20220323173100203.png)

![image-20220323173150476](G:\Document\mdNote\Typora\image-20220323173150476.png)



![image-20220323173213309](G:\Document\mdNote\Typora\image-20220323173213309.png)即可传输文件

# 4.安装JDK

https://blog.csdn.net/pang_ping/article/details/80570011

jdk位置:

```java
export JAVA_HOME=/usr/java/jdk1.8.0_162
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
```

有个小问题:

![image-20220323175307361](G:\Document\mdNote\Typora\image-20220323175307361.png)

mkdir:新建一个文件夹

解压:tar zxvf 压缩包名称 

删除:rm -f 文件名

编辑文件:编辑命令：vi 文件路径

编辑时撤销,先按ESC,再按U

让一个文件生效:source 文件路径及名称

# 5.安装RabbitMQ

所有资料在这里:F:\Java\MQ\RabbitMQ\ShanGuiGu\资料

![image-20220323194118883](G:\Document\mdNote\Typora\image-20220323194118883.png)



先到/usr/local/software目录下

安装:

```txt
rpm -ivh erlang-21.3-1.el7.x86_64.rpm
yum install socat -y
rpm -ivh rabbitmq-server-3.8.8-1.el7.noarch.rpm
```

操作:

```txt
添加开机启动 RabbitMQ 服务
chkconfig rabbitmq-server on
启动服务
/sbin/service rabbitmq-server start
查看服务状态
/sbin/service rabbitmq-server status
停止服务(选择执行)
/sbin/service rabbitmq-server stop
开启 web 管理插件
rabbitmq-plugins enable rabbitmq_management
```

安装插件:

地址IP和端口: 如

![image-20220323194819336](G:\Document\mdNote\Typora\image-20220323194819336.png)

加默认端口号:15672

http://192.168.31.147:15672/

复制到浏览器(如果打不开就先把防火墙关闭):

![image-20220323194900305](G:\Document\mdNote\Typora\image-20220323194900305.png)

暂时还进不去,要在MQ上添加一个账户:

```txt
创建账号:用户名root,密码123456
rabbitmqctl add_user root 123456
设置用户角色
rabbitmqctl set_user_tags root administrator
设置用户权限
set_permissions [-p <vhostpath>] <user> <conf> <write> <read>
rabbitmqctl set_permissions -p "/" root ".*" ".*" ".*"
用户 user_root具有/vhost1 这个 virtual host 中所有资源的配置、写、读权限
查看当前用户和角色
rabbitmqctl list_users  
```

创建完成后就可以打开后台管理界面:![image-20220323195852346](G:\Document\mdNote\Typora\image-20220323195852346.png)

查看rabbitmq的所有命令

rabbitmqmct help

添加一个虚拟主机:/ems

![image-20220324133106746](G:\Document\mdNote\Typora\image-20220324133106746.png)

