# Python环境

## 1.将虚拟环境删除没事

## 2.更改pip默认安装目录

![image-20220408194031975](G:\Document\mdNote\Typora\image-20220408194031975.png)

![image-20220408194058377](G:\Document\mdNote\Typora\image-20220408194058377.png)

```python
ENABLE_USER_SITE = True

# for distutils.commands.install
# These values are initialized by the getuserbase() and getusersitepackages()
# functions, through the main() function when Python starts.
USER_SITE = r"D:\Program Files\python3.9\Lib\site-packages"	
USER_BASE = r"D:\Program Files\python3.9\Scripts"
```

## 3.配置opencv

https://blog.csdn.net/qq_43605229/article/details/114572661

先下载配置文件包

https://www.lfd.uci.edu/~gohlke/pythonlibs/#opencv

对应python的版本,此电脑版本为python39

![image-20220408194852818](G:\Document\mdNote\Typora\image-20220408194852818.png)

下载好后,将压缩文件拷贝到安装位置(哪里都可以其实)

![image-20220408194936552](G:\Document\mdNote\Typora\image-20220408194936552.png)

,在这个位置打开dos界面,

![image-20220408195029667](G:\Document\mdNote\Typora\image-20220408195029667.png)

根据文件名安装即可



## 4.pip安装太慢

pip install -U 包名