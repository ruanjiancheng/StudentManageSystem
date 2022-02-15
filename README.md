## 项目介绍


这是一个采用前后端分离开发的项目，前端采用 Vue 开发、后端采用 Spring boot + Mybatis 开发。

<img src="README.assets/image-20220211161726091.png" alt="image-20220211161726091" style="zoom:50%;" />

### 项目体验

服务器比较慢，初次访问请耐心等待。

url: http://47.101.155.92:8080/

- 教师端 id：4 password：123
- 学生端 id：2 password：123

欢迎大家测试

### 项目运行

```shell
sh run.sh
```

### 项目展示

~~不会 CSS 界面有点丑~~

1. 登陆界面

![image-20220211152715838](README.assets/image-20220211152715838.png)

2. admin 主界面

![image-20220211152913239](README.assets/image-20220211152913239.png)

3. 动态搜索框与表格展示

![image-20220211153012483](README.assets/image-20220211153012483.png)

4. 学生端首页展示

![image-20220211153053836](README.assets/image-20220211153053836.png)

5. 教师端成绩搜索与编辑

![image-20220211153257252](README.assets/image-20220211153257252.png)

## 前端部分

### 项目运行

**由于涉及大量的 ES6/7 等新属性，node 需要 6.0 以上版本**

```shell
git clone git@github.com:ruanjiancheng/StudentManageSystem.git

cd /StudentManageSystem/student_client

npm install

npm run serve
```

### 技术栈

- Vuex
- Router
- Axios
- Element ui
- sessionStorage

### 项目介绍

采用 vue 2.0 开发,通过调用后端提供的数据接口实现数据的动态渲染. 项目默认端口号 8080

- 使用监视器,得益于 Mybatis 动态 SQL 功能,实现高性能动态搜索功能
- 同时实现了基于前端和后端的数据分页功能
- 使用 router 配置路由,实现不同用户类型导航栏的动态渲染
- 使用 axios 异步加载后端数据
- 使用 element ui 实现表单的前端校验功能
- 使用 sessionStorage 实现登录拦截

### 实现的功能

1. admin
   1. 实现对教师, 学生, 课程的 CRUD
   2. 实现对教师业务以及学生业务的全方位控制
2. teacher 
   1. 实现查询我开设的课程, 以及选择我课程的学生信息
   2. 对学生成绩的登陆
3. student
   1. 实现选课退课的功能
   2. 实现成绩查询的功能



## 后端部分

### 项目运行

**JDK 版本需要 17.0.2**
```shell
git clone git@github.com:ruanjiancheng/StudentManageSystem.git

cd /StudentManageSystem/student_server

java -jar student_server.jar

```

### 技术栈

- Spring boot 2.6.3
- Mybatis
- JDBC
- Servlet
- Maven
- Tomcat

### 项目介绍

采用 Restful 风格开发,采用 CrossOrigin 解决跨域问题. 采用注解以及 xml 文件配置 SQL 语句, 实现动态 SQL 的功能. 为前端提供完备的数据接口.

由于 vue 项目占用了 8080 tomcat 默认端口，所以指定项目启动在 10086 端口, 可以使用 YAML 文件配置。



## 数据库设计

<img src="README.assets/image-20220211163057648.png" alt="image-20220211163057648" style="zoom: 1%;" />

建表代码：

```sql
CREATE DATABASE studentms;

USE studentms;

CREATE TABLE `s` (
    `sid` INT AUTO_INCREMENT,
    `sname` VARCHAR(30) NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`sid`)
);

CREATE TABLE `c` (
    `cid` INT AUTO_INCREMENT,
    `cname` VARCHAR(30) NOT NULL,
    `ccredit` TINYINT,
    PRIMARY KEY (`cid`)
);

CREATE TABLE `t` (
    `tid` INT AUTO_INCREMENT,
    `tname` VARCHAR(30) NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`tid`)
);

CREATE TABLE `ct` (
    `ctid` INT AUTO_INCREMENT,
    `cid` INT,
    `tid` INT,
    `term` CHAR(6) NOT NULL,
    FOREIGN KEY (`cid`) REFERENCES c(`cid`),
    FOREIGN KEY (`tid`) REFERENCES t(`tid`),
    PRIMARY KEY (`ctid`)
);

CREATE TABLE `sct` (
    `sctid` INT AUTO_INCREMENT,
    `sid` INT,
    `cid` INT,
    `tid` INT,
    `grade` FLOAT,
    `term` CHAR(6),
    FOREIGN KEY (`sid`) REFERENCES s(`sid`),
    FOREIGN KEY (`tid`) REFERENCES ct(`tid`),
    FOREIGN KEY (`cid`) REFERENCES ct(`cid`),
    PRIMARY KEY (`sctid`)
);
```



## 项目存在的问题

- 由于是第一次编写 Vue 项目, 代码复用做得并不是很好. 导致许多组件代码量巨大. 
- 动态搜索导致前端频繁调用数据接口, 使得性能降低. 考虑使用 mybatis 的缓存解决.

- 期间多次因为太菜了, 导致功能实现不了. 感谢博客园的大佬相助. (CSDN 狗都不用)
- admin 中的学生查询为过时版本的查询，由于代码与教师查询相似而且代码量巨大，没有修改学生端的查询。~~我真的不是懒狗~~
