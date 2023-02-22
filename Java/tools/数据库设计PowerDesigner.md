## 1.安装

安装包,内有安装教程:

F:\Java\PracticeProject\BoGe\羊城货运系统



报错:

![image-20220407210129775](G:\Document\mdNote\Typora\image-20220407210129775.png)

## 2.E-R图(实物关系图)使用

### new一个model即可

![image-20220329102735230](G:\Document\mdNote\Typora\image-20220329102735230.png)

选择conceptual data

![image-20220329102851855](G:\Document\mdNote\Typora\image-20220329102851855.png)

选择实体图标,创建实体

![image-20220329103257850](G:\Document\mdNote\Typora\image-20220329103257850.png)

![image-20220329103329056](G:\Document\mdNote\Typora\image-20220329103329056.png)

### 编辑实体对象:

![image-20220329103504803](G:\Document\mdNote\Typora\image-20220329103504803.png)

定义其属性信息:

![image-20220329104100267](G:\Document\mdNote\Typora\image-20220329104100267.png)

![image-20220329104221611](G:\Document\mdNote\Typora\image-20220329104221611.png)

 

按照步骤,设置三个表属性

![image-20220329114822923](G:\Document\mdNote\Typora\image-20220329114822923.png)

### 配置实体间的关联关系

首先配置用户和部门的关联关系,他们是1对多的关联关系 

![image-20220329120413937](G:\Document\mdNote\Typora\image-20220329120413937.png)

![image-20220329120631741](G:\Document\mdNote\Typora\image-20220329120631741.png)

然后配置用户和角色的关系:

![image-20220329121025680](G:\Document\mdNote\Typora\image-20220329121025680.png)

### 创建物理模型图

![image-20220329130638311](G:\Document\mdNote\Typora\image-20220329130638311.png)

根据本机安装的数据库选择模型

![image-20220329130926025](G:\Document\mdNote\Typora\image-20220329130926025.png)

生成的模型

![image-20220329131036571](G:\Document\mdNote\Typora\image-20220329131036571.png)

修改自动生成的属性信息

![image-20220329131447146](G:\Document\mdNote\Typora\image-20220329131447146.png)

根据物理模型图生成数据库脚本

![image-20220329131635554](G:\Document\mdNote\Typora\image-20220329131635554.png)

设置保存位置，点击确定保存mysql文件：

![image-20220329132236316](G:\Document\mdNote\Typora\image-20220329132236316.png)

生成文件，如图

![image-20220329132315414](G:\Document\mdNote\Typora\image-20220329132315414.png)







## 生成外键:

1.先不要设置外键

2.将表关系连接好,生成物理关系图后会自动生成外键

![image-20220407224923721](G:\Document\mdNote\Typora\image-20220407224923721.png)