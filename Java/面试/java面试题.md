集合,多线程,MySQL优化,优化查询,Mysql索引,B+树,Innodb,my...,一张表可以建16个索引,  事务,四大原则,mvc过程,spring,生命周期,设计模式(除了单例和工厂),

bean的注入过程,并发编程的锁,sec...,lock,,secnized..锁升级过程,乐观锁,线程池.

为什么选redis,不选其他的如mogodb.

数据类型丰富,方便,速度快;

reids速度为什么那么快. 在内存中,

redis持久化.

数据库集群,缓存穿透\击穿

spring优点,ioc,iop



1.









## 1、说出Object类的常用方法？

protected Object clone()//创建并返回此对象的一个副本。
boolean equals(Object obj)//指示其他某个对象是否与此对象“相等”。
String toString()//返回该对象的字符串表示。
void wait()//在其他线程调用此对象的 notify() 方法或 notifyAll() //方法前，导致当前线程等待。
void notify()//唤醒在此对象监视器上等待的单个线程。
void notifyAll()//唤醒在此对象监视器上等待的所有线程。
int hashCode()//返回该对象的哈希码值。
Class<> getClass()//返回此 Object 的运行时类。
protected void finalize()//当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。
复制代码
这里每个方法用来做什么的，务必要搞清楚，比如说如果面试官让你对一个非基本数据类型的对象进行复制，但不能影响到原对象，这里我们要学会使用clone（）方法，如此等等。

## 2、说出一些常用的类，包，接口，常见的runtime exception，请各举5个。

常用的类：基本数据类型的包装类(Integer,Sort,Long,Float,Chart,Double,String),及IO类,Thread
常用的包：java.lang、java.awt、java.io、java.util、java.sql、javax.xml、java.NET
常用的接口： Collection,Set,List、Map、Callable,Runable,Servlet,Document、NodeList、Remote
常见的异常：ArithmeticException （a=5/0即数学运算中除0异常）ClassCastException （强制转换异常）NullPointerException（空指针异常，使用Null时）ArrayIndexOutofBoundsException（数组越界）StringIndexOutBoundsException（指示索引或者为负或者超出字符串大小）IndexOutOfBoundsException（下标越界）NegativeArraySizeException（创建大小为负的数组）IllegalArgumentException（传递非法参数）SecurityException（安全异常）NumberFormatException（数据格式异常，字符串->数字）FileNotFoundException（文件未找到异常）SQLException（操作数据库异常）IOException（I/O输入输出异常）

## 3、说说你常用的数据结构？

首先我们要说的应该是对集合框架的理解，也可以当着面试官写下自己认为的集合框架结构图或者简图之类的，这里的具体的话，自己发挥！

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224173656870.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)



