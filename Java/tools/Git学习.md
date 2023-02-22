# Git学习

工作区-->暂存区-->本地库-->网络库

## 1.Git下载

地址,阿里镜像:https://registry.npmmirror.com/binary.html?path=git-for-windows/v2.35.1.windows.2/

本地安装包:F:\Java\Git\Git\尚硅谷Git、GitHub、Gitee码云、GitLab（IDEA版本）\资料

安装步骤:F:\Java\Git\Git\尚硅谷Git、GitHub、Gitee码云、GitLab（IDEA版本）\笔记\尚硅谷技术课程系列之Git V2.0.pdf

## 2.Git常用命令

**命令名称** 				**作用**

git config --global user.name 用户名 

设置用户签名

git config --global user.email 邮箱 

设置用户签名

git init 

初始化本地库

git status 

查看本地库状态

git add 文件名 

添加到暂存区

git commit -m "日志信息" 文件名 

提交到本地库

git reflog 

查看历史记录

git reset --hard 版本号 

版本穿梭

## 3.设置用户名和邮箱

设置:

![image-20220228113909406](G:\Document\mdNote\Typora\image-20220228113909406.png)

皮皮虾@DESKTOP-IO48VPB MINGW64 ~/Desktop
$ git config --global user.name BigBruck

皮皮虾@DESKTOP-IO48VPB MINGW64 ~/Desktop
$ git config --global user.email 401968751@qq.com

查看设置结果:C:\Users\皮皮虾\.gitconfig

## 4.初始化本地库

注意:打开文件夹再git init

f/Java/Git/git-demo

```cmd

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo
$ git init
Initialized empty Git repository in F:/Java/Git/git-demo/.git/

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ ll -a
total 4
drwxr-xr-x 1 皮皮虾 197121 0 Feb 28 11:51 ./
drwxr-xr-x 1 皮皮虾 197121 0 Feb 28 11:51 ../
drwxr-xr-x 1 皮皮虾 197121 0 Feb 28 11:51 .git/

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$

```

## 5.查看本地库状态,并添加文件

**git status**

### **5.1** **首次查看（工作区没有任何文件）**

```cmd
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git status
On branch master

No commits yet

nothing to commit (create/copy files and use "git add" to track)

```

### **5.2** **新增文件（****hello.txt****）**

vim操作, yy复制, p粘贴, 退出编辑模式esc键, :wq保存

ll 查看

cat hello.txt 查看文件内容

```cmd
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ vim hello.txt

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ ll
total 1
-rw-r--r-- 1 皮皮虾 197121 28 Feb 28 12:33 hello.txt

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ cat hello.txt
sdjfhkasjdfhjhdsf
hello.txt

```





### **5.3** **再次查看（检测到未追踪的文件）**

只存在于工作区

![image-20220228124110988](G:\Document\mdNote\Typora\image-20220228124110988.png)

## **6** 添加暂存区

### **6.1** **将工作区的文件添加到暂存区**

**1****）基本语法**

**git** **add** **文件名**

**2****）案例实操**

```cmd
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git add hello.txt
warning: LF will be replaced by CRLF in hello.txt.
The file will have its original line endings in your working directory

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git status
On branch master

No commits yet

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   hello.txt

```

![image-20220228124334752](G:\Document\mdNote\Typora\image-20220228124334752.png)

再次查看时,暂存区有新文件

## 7.提交到本地库

**7.1** **将暂存区的文件提交到本地库**

**1**）基本语法

**git** **commit** **-m "**日志信息" **文件名**

**2）案例实操**

git commit -m "my first commit" hello.txt

![image-20220228124917550](G:\Document\mdNote\Typora\image-20220228124917550.png)

```cmd
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git commit -m "my first commit" hello.txt
warning: LF will be replaced by CRLF in hello.txt.
The file will have its original line endings in your working directory
[master (root-commit) 8c6be70] my first commit
 1 file changed, 2 insertions(+)
 create mode 100644 hello.txt

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git status
On branch master
nothing to commit, working tree clean

```

再次查看没有文件需要提交

## 8.修改文件(hello.txt)

### 8.1修改

```cmd
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ vim hello.txt
```

