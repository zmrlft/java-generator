# JAVA代码生成器

> 作者：橡皮膏xpg



#	2024-12-23

- 完成了本地代码生成功能的开发

-

-



#	2024-12-25

-利用picocli增加了命令行功能

-使用了命令模式设计，其中客户端为Main,调用方CommandExecutor类，接收者MainGenerator类
具体命令：每个子命令类
命令：在每一个子命令里实现了Runnable/Callable接口

-注意事项：当在 IDEA 中运行源代码时，System.getProperty("user.dir")返回的是项目的根目录；
当打包成jar运行，System.getProperty("user.dir")返回的是执行java -jar命令时所在的目录。



#   2025-1-4

- 完成了基础代码生成器制作工具的开发



