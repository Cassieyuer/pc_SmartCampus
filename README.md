### 智慧校园简介

- 本项目为智慧校园***PC***端，***Android***端请看我在***Github***上的[另一个项目](https://github.com/stdlzp/android_SmartCampus)
- 新人编写，欢迎支持，欢迎 ***Star !!!***
# 使用方法

 本项目数据库使用MySQL，MySQL配置文件在application.yml文件中，自行更改为你电脑上的数据库信息    
 本项目实现Web请求日志入Mongodb数据库  
 Mongodb安装方法请参考[这里](http://jingyan.baidu.com/article/d5c4b52bef7268da560dc5f8.html)
   -----
 如不需要使用请按如下操作
 1. 在pom文件中删除以下依赖  
 ````xml
        <dependency>
            <groupId>org.mongodb</groupId>
            <artifactId>mongodb-driver</artifactId>
        </dependency>
 ````
 2.在log4j.properties文件中删除Mongodb的配置
 ```properties
    1.  log4j.logger.mongodb=INFO, mongodb 

    2.  # mongodb输出
        log4j.appender.mongodb=com.std.smartcampus.common.config.MongoAppender
        log4j.appender.mongodb.connectionUrl=mongodb://localhost:27017
        log4j.appender.mongodb.databaseName=logs
        log4j.appender.mongodb.collectionName=logs_request
```
 3. 删除com.std.smartcampus.common.config.MongoAppender类  

 4. 在com.std.smartcampus.Application类中运行main方法，稍等片刻，会自动打开浏览器，项目启动成功。