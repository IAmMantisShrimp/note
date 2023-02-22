### 一. Java中的运算符:

#### 1.类型

算术运算符,比较运算符,赋值运算符,逻辑运算符

#### 2.运算符优先级

![image-20211207195513599](C:\Users\皮皮虾\AppData\Roaming\Typora\typora-user-images\image-20211207195513599.png)

#### 3.注意

&和&&都表示与操作,但&会将左右两边都判断,而&&左边是false时,就会跳过右边的表达式,即短路与.

|和||都表示或,但...,||也称短路或.

表达式:	x=10     x=x++ + x-- 结果为21,首先 x++ + 为 10 + 但此时x=11,

即10+11--=21  然后--生效x=10; 最后将21赋值给x

### 2.数组

#### 1.数组元素默认初始化

byte,short,int,long 为 0

float,double 为 0.0

char 为 一个空字符串,即'\u0000'

boolean 为 false

引用数据类型 为 null,表示不引用任何对象.

#### 2.常见异常

int[] num=new int[3];

num[4]  --->> 数组越界异常(运行时):ArrayIndexOutOfBoundsException

num=null; num[1];   -->> 空指针异常(运行时):NullPointerException

#### 3.初始化

int[] arr={1,2,3};

int[] arr= new int[3];

#### 4.二维数组

```text
数据类型[][] 数组名=new 数据类型 [m][n]

或：数据类型 数组名[][]=new 数据类型[m[n]

   数据类型[] 数组名[]=new 数据类型[m][n]
```

```java
    @Test
    public void test()
    {
        int arr[]={12,12};//警告:C-style array declaration of local variable 'arr'
        int[] arr1={12,12};
        int arr2[][]=new int[2][];//C格式,且未给一维数组分配地址,如果直接使用,会出想空指针异常
        arr2[0]=arr;//向一维数组分配地址
        arr2[1]=new int[10];//向一维数组分配地址
        System.out.println(arr[1]);
        System.out.println(arr1[1]);
        System.out.println(arr2[0][1]);
    }
```

## 3.修饰符

```text
修饰符	    当前类	同一包内  子孙类(同一包)  子孙类(不同包)   其他包
public	    √		√			√			√		    √
protected	√		√			√			√(×)		×
default		√		√			√			×			×
private		√		×			×			×			×
```

### 1.默认访问修饰符-不使用任何关键字

使用默认访问修饰符声明的变量和方法，对同一个包内的类是可见的。接口里的变量都隐式声明为 **public static final**,而接口里的方法默认情况下访问权限为 **public**。

### 2.私有访问修饰符-private

所以被声明为 **private** 的方法、变量和构造方法只能被所属类访问，并且类和接口不能声明为 **private**。

声明为私有访问类型的变量只能通过类中**公共的 getter 方法被外部类访问**。

Private 访问修饰符的使用主要用来隐藏类的实现细节和保护类的数据。

### 3.公有访问修饰符-public

被声明为 public 的类、方法、构造方法和接口能够被任何其他类访问。

Java 程序的 main() 方法必须设置成公有的，否则，Java 解释器将不能运行该类。

### 4.受保护的访问修饰符-protected

protected 需要从以下两个点来分析说明：

- **子类与基类在同一包中**：被声明为 protected 的变量、方法和构造器能被同一个包中的任何其他类访问；
- **子类与基类不在同一包中**：那么在子类中，子类实例可以访问其从基类继承而来的 protected 方法，而不能访问基类实例的protected方法。

protected 可以修饰数据成员，构造方法，方法成员，**不能修饰类（内部类除外）**。

### 5.访问控制和继承

请注意以下方法继承的规则：

- 父类中声明为 public 的方法在子类中也必须为 public。
- 父类中声明为 protected 的方法在子类中要么声明为 protected，要么声明为 public，不能声明为 private。
- 父类中声明为 private 的方法，不能够被继承。