![process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)](https://img-blog.csdnimg.cn/20201224175637444.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)

![img](https://img2020.cnblogs.com/blog/1704945/202012/1704945-20201216230929302-183002047.png)

### 1、其次说说Collection和Collections

Collection是集合类的上级接口，即是java.util下所有集合类的父接口，继承于他的接口主要有List和Set；Collections是针对集合类的一个工具类，提供了一系列静态方法实现对各种集合的搜索、排序、线程安全化等操作。

### 2、List和Set

List和Set是继承自Collection接口，而Map并不是继承自Collection接口；Set里的元素是不能重复的，可以用iterator（）方法来区分重复与否！equals()是判读两个set是否相等， equals()和==方法决定引用值是否指向同一对象equals()在类中被覆盖，为的是当两个分离的对象的内容和类型相配的话，返回真值。

### 3、Collection框架中  实现比较  要  实现comparable/comparator接口。

### 4、ArrayList和Vector的区别：

共同点：这两个类都实现了List接口（List接口继承了Collection接口），他们都是有序集合，即存储在这两个集合中的元素的位置都是有顺序的，相当于一种动态的数组，我们以后可以按位置索引号取出某个元素，并且其中的数据是允许重复的，这是与HashSet之类的集合的最大不同处，HashSet之类的集合不可以按索引号去检索其中的元素，也不允许有重复的元素。
接着说ArrayList与Vector的区别，这主要包括两个方面：.
同步性：Vector是线程安全的，也就是说是它的方法之间是线程同步的，而ArrayList是线程序不安全的，它的方法之间是线程不同步的。如果只有一个线程会访问到集合，那最好是使用ArrayList，因为它不考虑线程安全，效率会高些；如果有多个线程会访问到集合，那最好是使用Vector，因为不需要我们自己再去考虑和编写线程安全的代码。
注意：这里谈到线程安全，同步问题，面试官少不了会多嘴说一句，让你讲讲线程安全是咋回事，如果不考虑，你听到这个问题估计会是一脸懵逼，我当初就是这样子的！所以这里我补充下线程安全的问题： java中的线程安全就是线程同步的意思，就是当一个程序对一个线程安全的方法或者变量进行访问的时候，其他的程序不能再对他进行操作了，必须等到这次访问结束以后才能对这个线程安全的方法进行访问，否则将会造成错误发生；线程安全就是说，如果你的代码所在的进程中有多个线程在同时运行，而这些线程可能会同时运行这段代码。如果每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，就是线程安全的。 线程安全问题都是由全局变量及静态变量引起的，定义在方法内部的局部私有变量是没有线程安全与否一说的。
备注：对于Vector&ArrayList、Hashtable&HashMap，要记住线程安全的问题，记住Vector与Hashtable是旧的，是java一诞生就提供了的，它们是线程安全的，ArrayList与HashMap是java2时才提供的，它们是线程不安全的。所以，我们讲课时先讲老的。
数据增长：ArrayList与Vector都有一个初始的容量大小，当存储进它们里面的元素的个数超过了容量时，就需要增加ArrayList与Vector的存储空间，每次要增加存储空间时，不是只增加一个存储单元，而是增加多个存储单元，每次增加的存储单元的个数在内存空间利用与程序效率之间要取得一定的平衡。Vector默认增长为原来两倍，而ArrayList的增长策略在文档中没有明确规定（从源代码看到的是增长为原来的1.5倍）。ArrayList与Vector都可以设置初始的空间大小，Vector还可以设置增长的空间大小，而ArrayList没有提供设置增长空间的方法。
总结：即Vector增长原来的一倍，ArrayList增加原来的0.5倍。

### 5、ArrayList,Vector, LinkedList的存储性能和特性：

ArrayList和Vector都是使用数组方式存储数据，此数组元素数大于实际存储的数据以便增加和插入元素，它们都允许直接按序号索引元素，但是插入元素要涉及数组元素移动等内存操作，所以索引数据快而插入数据慢，Vector由于使用了synchronized方法（线程安全），通常性能上较ArrayList差，而LinkedList使用双向链表实现存储，按序号索引数据需要进行前向或后向遍历，但是插入数据时只需要记录本项的前后项即可，所以插入速度较快。LinkedList也是线程不安全的，LinkedList提供了一些方法，使得LinkedList可以被当作堆栈和队列来使用。

### 6、List和Map的区别：

一个是存储单列数据的集合，另一个是存储键和值这样的双列数据的集合，List中存储的数据是有顺序，并且允许重复；Map中存储的数据是没有顺序的，其键（key）是不能重复的，它的值（value）是可以有重复的，存值采用 put（key，value）。Map中取值：value=m.get(key)（这个面试官常问，虽然不难，但也得注意）

### 7、HashMap和Hashtable的区别：

HashMap把Hashtable的contains方法去掉了，改成containsvalue和containsKey。因为contains方法容易让人引起误解。
一.历史原因:Hashtable是基于陈旧的Dictionary类的，HashMap是Java 1.2引进的Map接口的一个实现
二.同步性:Hashtable是线程安全的，也就是说是同步的，而HashMap是线程序不安全的，不是同步的
三.值：只有HashMap可以让你将空值作为一个表的条目的key或value，即HashMap允许将null作为一个entry的key或者value，而Hashtable不允许。

## 4、String 和StringBuffer的区别？

答：这个问题相对来说很容易，某宝典上有详细的解说，问到的时候，只要按照某宝典上来发挥就行了！这个问题是杭州某家网络公司问到的。。

String和StringBuffer类，它们可以储存和操作字符串，即包含多个字符的字符数据。这个String类提供了数值不可改变的字符串，而这个StringBuffer类提供的字符串可以进行修改，所以当你知道字符数据要改变的时候你就可以使用StringBuffer。典型地，你可以使用StringBuffers来动态构造字符数据。

另外，String类实现了equals方法，new String(“abc”).equals(new String(“abc”)的结果为true,而StringBuffer没有实现equals方法，所以，new StringBuffer(“abc”).equals(new StringBuffer(“abc”)的结果为false。（String覆盖了equals方法和hashCode方法，而StringBuffer没有覆盖equals方法和hashCode方法，所以，将StringBuffer对象存储进Java集合类中时会出现问题。）

## 5、关于线程的一些问题？

很典型的一个问题，你一般如何实现多线程，这个问题不用多说，很简单，java提供了两种方式，一个是继承Thread类，另一个是实现Runnable接口，由于java不支持多继承，所以在多继承的时候，我们得优先选用 实现 Runnable接口，因为我们可以通过实现接口的办法，间接的实现多继承！

另外还有公司问到，线程之间是通过哪些方法进行通信，这个其实在上面说到Object类的常用方法的时候已经提到过了，主要是三个方法，wait（）、notify（）、notifyAll（）方法，解释的话就自己组织下就行咯，在这之前，有些HR会问你你通常如何启动线程，显而易见，start（）方法！好吧，问到这里，其实线程问题也差不多了，不过有些面试官往往不知从何找话题，于是简单的问道，线程的生命周期，嗯这个只要我们想到OS中的进程就行了，差不多的，创建、就绪、运行、阻塞、消亡！

OK，不，好像还有个问题被问到了，线程池，这个问题我完全不知道，查了百科，也就大致了解了下，所以大家自己看看，我就不组织语言了，最后自己觉得线程中还有一个比较重要的东西，一个就是 interrupt（）方法，一个是currentThread（）方法，具体情况大家自己查看java API文档就好，我就不多解释了！

## 6、说说你常用的数据库？

### 1.用MySQL时，一般用的是哪种存储引擎（Engine）：

InnoDB和MyISAM，其中InnoDB适用频繁维护的。修改 插入等的数据表，MyISAM适合少改写 少插入的读取频繁的表，那么显而易见，我们做开发的，肯定是InnoDB存储引擎用的多了！

### 2.事务是什么东西:

我们首先要了解到的是，事务是并发控制的单位，是用户定义的一个操作序列，要么全做，要么不做，是一个不可分割的（通俗的理解，事务是一组原子操作单元，从数据库角度说，就是一组SQL指令，要么全部执行成功，若因为某个原因其中一条指令执行有错误，则撤销先前执行过的所有指令），主要是为了保证数据的完整性！

### 3.事务的特点:

原子性、一致性、隔离性、持久性！

（如果你想回答的更好那么把各个解释下吧：事务的原子性表示事务执行过程中的任何失败都将导致事务所做的任何修改失效。一致性表示当事务执行失败时，所有被该事务影响的数据都应该恢复到事务执行前的状态。隔离性表示在事务执行过程中对数据的修改，在事务提交之前对其他事务不可见。持久性表示已提交的数据在事务执行失败时，数据的状态都应该正确）那么说了那么多，为什么需要事务呢？之前就说过，为了保证数据的完整性，对的，事务就是为解决数据安全操作提出的，事务控制实际上就是控制数据的安全访问。OK，那么我们就要知道Java事务的类型有三种：JDBC事务、JTA(Java Transaction API)事务、容器事务。好了，那么我们平时一般怎么处理事务呢？spring的事务是通过“声明式事务”的方式对事务进行管理，即在配置文件中进行声明，通过AOP将事务切面切入程序，最大的好处是大大减少了代码量，提高了工作效率。

## 7、sql之left join、right join、inner join

根据字面理解，left join就是返回包括左表中所有记录和右表中联结字段相等的记录，好了面试官会问你，那么如果A表中，有甲丙丁3条记录，B表中有甲乙丙丁4条记录，那么如果条件都满足的情况，A left join B 丙记录是否会被查出，答案是否定的！好了，right join就是和left join 相反的，inner join等值联结 只返回2表中联结字段相等的行！

## 8、sql优化

索引（DB中的索引是某个表中一列或多列值的集合和相应的指向表中物理标识这些值的数据页的逻辑指针）啊，主键（聚集索引）啊：一方面，建立索引提高查询速度；另一方面，把所有需提高查询速度的字段都加入聚集索引。最后啊，在sql中，尽量多的使用commit！

## 9.谈一谈JVM的GC

GC指的是垃圾回收，jvm采用分代收集算法进行垃圾回收

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224174023768.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)

### 1.Minor gc:

当Eden区满的时候，会触发第一次Minor gc，把还活着的对象拷贝到Survivor From( S0)区；当Eden区再次触发Minor gc的时候，会扫描Eden区和From区，对两个区域进行垃圾回收，经过这次回收后还存活的对象，则直接复制到To(S1)区域，并将Eden区和From区清空。
当后续Eden区又发生Minor gc的时候，会对Eden区和To区进行垃圾回收，存活的对象复制到From区，并将Eden区和To区清空
部分对象会在From区域和To区域中复制来复制去，如此交换15次(由JVM参数MaxTenuringThreshold决定，这个参数默认是15)，最终如果还存活，就存入老年代。此过程采用的是复制算法。

### 2.Major gc:

当老年代的内存不足以放下从年轻代而来的对象时，触发Major gc，当触发Major gc时虚拟机暂停工作，因此应该尽量少的触发。

## 10.谈谈final, finally, finalize的区别

final修饰的变量为常量不可以被修改
final修饰的类不能被继承
final修饰的方法不能被重写
finally在处理异常时使用，不论是否有异常总会执行finally代码块
finalize()是一个方法，当对象被垃圾回收时，会自动调用该方法

## 11.Java 关键字volatile 与 synchronized 作用与区别？

在计算机中，cpu和内存的交互最为频繁，相比内存，磁盘读写太慢，内存相当于高速的缓冲区
但是随着cpu的发展，内存的读写速度也远远赶不上cpu。因此cpu厂商在每颗cpu上加上高速缓存，用于缓解这种情况
cpu上加入了高速缓存这样做解决了处理器和内存的矛盾(一快一慢)，但是引来的新的问题 —— 缓存一致性
在多核cpu中，每个处理器都有各自的高速缓存，而主内存却只有一个。
在不同CPU执行的不同线程对同一个变量的缓存值不同。因此，操作系统提供了一些内存屏障以解决这种问题。
用volatile可以解决上面的问题，不同硬件对内存屏障的实现方式不一样。java屏蔽掉这些差异，通过jvm生成内存屏障的指令。
对于读屏障:在指令前插入读屏障，可以让高速缓存中的数据失效，强制从主内存取。 硬件层的内存屏障分为两种：Load Barrier
和 Store Barrier即读屏障和写屏障。【内存屏障是硬件层的】
当我们声明某个变量为volatile修饰时，这个变量就有了线程可见性，一个线程对共享变量做了修改，其他线程可以立即看到更改后的最新数据

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224174057640.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)

Synchronized是加锁，在同一时刻只有一个线程可以执行加锁的代码。

## 17.mybatis是如何工作的？

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224174303714.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)

### 工作方式一：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224174313428.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)

### 工作方式二：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224174321904.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)

## 18.Mybatis动态sql有什么用？执行原理？有哪些动态sql？

作用：可以在编写sql时加入流程控制，比如if choose when foreach 等，便于业务处理
原理：在执行sql前解析标签，最终形成我们要执行的sql语句。
动态sql:

1. if 语句 (简单的条件判断)
2. where (主要是用来简化sql语句中where条件判断的，能智能的处理 and or ,不必担心多余导致语法错误)
3. choose (when,otherwize) ,与 jstl 中的choose 很类似.
4. set (主要用于update语句)
5. trim (在自己包含的内容前加上某些前缀或者后缀)  与java中的trim要区分开
6. foreach (在实现in 语句查询时特别有用)

## 19.线程池的原理及实现

线程池和数据库连接池是一个道理，节省资源开销.

池化技术简单点来说，就是提前保存大量的资源，以备不时之需。在机器资源有限的情况下，使用池化技术可以大大的提高资源的利用率，提升性能等。

直接调用ThreadPoolExecutor构造一个就可以了，

线程池的本质:https://www.cnblogs.com/rinack/p/9888717.html

## 21.使用MyBatis的mapper接口调用时有哪些要求？

1. Mapper.xml文件中的namespace，就是接口的类路径
2. Mapper接口方法名和Mapper.xml中定义的每个SQL的id相同；
3. Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql的parameterType类型相同
4. Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

## 22.Spring的AOP怎么理解的，Spring的IOC怎么理解的？



## 23.谈谈Spring Bean的生命周期？

https://blog.csdn.net/w_linux/article/details/80086950

#### 一:首先你需要知道的知识

在IoC容器启动之后，并不会马上就实例化相应的bean，此时容器仅仅拥有所有对象的BeanDefinition(**BeanDefinition**：是容器依赖某些工具加载的XML配置信息进行解析和分析，并将分析后的信息编组为相应的BeanDefinition)。只有当getBean()调用时才是有可能触发Bean实例化阶段的活动

##### 为什么说有可能触发Bean实例化阶段？

因为当对应某个bean定义的getBean()方法第一次被调用时，不管是显示的还是隐式的，Bean实例化阶段才会被触发，第二次被调用则会直接返回容器缓存的第一次实例化完的对象实例(因为默认是singleton单例，当然，这里的情况prototype类型的bean除外)

### 二、Bean的一生过程

![在这里插入图片描述](https://img-blog.csdnimg.cn/20201224174504791.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ1MjY2OTAw,size_16,color_FFFFFF,t_70)

#### 可以简述为以下九步

- 实例化bean对象(通过构造方法或者工厂方法)
- 设置对象属性(setter等)（依赖注入）
- 如果Bean实现了BeanNameAware接口，工厂调用Bean的setBeanName()方法传递Bean的ID。（和下面的一条均属于检查Aware接口）
- 如果Bean实现了BeanFactoryAware接口，工厂调用setBeanFactory()方法传入工厂自身
- 将Bean实例传递给Bean的前置处理器的postProcessBeforeInitialization(Object bean, String beanname)方法
- 调用Bean的初始化方法
- 将Bean实例传递给Bean的后置处理器的postProcessAfterInitialization(Object bean, String beanname)方法
- 使用Bean
- 容器关闭之前，调用Bean的销毁方法

### 三、Bean的后置处理器

上面bean的一生其实已经算是对bean生命周期很完整的解释了，然而bean的后置处理器，是为了对bean的一个增强

用法:分别在Bean的初始化前后对Bean对象提供自己的实例化逻辑

## 24.Spring事务的实现方式和实现原理？

1.声明式事务，通过xml配置文件实现
2.注解式事务，在需要添加事务的方法上加上@Transactional
通过AOP实现的（面向切面编程）

## 25.SpringMVC怎么和AJAX相互调用的？

在ajax请求的方法上加上注解 @ResponseBody 就可以为ajax返回json字符串

## 26.SpringMVC里面的拦截器了解吗？有几种？分别是怎么实现的？

拦截器不依赖servlet容器,使用反射实现,是AOP的一种实现方式， 拦截所有的controller中的请求
Spring MVC中的拦截器（Interceptor）类似于servlet中的过滤器（Filter），它主要用于拦截用户请求并作相应的处理。例如通过拦截器可以进行权限验证、记录请求信息的日志、判断用户是否登录等。
要使用Spring MVC中的拦截器，就需要对拦截器类进行定义和配置，一般通过通过实现HandlerInterceptor接口，或继承HandlerInterceptor接口的实现类（如HandlerInterceptorAdapter）来定义:

```java
public class Login implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse, Object arg2, Exception arg3) throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {       

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object object) throws Exception {
    	Object obj = request.getSession().getAttribute("user");
    	if(obj==null){
    		 //请求的路径
            String contextPath=request.getContextPath();
            response.sendRedirect(contextPath + "/view/login.jsp");  
            return false;
    	}
       return true;

    }

}
```

上述代码中，自定义拦截器实现了HandlerInterceptor接口，并实现了接口中的三个方法：

- preHandle()方法：该方法会在控制器方法(controller中的方法)调用前执行，其返回值表示是否中断后续操作。当其返回值为true时，表示继续向下执行；当其返回值为false时，会中断后续的所有操作（包括调用下一个拦截器和控制器类中的方法执行等）。
- postHandle()方法：该方法会在控制器方法(controller中的方法)调用之后，且解析视图之前执行。可以通过此方法对请求域中的模型和视图做出进一步的修改。
- afterCompletion()方法：该方法会在整个请求完成，即视图渲染结束之后执行。可以通过此方法实现一些资源清理、记录日志信息等工作。



开发拦截器就像开发servlet或者filter一样，都需要在配置文件(spring-servlet.xml)进行配置，配置代码如下：

```xml
<mvc:interceptors>
		<mvc:interceptor>
		    <!-- 
			 	/是web项目的根目录
          		/*是所有文件夹，不含子文件夹(一级目录)
          		/**的意思是所有文件夹及里面的子文件夹（多级目录）
      			/** 表示拦截所有请求 
      		-->
			<!-- <mvc:mapping path="/**" /> -->
			<!-- /user/** 表示拦截/user/下的所有请求  /user/type/add.do-->
			<!-- <mvc:mapping path="/user/**" /> -->
			<!-- /item/* 表示拦截/item/下的请求  可以拦截/item/add.do  不可以拦截/item/type/add.do -->
			<!-- <mvc:mapping path="/item/*" /> -->
			<mvc:mapping path="/item/addCart.do" />
			<mvc:mapping path="/item/pay.do" />
			<!-- 不进行拦截 -->
			<!-- <mvc:exclude-mapping path="/index.do" /> -->
			<bean class="com.lq.interceptor.Login" />
		</mvc:interceptor>
	</mvc:interceptors>

```

## 27.谈谈Spring MVC 的运行流程？

SpringMVC原理：SpringMVC以请求为驱动，围绕Servlet设计，将请求发给控制器，然后通过模型对象，分派器来展示请求结果视图。其中核心类是DispatcherServlet，它是一个Servlet，顶层是实现的Servlet接口。

执行过程：

![img](https://img-blog.csdnimg.cn/20191206163619302.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVfTXlMWQ==,size_16,color_FFFFFF,t_70)

1. 客户端（浏览器）发送请求，请求提交到DispatcherServlet。
2. DispatcherServlet调用HandlerMapping查询请求信息，找到对应的Controller。
3. DispatcherServlet调用对应Controller
4. Controller会根据请求信息来调用Service，Service会处理相应的业务逻辑。
5. Service处理完业务后，会返回一个ModelAndView对象，Model是返回的数据对象，View是个逻辑上的View。
6. DispatcherServlet调用ViewResolver，ViewResolver 会根据逻辑View查找实际的View。
7. DispaterServlet把返回的Model传给View。
8. 通过View返回处理结果给请求者客户端（浏览器）并显示

## 28.后台怎么接收Json？

1. 通过HttpServletRequest接收
2. 使用@RequestParam
3. 使用@RequestVariable
4. 使用@RequestBody
5. 使用@ModelAttribute

## 29.Java 集合的类型。

![img](https://img-blog.csdnimg.cn/2019120617214993.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xvdmVfTXlMWQ==,size_16,color_FFFFFF,t_70)

## 30.Redis支持的数据类型。

1. String（字符串）
2. hash（哈希）
3. list（列表）
4. set（集合）
5. zset (sorted set：有序集合)

## 31.Maven 是干什么的？

Maven 是项目构建、版本和依赖管理工具。

## 32.什么是分布式系统，分布式系统有什么好处？

分布式系统概念：分布式系统是由一组通过网络进行通信，为了完成共同的任务而协调工作的计算机节点组成的系统。具有高度的内聚性和透明性。

分布式系统的好处/优点：

1. 可靠性高、容错性高
   一台服务器的系统崩溃不会影响到其他服务器的运行。
2. 扩展性好
   分布式系统中可以根据需要增加服务器。
3. 灵活性
   容易添加新的服务。
4. 高性能
   性能比传统架构好，且性价比高
5. 技术多样且开放。

顺便说一下缺点：

1. 架构设计复杂。
2. 管理和运维复杂。
3. 部署复杂。

## 33.java创建对象的方式

### 1.new

用new是最常见的方式，new可以创建一个对象

Student s1 = new Student()

注意：

Student s2 = s1;

这里没有new，所以并没有创建新的对象，s2, s1指向了同一对象

### 2.字面常量对象

如:String s = "afanihao";

则s指向了一个常量对象，这里并没有new，但是右侧有一个对象

### 3.反射

利用反射机制，可以根据 Class得到一个实例

Class cls = Student.class;

Object a = cls.getInstance( ); // 得到一个对象

Student s = (Student) a;

使用 getInstance() 可以创建一个对象

4.最后，还有一种对象，

Class cls = Class.forName ("my.Student")

这里的 cls 也是一个对象，类型为 Class。听起有点别扭。这个对象是存在元究竟 Metaspace里的，和上面的普通对象不一样。

## 34.创建对象,一定会调用构造器吗?

只要新创建实例，直接或间接的new是必须的。

```text
1 明确使用new操作符,调用构造.
2 使用反射,调用构造.
3 使用clone,不调用构造.把原来被克隆的实例变量中的值拷贝到新对象中.
4 使用ObjectInputStream类的getObject()方法反序列化,不调用构造.虚拟机通过从输入流中读入的值来初始实例变量.
解析:
别以为3，4两种就不要new了，clone还是要自己实现的一个方法。反序列化你试试反一个单列模式的类出来，那是不可能的，所以不管怎么样，只要新创建实例，直接或间接的new是必须的。
```

## 35.构造器(构造方法)

### 1.构造方法定义

a.方法名与类名相同

b.在方法名的前面没有返回值类型

c.在方法中不能使用return语句返回一个值,但可以单独写return语句来结束方法.

### 2.this关键字

a.通过this关键字可以明确地去访问一个类的成员变量,解决与局部变量名称冲突问题.

​	如:this.age=age;

b.this关键字调用成员方法,如this.write();

c.构造方法是在实例化对象时被java虚拟机自动调用的,在程序中不能像调用其它方法一样去调用构造方法,但可以在一个构造方法中使用this(arg1,arg2,...)的方式调用其它构造器.

注意:

a.怎能在构造器方法中使用this()调用其它构造方法,不能在成员方法中使用.

b.在构造方法中,使用this调用构造方法的语句必须位于第一行,且只能出现一次.

c.不能在一个类的两个构造方法中互相调用.

### 3.注意

一旦定义了构造方法,系统就不再提供默认的构造方法.

如:当定义了一个有参构造后,没定义无参构造.使用无参构造时会发生编译异常(不存在).

## 36.垃圾回收

在java中,当一个对象称为垃圾后仍会占用内存空间,时间一长,就会导致内存空间的不足.针对这种情况,java中引入了垃圾回收机制.

一个对象成为垃圾后会暂时地保留在内存中,当这样的垃圾堆积到一定程度时,java虚拟机就会启动垃圾回收器将这些垃圾对象从内存中释放,从而使程序获得更多可用的内存空间.出来等待Java虚拟机进行自动垃圾回收外,还可以通过调用System.gc()方法来通知java虚拟机进行垃圾回收.当一个对象在内存中被释放时,它的finalize()方法就会被自动调用,因此可以在类中定义finalize()方法来观察对象何时被释放.

## 37.static

**static 的内存分配**

静态变量属于类，不属于任何独立的对象，所以无需创建类的实例就可以访问静态变量。之所以会产生这样的结果，是因为编译器只为整个类创建了一个静态变量的副本，也就是只分配一个内存空间，虽然有多个实例，但这些实例共享该内存。实例变量则不同，每创建一个对象，都会分配一次内存空间，不同变量的内存相互独立，互不影响，改变 a 对象的实例变量不会影响 b 对象。

1.static只能用来修饰成员变量(类里面的),不能修饰局部变量,否则编译会报错.

```java
public class Student{
    public static int a;//合法,没问题
    public void test(){
        private static int b;//编译不通过,非法
    }
}
```

2.static修饰的方法,即可以用"类名.方法名"的方式调用,也可以用实例化的方法调用(不推荐)

```java
//"类名.方法名"
Student.a;
Student.test();
//实例化
Student student=new Student();
student.a;
student.test();
```

3.在一个静态方法中,只能访问用static修饰的成员,原因在于没有被static修饰的成员需要像创建对象才能访问,而静态方法被调用时可以不创建任何对象.**但是,传入的形参不用是静态的.**

4.静态代码块

当类被加载时,静态代码块会执行,由于类只加载一次,因此静态代码块只执行一次.

5.static方法不能被覆盖，也就是说，这个类的子类，不能有相同名、相同参数的方法；

6.static方法只能访问static方法，不能直接访问非static方法(静态方法中没有隐式this的传递,故不能访问对象的方法)，但非static方法可以访问static方法；

![image-20211208103226258](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20211208103226258.png)

6.static方法是属于整个类的，它在内存中的代码段将随着类的定义而分配和装载。而非static的方法是属于某个对象的方法，在这个对像创建时，在对象的内存中拥有这个方法的专用代码段；

**关于静态变量和静态方法的总结：**

- 一个类的静态方法只能访问静态变量；
- 一个类的静态方法不能够直接调用非静态方法；
- 如访问控制权限允许，静态变量和静态方法也可以通过对象来访问，但是不被推荐；
- 静态方法中不存在当前对象，因而不能使用 this，当然也不能使用 super；
- 静态方法不能被非静态方法覆盖；
- 构造方法不允许声明为 static 的；
- 局部变量不能使用static修饰。

## 38.final

### 1.final变量

final 变量一旦赋值后，不能被重新赋值。被 final 修饰的实例变量必须显式指定初始值。

final 修饰符通常和 static 修饰符一起使用来创建类常量。

```java
public class Test{
  final int value = 10;
  // 下面是声明常量的实例
  public static final int BOXWIDTH = 6;
  static final String TITLE = "Manager";
 
  public void changeValue(){
     value = 12; //将输出一个错误
  }
}
```



| ![image-20211208102430500](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20211208102430500.png) | ![image-20211208100007388](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20211208100007388.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

如图可见:final修饰可以先不赋值,但只能赋值一次,而且赋值得位置必须保证一定会被执行,不然会报编译错误.

static final 修饰得变量要马上赋值.

第11行编译错误,是因为e未找到,可能类加载时不一定按顺序加载,因此e可能会出现未声明,但是可以在静态代码块中初始化.

### 2.final方法

final方法可以被本类重载.

父类中的 final 方法可以被子类继承，但是不能被子类重写。

声明 final 方法的主要目的是防止该方法的内容被修改。

### 3.final类

final 类不能被继承，没有类能够继承 final 类的任何特性。

## 39.abstract

### 1.抽象方法

抽象方法是一种没有任何实现的方法，该方法的具体实现由子类提供。

**抽象方法不能被声明成 final 和 static。**

抽象方法用private修饰可以通过编译,但没有意义,子类不能重写.

任何继承抽象类的子类必须实现父类的所有抽象方法，除非该子类也是抽象类。

**如果一个类包含若干个抽象方法，那么该类必须声明为抽象类。抽象类可以不包含抽象方法。**

抽象方法的声明以分号结尾，例如：**public abstract sample();**。

### 2.抽象类

抽象类**不能用来实例化**对象，**声明抽象类的唯一目的是为了将来对该类进行扩充。**

**一个类不能同时被 abstract 和 final 修饰。如果一个类包含抽象方法，那么该类一定要声明为抽象类，否则将出现编译错误。**

抽象类可以包含抽象方法和非抽象方法。

### 3.接口

如果一个抽象类中得所有方法都是抽象的,则可以将找个类用另外一种方式来定义,即接口.接口是又常量喝抽象方法组成对的特殊类.

## 40.接口

接口并不是类，编写接口的方式和类很相似，但是它们属于不同的概念。类描述对象的属性和方法。接口则包含类要实现的方法。

除非实现接口的类是抽象类(要实现所有的抽象方法)，否则该类要定义接口中的所有方法(接口中所有的方法都是抽象的)。

接口无法被实例化，但是可以被实现。一个实现接口的类，必须实现接口内所描述的所有方法，否则就必须声明为抽象类。另外，在 Java 中，接口类型可用来声明一个变量，他们可以成为一个空指针，或是被绑定在一个以此接口实现的对象。

### 接口与类相似点：

- 一个接口可以有多个方法。
- 接口文件保存在 .java 结尾的文件中，文件名使用接口名。
- 接口的字节码文件保存在 .class 结尾的文件中。
- 接口相应的字节码文件必须在与包名称相匹配的目录结构中。

### 接口与类的区别：

- 接口不能用于实例化对象。
- 接口没有构造方法。
- 接口中所有的方法必须是抽象方法，Java 8 之后 接口中可以使用 default 关键字修饰的非抽象方法。
- 接口不能包含成员变量，除了 static 和 final 变量。
- 接口不是被类继承了，而是要被类实现。
- 接口支持多继承。

**java接口可以多继承。Interface3 Extends Interface0, Interface1, interface……**

**不允许类多重继承的主要原因是，如果A同时继承B和C，而B和C同时有一个D方法，A如何决定该继承那一个呢？**

**但接口不存在这样的问题，接口全都是抽象方法继承谁都无所谓，所以接口可以继承多个接口。**

### 接口特性

- 接口中每一个方法也是隐式抽象的,接口中的方法会被隐式的指定为 **public abstract**（只能是 public abstract，其他修饰符都会报错）。
- 接口中可以含有变量，但是接口中的变量会被隐式的指定为 **public static final** 变量（并且只能是 public，用 private 修饰会报编译错误）。
- 接口中的方法是不能在接口中实现的，只能由实现接口的类来实现接口中的方法。

### 抽象类和接口的区别

- 抽象类中的方法可以有方法体，就是能实现方法的具体功能，但是接口中的方法不行。
-  抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是 **public static final** 类型的。
- 接口中不能含有静态代码块以及静态方法(用 static 修饰的方法)，而抽象类是可以有静态代码块和静态方法。
- 一个类只能继承一个抽象类，而一个类却可以实现多个接口。

### 注意

**注**：JDK 1.8 以后，接口里可以有静态方法和方法体了。

**注**：JDK 1.8 以后，接口允许包含具体实现的方法，该方法称为"默认方法"，默认方法使用 default 关键字修饰。

**注***：JDK 1.9 以后，允许将方法定义为 private，使得某些复用的代码不会把方法暴露出去。

![image-20211208112110002](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20211208112110002.png)

**一个接口能继承另一个接口**，这和类之间的继承比较相似。接口的实现类可以使用两个接口的方法.

```java
public interface Animal {
    public void eat();
}
public interface person extends Animal{
    public void work();
}
```

| ![image-20211208110723943](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20211208110723943.png) | ![](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20211208110404265.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |



## 41.内部类

https://www.cnblogs.com/chenssy/p/3388487.html

### 1.种类

根据内部类的位置,修饰符,定义方式可分为 成员内部类,静态内部类,方法(局部)内部类,匿名内部类

### 2.为什么要使用内部类

使用内部类最吸引人的原因是：每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。

在我们程序设计中有时候会存在一些使用接口很难解决的问题，这个时候我们可以利用内部类提供的、可以继承多个具体的或者抽象的类的能力来解决这些程序设计问题。可以这样说，接口只是解决了部分问题，而内部类使得多重继承的解决方案变得更加完整。

```java
public interface Father {

}

public interface Mother {

}

public class Son implements Father, Mother {

}

public class Daughter implements Father{

    class Mother_ implements Mother{
        
    }
}
```

其实对于这个实例我们确实是看不出来使用内部类存在何种优点，但是如果Father、Mother不是接口，而是抽象类或者具体类呢？这个时候我们就只能使用内部类才能实现多重继承了。

其实使用内部类最大的优点就在于它能够非常好的解决多重继承的问题，但是如果我们不需要解决多重继承问题，那么我们自然可以使用其他的编码方式，但是使用内部类还能够为我们带来如下特性:

   **1、**内部类可以用多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立。

   **2、**在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或者继承同一个类。

   **3、**创建内部类对象的时刻并不依赖于外围类对象的创建。

   **4、**内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体。

   **5、**内部类提供了更好的封装，除了该外围类，其他类都不能访问。

### 3.使用外部类

当我们在创建一个内部类的时候，它无形中就与外围类有了一种联系，依赖于这种联系，它可以无限制地访问外围类的元素。

```java
public class OuterClass {
    private String name ;
    private int age;
    
    public void display(){
        System.out.println("OuterClass...");
    }
    /**省略getter和setter方法**/
    
    public class InnerClass{
        public InnerClass(){
            name = "chenssy";
            age = 23;
        }
        public OuterClass getOuterClass(){
            return OuterClass.this;
        }
        
        public void display(){
            System.out.println("name：" + getName() +"   ;age：" + getAge());
        }
    }
    
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();
        innerClass.getOuterClass().display();
    }
}
--------------
Output：
name：chenssy   ;age：23
OuterClass...
```

在这个应用程序中，我们可以看到内部类InnerClass可以对外围类OuterClass的属性进行无缝的访问，**尽管它是private修饰的**。这是因为当我们在创建某个外围类的内部类对象时，此时内部类对象必定会捕获一个指向那个外围类对象的引用，只要我们在访问外围类的成员时，就会用这个引用来选择外围类的成员。

引用内部类我们需要指明这个对象的类型：OuterClasName.InnerClassName。同时如果我们需要创建某个内部类对象，必须要利用外部类的对象通过.new来创建内部类： OuterClass.InnerClass innerClass = outerClass.new InnerClass();

同时如果我们需要生成对外部类对象的引用，可以使用OuterClassName.this，这样就能够产生一个正确引用外部类的引用了。

### 4.成员内部类

成员内部类也是最普通的内部类，它是外围类的一个成员，所以他是可以无限制的访问外围类的所有 成员属性和方法，尽管是private的，但是外围类要访问内部类的成员属性和方法则需要通过内部类实例来访问。

在成员内部类中要注意两点，**第一：**成员内部类中不能存在任何static的变量和方法；**第二：**成员内部类是依附于外围类的，所以只有先创建了外围类才能够创建内部类。

```java
public class OuterClass {
    private String str;
    
    public void outerDisplay(){
        System.out.println("outerClass...");
    }
    
    public class InnerClass{
        public void innerDisplay(){
            //使用外围内的属性
            str = "chenssy...";
            System.out.println(str);
            //使用外围内的方法
            outerDisplay();
        }
    }
    
    /*推荐使用getxxx()来获取成员内部类，尤其是该内部类的构造函数无参数时 */
    public InnerClass getInnerClass(){
        return new InnerClass();
    }
    
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.getInnerClass();
        inner.innerDisplay();
    }
}
--------------------
chenssy...
outerClass...
```

### 5.局部内部类

有这样一种内部类，它是嵌套在方法和作用于内的，对于这个类的使用主要是应用与解决比较复杂的问题，想创建一个类来辅助我们的解决方案，到那时又不希望这个类是公共可用的，所以就产生了局部内部类，局部内部类和成员内部类一样被编译，只是它的作用域发生了改变，它只能在该方法和属性中被使用，出了该方法和属性就会失效。

```java
//定义在方法里：
public class Parcel5 {
    public Destionation destionation(String str){
        class PDestionation implements Destionation{
            private String label;
            private PDestionation(String whereTo){
                label = whereTo;
            }
            public String readLabel(){
                return label;
            }
        }
        return new PDestionation(str);
    }
    
    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        Destionation d = parcel5.destionation("chenssy");
    }
}
```

```java
// 定义在作用域内:
public class Parcel6 {
    private void internalTracking(boolean b){
        if(b){
            class TrackingSlip{
                private String id;
                TrackingSlip(String s) {
                    id = s;
                }
                String getSlip(){
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("chenssy");
            String string = ts.getSlip();
        }
    }
    
    public void track(){
        internalTracking(true);
    }
    
    public static void main(String[] args) {
        Parcel6 parcel6 = new Parcel6();
        parcel6.track();
    }
}
```

### 6.匿名内部类

在做Swing编程中，我们经常使用这种方式来绑定事件

```java
button2.addActionListener(  
                new ActionListener(){  
                    public void actionPerformed(ActionEvent e) {  
                        System.out.println("你按了按钮二");  
                    }  
                });
```

 我们咋一看可能觉得非常奇怪，因为这个内部类是没有名字的，在看如下这个例子：

```java
public class OuterClass {
    public InnerClass getInnerClass(final int num,String str2){
        return new InnerClass(){
            int number = num + 3;
            public int getNumber(){
                return number;
            }
        };        /* 注意：分号不能省 */
    }
    
    public static void main(String[] args) {
        OuterClass out = new OuterClass();
        InnerClass inner = out.getInnerClass(2, "chenssy");
        System.out.println(inner.getNumber());
    }
}

interface InnerClass {
    int getNumber();
}

----------------
Output:
5
```

这里我们就需要看清几个地方

​    **1、** 匿名内部类是没有访问修饰符的。

​     **2、** new 匿名内部类，这个类首先是要存在的。如果我们将那个InnerClass接口注释掉，就会出现编译出错。

​     **3、** 注意getInnerClass()方法的形参，第一个形参是用final修饰的，而第二个却没有。同时我们也发现第二个形参在匿名内部类中没有使用过，所以当所在方法的形参需要被匿名内部类使用，那么这个形参就必须为final。

​    **4、** 匿名内部类是没有构造方法的。因为它连名字都没有何来构造方法。

### 7.静态内部类

  在java提高篇-----关键字static中提到Static可以修饰成员变量、方法、代码块，其他它还可以修饰内部类，使用static修饰的内部类我们称之为静态内部类，不过我们更喜欢称之为嵌套内部类。静态内部类与非静态内部类之间存在一个最大的区别，我们知道非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围内，但是静态内部类却没有。没有这个引用就意味着：

   **1、** 它的创建是不需要依赖于外围类的。

   **2、** 它不能使用任何外围类的非static成员变量和方法。

```java
public class OuterClass {
    private String sex;
    public static String name = "chenssy";
    
    /**
     *静态内部类
     */
    static class InnerClass1{
        /* 在静态内部类中可以存在静态成员 */
        public static String _name1 = "chenssy_static";
        
        public void display(){
            /* 
             * 静态内部类只能访问外围类的静态成员变量和方法
             * 不能访问外围类的非静态成员变量和方法
             */
            System.out.println("OutClass name :" + name);
        }
    }
    
    /**
     * 非静态内部类
     */
    class InnerClass2{
        /* 非静态内部类中不能存在静态成员 */
        public String _name2 = "chenssy_inner";
        /* 非静态内部类中可以调用外围类的任何成员,不管是静态的还是非静态的 */
        public void display(){
            System.out.println("OuterClass name：" + name);
        }
    }
    
    /**
     * @desc 外围类方法
     * @author chenssy
     * @data 2013-10-25
     * @return void
     */
    public void display(){
        /* 外围类访问静态内部类：内部类. */
        System.out.println(InnerClass1._name1);
        /* 静态内部类 可以直接创建实例不需要依赖于外围类 */
        new InnerClass1().display();
        
        /* 非静态内部的创建需要依赖于外围类 */
        OuterClass.InnerClass2 inner2 = new OuterClass().new InnerClass2();
        /* 方位非静态内部类的成员需要使用非静态内部类的实例 */
        System.out.println(inner2._name2);
        inner2.display();
    }
    
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.display();
    }
}
----------------
Output:
chenssy_static
OutClass name :chenssy
chenssy_inner
OuterClass name：chenssy
```

## 42.String,StringBuffer,StringBuilder

![img](https://www.runoob.com/wp-content/uploads/2013/12/java-string-20201208.png)

### 1.String

#### a.在 Java 中字符串属于对象,每次改变都会生成一个新的对象

#### b.创建

①字符串最简单的方式:    String str = "Runoob";  这里的值是 "**Runoob**"，编译器会使用该值创建一个 String 对象。

②用构造函数创建字符串：String str2=new String("Runoob");

区别:① 创建的字符串存储在公共池中，而 ②创建的字符串对象在堆上：

```java
String s1 = "Runoob";              // String 直接创建
String s2 = "Runoob";              // String 直接创建
String s3 = s1;                    // 相同引用
String s4 = new String("Runoob");   // String 对象创建
String s5 = new String("Runoob");   // String 对象创建
```

![img](https://www.runoob.com/wp-content/uploads/2013/12/java-string-1-2020-12-01.png)

#### c.格式化字符串

我们知道输出格式化数字可以使用 printf() 和 format() 方法。

String 类使用静态方法 format() 返回一个String 对象而不是 PrintStream 对象。

String 类的静态方法 format() 能用来创建可复用的格式化字符串，而不仅仅是用于一次打印输出。

```java
public class MyTest {
    public static void main(String[] args) {
        float floatVar=0.123f;
        int intVar=13;
        String stringVar="jjj";
        //printf返回 PrintStream 对象。用于格式化打印,不能复用,无换行
        System.out.printf("浮点型变量的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                "is %s", floatVar, intVar, stringVar);
        //format返回一个String 对象,可复用
        String fs;
        fs = String.format("浮点型变量的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                " %s", floatVar, intVar, stringVar);
        System.out.println(fs);
    }
}
```

#### d.常用方法

| char charAt(int index)                         | 返回指定索引处的 char 值。                                   |
| :--------------------------------------------- | ------------------------------------------------------------ |
| int compareTo(Object o)                        | 把这个字符串和另一个对象比较。                               |
| int compareToIgnoreCase(String str)            | 按字典顺序比较两个字符串，不考虑大小写。                     |
| boolean endsWith(String suffix)                | 测试此字符串是否以指定的后缀结束。                           |
| boolean equals(Object anObject)                | 将此字符串与指定的对象比较。                                 |
| byte[] getBytes()                              | 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。 |
| int indexOf(String str)                        | 返回指定子字符串在此字符串中第一次出现处的索引。             |
| int lastIndexOf(int ch)                        | 返回指定字符在此字符串中最后一次出现处的索引。               |
| int length()                                   | 返回此字符串的长度。                                         |
| boolean matches(String regex)                  | 告知此字符串是否匹配给定的正则表达式。                       |
| String replace(char oldChar, char newChar)     | 返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。 |
| String[] split(String regex)                   | 根据给定正则表达式的匹配拆分此字符串。                       |
| String substring(int beginIndex, int endIndex) | 返回一个新字符串，它是此字符串的一个子字符串。               |
| String trim()                                  | 返回字符串的副本，忽略前导空白和尾部空白。                   |



### 2.StringBuffer

#### a.介绍

每次都会对 StringBuffer 对象本身进行操作，而不是生成新的对象，所以如果需要对字符串进行修改推荐使用 StringBuffer。而且StringBuffer是线程安全的.

应用程序要求线程安全的情况下，则必须使用 StringBuffer 类。

```java
public class Test{
  public static void main(String args[]){
    StringBuffer sBuffer = new StringBuffer("菜鸟教程官网：");
    sBuffer.append("www");
    sBuffer.append(".runoob");
    sBuffer.append(".com");
    System.out.println(sBuffer);  
  }
}
```

#### b.方法

| 方法                                    | 描述                                                     |
| --------------------------------------- | -------------------------------------------------------- |
| public StringBuffer append(String s)    | 将指定的字符串追加到此字符序列。                         |
| public StringBuffer reverse()           | 将此字符序列用其反转形式取代。                           |
| public delete(int start, int end)       | 移除此序列的子字符串中的字符。                           |
| insert(int offset, String str)          | 将 `str` 参数的字符串插入此序列中                        |
| replace(int start, int end, String str) | 使用给定 `String` 中的字符替换此序列的子字符串中的字符。 |
| int capacity()                          | 返回当前容量。                                           |
| int length()                            | 返回长度（字符数）。                                     |
| String toString()                       | 返回此序列中数据的字符串表示形式。                       |

### 3.StringBuilder

#### a.介绍

StringBuilder 类在 Java 5 中被提出，它和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）。

由于 StringBuilder 相较于 StringBuffer 有速度优势，所以多数情况下建议使用 StringBuilder 类。

```java
public class RunoobTest{
    public static void main(String args[]){
        StringBuilder sb = new StringBuilder(10);
        sb.append("Runoob..");
        System.out.println(sb);  
        sb.append("!");
        System.out.println(sb); 
        sb.insert(8, "Java");
        System.out.println(sb); 
        sb.delete(5,8);
        System.out.println(sb);  
    }
}
```

![img](https://www.runoob.com/wp-content/uploads/2013/12/2021-03-01-java-stringbuffer.svg)

```
public StringBuilder()
```

构造一个字符串构建器，其中不包含任何字符，初始容量为16个字符。

## 43.HashTable和HashMap的区别

### 1.看图

![也许你并不知道的HashMap和HashTable区别](https://resource.shangmayuan.com/droxy-blog/2020/11/29/23c49dbdf0064e258518a9d6aefc27fa-1.jpg)

| ![img](https://images2018.cnblogs.com/blog/1208477/201802/1208477-20180225092123804-252750985.png) | ![img](https://images2018.cnblogs.com/blog/1208477/201802/1208477-20180225092123804-252750985.png) |
| ------------------------------------------------------------ | ------------------------------------------------------------ |

1.Hashtable继承自Dictionary类，而HashMap继承自AbstractMap类。但二者都实现了Map接口。

2.HashMap的初始容量为16，Hashtable初始容量为11，两者的填充因子默认都是0.75。

3.HashMap是非线程安全的，只是用于单线程环境下，多线程环境下可以采用concurrent并发包下的concurrentHashMap。HashTable是线程安全的。

4.HashMap中key和value都允许为null。key为null的键值对永远都放在以table[0]为头结点的链表中。HashTable在遇到null时，会抛出NullPointerException异常。(HashTable设计的比较早,设计者认为null值没有用. 在后来的HashMap设计者,考虑到有时候也会用到null所以就支持null值了.)

5.HashMap仅支持Iterator的遍历方式，Hashtable支持Iterator和Enumeration两种遍历方式。

6.判断是否含有某个键 :

在HashMap 中，null 可以作为键，这样的键只有一个；可以有一个或多个键所对 
应的值为null。当get()方法返回null 值时，既可以表示HashMap 中没有该键，也可 
以表示该键所对应的值为null。因此，在HashMap 中不能用get()方法来判断HashM 
ap 中是否存在某个键，而应该用containsKey()方法来判断。Hashtable 的键值都不能 
为null，所以可以用get()方法来判断是否含有某个键。

## 44.基本数据类型和包装类型的区别

1、包装类是对象，拥有方法和字段，对象的调用都是通过引用对象的地址；基本类型不是 
	   2、包装类型是引用的传递；基本类型是值的传递 
	   3、声明方式不同：
        	基本数据类型不需要new关键字；
        	包装类型需要new在堆内存中进行new来分配内存空间 
	   4、存储位置不同：
        	基本数据类型直接将值保存在值栈中；
        	包装类型是把对象放在堆中，然后通过对象的引用来调用他们 
	   5、初始值不同：
        	int的初始值为 0 、 boolean的初始值为false 
       	 包装类型的初始值为null 
	   6、使用方式不同：
        	基本数据类型直接赋值使用就好；
        	包装类型一般可以放在集合中使用，如 Collection集合中List、Set，以及Map键值对存储。

![img](https://img-blog.csdnimg.cn/20190226164706730.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODIwODAz,size_16,color_FFFFFF,t_70)



## 44.ConcurrentHashMap与HashMap的区别

ConcurrentHashMap与HashMap的区别是：1.基本概念不同；2.底层数据结构不同；3.线程安全属性不同；4.对整个桶数组的处理方式不同。基本概念不同在于，ConcurrentHashMap是一个支持高并发更新与查询的哈希表；而HashMap是基于哈希表的Map接口的实现。

### 1.基本概念不同

ConcurrentHashMap是一个支持高并发更新与查询的哈希表。在保证安全的前提下，进行检索不需要锁定。

HashMap是基于哈希表的Map接口的实现，此实现提供所有可选的映射操作，并允许使用null值和null键。

### 2.底层数据结构不同

HashMap的底层数据结构主要是：数组+链表，确切的说是由链表为元素的数组。

ConcurrentHashMap的底层数据结构是：Segments数组+HashEntry数组+链表。

### 3.线程安全属性不同

ConcurrentHashMap是线程安全的数组，它采用分段锁保证安全性。容器中有多把锁，每一把锁锁一段数据，这样在多线程访问时不同段的数据时，就不会存在锁竞争了，这样便可以有效地提高并发效率。

而HashMap不是线程安全，没有锁机制，在多线程环境下会导致数据覆盖之类的问题，所以在多线程中使用HashMap是会抛出异常的。

### 4.对整个桶数组的处理方式不同

ConcurrentHashMap对整个桶数组进行了分段；而HashMap则没有对整个桶数组进行分段。



