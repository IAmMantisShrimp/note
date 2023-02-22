# IDEA破解

## 1.打开idea64.exe.vmoptions

![image-20220103101821418](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20220103101821418.png)

## 2.输入 

-javaagent:F:\\Environment\\java\\ja-netfilter\\FineAgent.jar

![image-20220103101929048](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20220103101929048.png)

![image-20220103101959225](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20220103101959225.png)

## 3.重启IDEA

## 4.输入激活码

![image-20220103102055233](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20220103102055233.png)

![image-20220103102040525](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20220103102040525.png)

## 5.激活成功

![image-20220103102120803](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20220103102120803.png)



















# IDEA优化

## 1.更改配置

C:\Users\皮皮虾\AppData\Roaming\JetBrains\IntelliJIdea2021.2\idea64.exe.vmoptions

![image-20220412151919159](G:\Document\mdNote\Typora\image-20220412151919159.png)

原来的配置:

```txt
-XX:ReservedCodeCacheSize=512m
-Xmx3072m
-Xms128m
-XX:+UseG1GC
-XX:SoftRefLRUPolicyMSPerMB=50
-XX:CICompilerCount=2
-XX:+HeapDumpOnOutOfMemoryError
-XX:-OmitStackTraceInFastThrow
-ea
-Dsun.io.useCanonCaches=false
-Djdk.http.auth.tunneling.disabledSchemes=""
-Djdk.attach.allowAttachSelf=true
-Djdk.module.illegalAccess.silent=true
-Dkotlinx.coroutines.debug=off
-javaagent:F:\\Environment\\java\\ja-netfilter\\FineAgent.jar
```

修改为:

```txt

-Xms2048m
-Xmx4096m
-Xverify:none
-XX:+DisableExplicitGC
-XX:ReservedCodeCacheSize=720m
-XX:SoftRefLRUPolicyMSPerMB=50
-ea
-Dsun.io.useCanonCaches=false
-Djava.net.preferIPv4Stack=true
-Djdk.http.auth.tunneling.disabledSchemes=""
-XX:+HeapDumpOnOutOfMemoryError
-XX:-OmitStackTraceInFastThrow
# JIT 参数
# 设置用于编译的编译器线程
-XX:CICompilerCount=2
# 开启分层编码
-XX:TieredStopAtLevel=1
# 控制最大数量嵌套调用内存
-XX:MaxInlineLevel=3
# 即时编译的东西
-XX:Tier4MinInvocationThreshold=100000
-XX:Tier4InvocationThreshold=110000
-XX:Tier4CompileThreshold=120000

```

