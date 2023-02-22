安装软件: sudo apt-get install 包名

卸载软件: sudo apt-get --purge remove 包名(--purge是可选项,写上这个属性是将软件及其配置文件一并删除,如不需要删除配置文件,可执行sudo apt-get remove 包名)

卸载rabbitmq:	sudo apt-get --purge remove rabbitmq-server



压缩：tar cvfz 目标文档 源文档1 [源文档2...]
例如：tar cvfz m.tar.gz hello.cpp hello.h //m.tar.gz是目标文档，hello.cpp和hello.h是源文档
解压：tar xvfz 目标文档
例如：tar xvfz m.tar.gz //在当前目录下将压缩包文档m.tar.gz解压缩





## 删除:

rm [选项] 文件
  -f, --force          强力删除，不要求确认
  -i                       每删除一个文件或进入一个子目录都要求确认
  -I                       在删除超过三个文件或者递归删除前要求确认
  -r, -R                递归删除子目录
  -d, --dir             删除空目录
  -v, --verbose     显示删除结果
============================================================================
常用如下几个：
rm -d 目录名              #删除一个空目录
rmdir 目录名              #删除一个空目录
rm -r 目录名              #删除一个非空目录
rm 文件名                  #删除文件

在终端进到那个文件夹，然后执行：
sudo rm -rf 文件夹名
如果还是不行，就用
sudo chmod 777 文件夹名(这个最管用)
sudo rm -rf 文件夹名