![image-20220228125153254](G:\Document\mdNote\Typora\image-20220228125153254.png)

### 8.2 **查看状态,检测到工作区有文件被修改**

![image-20220228125329721](G:\Document\mdNote\Typora\image-20220228125329721.png)

### 8.3 将修改的文件再次添加到暂存区

### **8.4查看状态（工作区的修改添加到了暂存区)**

![image-20220228125543642](G:\Document\mdNote\Typora\image-20220228125543642.png)

### **8.5 提交到本地库**

```cmd
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git commit -m "my second commit" hello.txt
[master 23279eb] my second commit
 1 file changed, 5 insertions(+), 1 deletion(-)

```



## 9.历史版本

### 9.1 查看历史版本

**1）基本语法**

**git reflog 查看版本信息**

**git log** **查看版本详细信息**

**2）案例实操**

```cmd

皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git reflog
23279eb (HEAD -> master) HEAD@{0}: commit: my second commit
8c6be70 HEAD@{1}: commit (initial): my first commit


```

### 9.2 版本穿梭

**1）基本语法**

**git reset --hard** **版本号**

**2）案例实操**

```cmd
--首先查看当前的历史记录，可以看到当前是在 23279eb 这个版本
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git reflog
23279eb (HEAD -> master) HEAD@{0}: commit: my second commit
8c6be70 HEAD@{1}: commit (initial): my first commit

--切换到 8c6be70 版本，也就是我们第一次提交的版本
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git reset --hard 8c6be70
HEAD is now at 8c6be70 my first commit

--切换完毕之后再查看历史记录，当前成功切换到了 8c6be70 版本
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ git reflog
8c6be70 (HEAD -> master) HEAD@{0}: reset: moving to 8c6be70
23279eb HEAD@{1}: commit: my second commit
8c6be70 (HEAD -> master) HEAD@{2}: commit (initial): my first commit

--然后查看文件 hello.txt，发现文件内容已经变化,是第一个版本的文件
皮皮虾@DESKTOP-IO48VPB MINGW64 /f/Java/Git/git-demo (master)
$ cat hello.txt
sdjfhkasjdfhjhdsf
hello.txt

```

Git 切换版本，底层其实是移动的 HEAD 指针，具体原理如下图所示。

![image-20220228130946531](G:\Document\mdNote\Typora\image-20220228130946531.png)

## 10.git 分支操作

![image-20220228131053429](G:\Document\mdNote\Typora\image-20220228131053429.png)

**10.1  什么是分支**

在版本控制过程中，同时推进多个任务，为每个任务，我们就可以创建每个任务的单独分支。使用分支意味着程序员可以把自己的工作从开发主线上分离开来，开发自己分支的时候，不会影响主线分支的运行。对于初学者而言，分支可以简单理解为副本，一个分支就是一个单独的副本。（分支底层其实也是指针的引用）

![image-20220228131259935](G:\Document\mdNote\Typora\image-20220228131259935.png)

**10.2** **分支的好处**

同时并行推进多个功能开发，提高开发效率。

各个分支在开发过程中，如果某一个分支开发失败，不会对其他分支有任何影响。失败的分支删除重新开始即可

**10.3** **分支的操作**

![image-20220228131708801](G:\Document\mdNote\Typora\image-20220228131708801.png)

**10.3.1** **查看分支**

**1）基本语法**

**git branch -v** 

**10.3.2** **创建分支**

**git branch 分支名**

## 11.推送

**1）基本语法**

**git push 远程地址别名  分支名** 

远程地址(使用SSH链接):

![image-20220228145018468](G:\Document\mdNote\Typora\image-20220228145018468.png)

## 12.SSH免密登录设置

直接三个回车

![image-20220228145205664](G:\Document\mdNote\Typora\image-20220228145205664.png)

![image-20220228145226782](G:\Document\mdNote\Typora\image-20220228145226782.png)

![image-20220228145246265](G:\Document\mdNote\Typora\image-20220228145246265.png)

然后在github上设置

![image-20220228145330519](G:\Document\mdNote\Typora\image-20220228145330519.png)

## 13.添加远程仓库

1.查看

git remote -v

2.添加

git remote add 别名 远程地址