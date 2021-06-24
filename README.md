# SSM-Mybatis框架学习

## 一、框架

### 1.1 什么是框架

​		框架（Framework）是整个或部分系统的可重用设计，表现为一组抽象构件及构件实例间交互的方法;另一种定义认为，框架是可被应用开发者定制的应用骨架。前者是从应用方面而后者是从目的方面给出的定义。

​		简而言之，框架其实就是某种应用的**半成品**，就是一组组件，供你选用完成你自己的系统。简单说就是使用别人搭好的舞台，你来做表演。而且，框架一般是成熟的，不断升级的软件。

### 2.2 MVC设计思想

> 什么是MVC？

M(Model模型)V(View视图)C(Controller控制器)开发模型

![image-20210625021811903](C:\Users\11877\AppData\Roaming\Typora\typora-user-images\image-20210625021811903.png)

## **二、Mybatis 框架快速入门**

### 2.1**创建** **maven** **工程**

```xml
创建 mybatis01 的工程，工程信息如下：

Groupid:com.itheima

ArtifactId:mybatis01

Packing:jar
```

### **2.2** **添加** **Mybatis3.4.5** **的坐标**

```xml
<dependencies>

<dependency>

<groupId>org.mybatis</groupId>

<artifactId>mybatis</artifactId>

<version>3.4.5</version>

</dependency>

<dependency>

<groupId>junit</groupId>

<artifactId>junit</artifactId>

<version>4.10</version>

<scope>test</scope>

</dependency>

<dependency>

<groupId>mysql</groupId>

<artifactId>mysql-connector-java</artifactId>

<version>5.1.6</version>

<scope>runtime</scope>

</dependency>

<dependency>

<groupId>log4j</groupId>

<artifactId>log4j</artifactId>

<version>1.2.12</version>

</dependency>

 </dependencies>
```

### 2.3创建User实体类

### 2.4编写持久层接口UserDao(内部写方法)

### **2.5** **编写持久层接口的映射文件** **IUserDao.xml**（SQL语句等配置信息）

### **2.6** **编写** **SqlMapConfig.xml** **配置文件**

文件创建位置如图所示：

![image-20210625022635552](C:\Users\11877\AppData\Roaming\Typora\typora-user-images\image-20210625022635552.png)

### **2.7** **编写测试类**

在测试类中

```java
//1.读取配置文件

InputStream in = Resources.*getResourceAsStream*("SqlMapConfig.xml");

//2.创建 SqlSessionFactory 的构建者对象

SqlSessionFactoryBuilder builder = **new** SqlSessionFactoryBuilder();

//3.使用构建者创建工厂对象 SqlSessionFactory

SqlSessionFactory factory = builder.build(in);

//4.使用 SqlSessionFactory 生产 SqlSession 对象

SqlSession session = factory.openSession();

//5.使用 SqlSession 创建 dao 接口的代理对象

IUserDao userDao = session.getMapper(IUserDao.**class**);

//6.使用代理对象执行查询所有方法

List<User> users = userDao.findAll();

**for**(User user : users) {

System.**out**.println(user);

}

//7.释放资源

session.close();

in.close();
```

补充：

**基于注解的mybatis使用**

①在**持久层**的方法上添加Sql语句的注解如：

```@Select("select * from user")```

②修改**SqlMapConfig.xml**

```xml
<!-- 告知 mybatis 映射配置的位置 --> 

<mappers> 

<mapper class=*"com.itheima.dao.IUserDao"*/>

</mappers>
```

## 三、自定义 Mybatis 框架

实际开发中常使用mybatis代理jdbc所以在这里就不细说了

具体实现代码：

[Silencoco/SSM-Mybatis: SSM 框架之Mybatis学习 (github.com)](https://github.com/Silencoco/SSM-Mybatis)

**设计说明：**

![image-20210625023737701](C:\Users\11877\AppData\Roaming\Typora\typora-user-images\image-20210625023737701.png)

![image-20210625023853394](C:\Users\11877\AppData\Roaming\Typora\typora-user-images\image-20210625023853394.png)

![image-20210625023943242](C:\Users\11877\AppData\Roaming\Typora\typora-user-images\image-20210625023943242.png)

![image-20210625023956412](C:\Users\11877\AppData\Roaming\Typora\typora-user-images\image-20210625023956412.png)

## 四、代理DAO实现CRUD操作

**Mybatis** **与** **JDBC** **编程的比较**

1.数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。解决：在 SqlMapConfig.xml 中配置数据链接池，使用连接池管理数据库链接。

2.Sql 语句写在代码中造成代码不易维护，实际应用 sql 变化的可能较大，sql 变动需要改变 java 代码。

解决：将 Sql 语句配置在 XXXXmapper.xml 文件中与 java 代码分离。

3.向 sql 语句传参数麻烦，因为 sql 语句的 where 条件不一定，可能多也可能少，占位符需要和参数对应。

解决：Mybatis 自动将 java 对象映射至 sql 语句，通过 statement 中的 parameterType 定义输入参数的类型。

4.对结果集解析麻烦，sql 变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成 pojo 对象解析比较方便。

解决：Mybatis 自动将 sql 执行结果映射至 java 对象，通过 statement 中的 resultType 定义输出结果的类型

## 五、 **Mybatis** **的输出结果封装**

resultType 属性：

可以指定**结果集**的类型，它支持**基本类型**和**实体类类型**

同时，当是实体类名称是，还有一个要求，实体类中的**属性名称必须和查询语句中的列名保持一致**，否则无法实现封装。

resultMap 结果类型：

resultMap 标签可以建立查询的列名和实体类的属性名称不一致时建立对应关系。从而实现封装。

在 select 标签中使用 resultMap 属性指定引用即可。同时 resultMap 可以实现将查询结果映射为复杂类型的 pojo，比如在查询结果映射对象中包括 pojo 和 list **实现一对一查询和一对多查询**。

## 六、SqlMapConfig.xml配置文件

```xml
-properties****（属性）**

**--property**

-settings（全局配置参数）

--setting

**-typeAliases****（类型别名）**

**--typeAliase**

**--package**

-typeHandlers（类型处理器）

-objectFactory（对象工厂）

-plugins（插件）

-environments（环境集合属性对象）

--environment（环境子属性对象）

---transactionManager（事务管理）

---dataSource（数据源）

**-mappers****（映射器）**

**--mapper**

**--package**
```

**properties****（属性）**的两种配置方式

一、

```xml
<properties> 
    <property name="jdbc.driver" value="com.mysql.jdbc.Driver"/>
    <property name="jdbc.url" value="jdbc:mysql://localhost:3306/eesy"/>
    <property name="jdbc.username" value="root"/>
    <property name="jdbc.password" value="1234"/>
</properties>
```

二、**在 classpath 下定义 db.properties 文件**

```properties
jdbc.driver=com.mysql.jdbc.Driver

jdbc.url=jdbc:mysql://localhost:3306/eesy

jdbc.username=root

jdbc.password=1234
```

**dataSource** **标签就变成了引用下面的配置**

```xml
<dataSource type="POOLED">
    <property name="driver" value="${jdbc.driver}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</dataSource>
```

### 6.1 typeAliases（类型别名）

在 SqlMapConfig.xml 中配置：

```xml
<typeAliases>
<!-- 单个别名定义 --> <typeAlias alias="user" type="com.itheima.domain.User"/>
<!-- 批量别名定义，扫描整个包下的类，别名为类名（首字母大写或小写都可以） --> <package name="com.itheima.domain"/>
<package name="其它包"/>
</typeAliases>
```



### 6.2 mappers（映射器）

**6.2.1 <mapper resource=" " />**

```
使用相对于类路径的资源
如：<mapper resource="com/itheima/dao/IUserDao.xml" />
```

**6.2.2 <mapper class=" " />**

```
使用 mapper 接口类路径
如：<mapper class="com.itheima.dao.UserDao"/>
注意：此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。 
```

**6.2.3 <package name=""/>**

```
注册指定包下的所有 mapper 接口
如：<package name="cn.itcast.mybatis.mapper"/>
注意：此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。
```

## 七、**Mybatis** **连接池与事务深入**

在 Mybatis 的 SqlMapConfig.xml 配置文件中，通过<dataSource type="pooled">来实现 Mybatis 中连接池的配置

我们的数据源配置就是在 SqlMapConfig.xml 文件中，具体配置如下：

```xml
<!-- 配置数据源（连接池）信息 --> 
<dataSource type="POOLED"> <property name="driver" value="${jdbc.driver}"/>
<property name="url" value="${jdbc.url}"/>
<property name="username" value="${jdbc.username}"/>
<property name="password" value="${jdbc.password}"/>
</dataSource>
```

MyBatis 在初始化时，根据<dataSource>的 type 属性来创建相应类型的的数据源 DataSource，即：

type=”POOLED”：MyBatis 会创建 PooledDataSource 实例

type=”UNPOOLED” ： MyBatis 会创建 UnpooledDataSource 实例

type=”JNDI”：MyBatis 会从 JNDI 服务上查找 DataSource 实例，然后返回使用

### 7.1 **Mybatis** **的事务控制**

​		在 JDBC 中我们可以通过手动方式将事务的提交改为手动方式，通过 setAutoCommit()方法就可以调整。
​		那么我们的 Mybatis 框架因为是对 JDBC 的封装，所以 Mybatis 框架的事务控制方式，本身也是用 JDBC 的setAutoCommit()方法来设置事务提交方式的。

```session.commit();```

#### 7.1.1 **Mybatis** **自动提交事务的设置**

通过上面的研究和分析，现在我们一起思考，为什么 CUD 过程中必须使用 sqlSession.commit()提交事务？主要原因就是在连接池中取出的连接，都会将调用 connection.setAutoCommit(false)方法，这样我们就必须使用 sqlSession.commit()方法，相当于使用了 JDBC 中的 connection.commit()方法实现事务提交.

```java
//1.读取配置文件

in = Resources.*getResourceAsStream*("SqlMapConfig.xml");

//2.创建构建者对象

SqlSessionFactoryBuilder builder = **new** SqlSessionFactoryBuilder();

//3.创建 SqlSession 工厂对象

factory = builder.build(in);

//4.创建 SqlSession 对象

session = **factory.openSession(true);**

//5.创建 Dao 的代理对象

userDao = session.getMapper(IUserDao.**class**);
```

### 8. **Mybatis** **的动态** **SQL** **语句**

持久层：

```xml
<select id=*"findByUser"* resultType=*"user"* parameterType=*"user"*>

select * from user where 1=1

<if test=*"username!=null and username != '' "*>

and username like #{username}

</if> 

<if test=*"address != null"*>

and address like #{address}

</if>

</select>
```

测试类

```java
@Test
public void testFindByUser() {
    User u = new User();
    u.setUsername("%王%");
    u.setAddress("%顺义%");
    //6.执行操作
    List < User > users = userDao.findByUser(u);
    for (User user: users) {
        System.out.println(user);
    }
}
```

#### 9. Mybatis 中简化编写的 SQL 片段

```xml
<!-- 抽取重复的语句代码片段 --> 
<sql id="defaultSql">
select * from user
</sql>
```

```xml
<!-- 配置查询所有操作 --> 
<select id="findAll" resultType="user"> <include refid="defaultSql"></include></select>
<!-- 根据 id 查询 --> 
<select id="findById" resultType="UsEr" parameterType="int"><include refid="defaultSql"></include>
where id = #{uid}
</select>
```

## 八、**Mybatis** **多表查询之一对多**、多对多

#### 一对多：

需求：

​		查询所有用户信息及用户关联的账户信息。

分析：

​		用户信息和他的账户信息为一对多关系，并且查询过程中如果用户没有账户信息，此时也要将用户信息查询出来，我们想到了左外连接查询比较合适。

#### **多对多**：

通过前面的学习，我们使用 Mybatis 实现一对多关系的维护。多对多关系其实我们看成是双向的一对多关系

## 九、 **Mybatis 延迟加载策略**

### 9.1 什么是延迟加载

**延迟加载：**

​		就是在需要用到数据时才进行加载，不需要用到数据时就不加载数据。延迟加载也称懒加载.

**好处**：

​		先从单表查询，需要时再从关联表去关联查询，大大提高数据库性能，因为查询单表要比关联查询多张表速度要快。

**坏处**：

​		因为只有当需要用到数据时，才会进行数据库查询，这样在大批量数据查询时，因为查询工作也要消耗时间，所以可能造成用户等待时间变长，造成用户体验下降。

### 9.2 实现需求

需求：

查询账户(Account)信息并且关联查询用户(User)信息。如果先查询账户(Account)信息即可满足要求，当我们需要查询用户(User)信息时再查询用户(User)信息。把对用户(User)信息的按需去查询就是延迟加载。mybatis第三天实现多表操作时，我们使用了resultMap来实现一对一，一对多，多对多关系的操作。主要是通过 association、collection 实现一对一及一对多映射。**association、collection** 具备延迟加载功能。

### 9.3 **使用** **assocation** **实现延迟加载**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<mapper namespace="com.itheima.dao.IAccountDao">
<!-- 建立对应关系 --> 
    <resultMap type="account" id="accountMap"> 
        <id column="aid" property="id"/>
        <result column="uid" property="uid"/>
        <result column="money" property="money"/>
<!-- 它是用于指定从表方的引用实体属性的 --> 
        <association property="user" javaType="user"
                 select="com.itheima.dao.IUserDao.findById"
                 column="uid">
        </association>
    </resultMap>
    <select id="findAll" resultMap="accountMap">
        select * from account
    </select>
</mapper>
```

select： 填写我们要调用的 select 映射的 id 
column ： 填写我们要传递给 select 映射的参数



持久层接口及映射文件

```java
public interface** IUserDao {

/**

\* 根据 id 查询

\* **@param** userId

\* **@return**

*/

User findById(Integer userId);

}
```

```xml
<?xml version=*"1.0"* encoding=*"UTF-8"*?>

<!DOCTYPE mapper 

 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 

 "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 

<mapper namespace=*"com.itheima.dao.IUserDao"*>

<!-- 根据 id 查询 --> 

<select id=*"findById"* resultType=*"user"* parameterType=*"int"* >

select * from user where id = #{uid}

</select>

</mapper>
```

### 9.4 开启延迟加载

```xml
<settings> 
    <setting name="lazyLoadingEnabled" value="true"/>
    <setting name="aggressiveLazyLoading" value="false"/>
</settings>
```

### 9.5 使用 Collection 实现延迟加载

同样我们也可以在一对多关系配置的<collection>结点中配置延迟加载策略。
<collection>结点中也有 select 属性，column 属性。
需求：
完成加载用户对象时，查询该用户所拥有的账户信息.

**在** **User** **实体类中加入** **List<Account>****属性**

**编写用户和账户持久层接口的方法**

**编写用户持久层映射配置**

```xml
<resultMap type="user" id="userMap"> 
    <id column="id" property="id"></id> 
    <result column="username" property="username"/>
    <result column="address" property="address"/>
    <result column="sex" property="sex"/>
    <result column="birthday" property="birthday"/>
<!-- collection 是用于建立一对多中集合属性的对应关系
ofType 用于指定集合元素的数据类型
select 是用于指定查询账户的唯一标识（账户的 dao 全限定类名加上方法名称）
column 是用于指定使用哪个字段的值作为条件查询
--> 
    <collection property="accounts" ofType="account"
                select="com.itheima.dao.IAccountDao.findByUid"
                column="id">
    </collection>
</resultMap>
<!-- 配置查询所有操作 -->
<select id="findAll" resultMap="userMap">
    select * from user
</select>
```

<collection>标签：
主要用于加载关联的集合对象
select 属性：
用于指定查询 account 列表的 sql 语句，所以填写的是该 sql 映射的 id
column 属性：
用于指定 select 属性的 sql 语句的参数来源，上面的参数来自于 user 的 id 列，所以就写成 id 这一
个字段名了

## 十、**Mybatis** **一级缓存**

​		一级缓存是 SqlSession 级别的缓存，只要 SqlSession 没有 flush 或 close，它就存在。

​		一级缓存是 SqlSession 范围的缓存，当调用 SqlSession 的修改，添加，删除，commit()，close()等方法时，就会清空一级缓存。

​		第一次发起查询用户 id 为 1 的用户信息，先去找缓存中是否有 id 为 1 的用户信息，如果没有，从数据库查询用户信息。得到用户信息，将用户信息存储到一级缓存中。

​		如果 sqlSession 去执行 commit 操作（执行插入、更新、删除），清空 SqlSession 中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

​		第二次发起查询用户 id 为 1 的用户信息，先去找缓存中是否有 id 为 1 的用户信息，缓存中有，直接从缓存中获取用户信息。

## 十一、**二级缓存的开启与关闭**

**第一步：在** **SqlMapConfig.xml** **文件开启二级缓存**

```xml
<settings>

<!-- 开启二级缓存的支持 --> 

<setting name=*"cacheEnabled"* value=*"true"*/>

</settings>
```

因为 cacheEnabled 的取值默认就为 true，所以这一步可以省略不配置。为 true 代表开启二级缓存；为false 代表不开启二级缓存。

**第二步：配置相关的** **Mapper** **映射文件**

<cache>标签表示当前这个 mapper 映射将使用二级缓存，区分的标准就看 mapper 的 namespace 值。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<mapper namespace="com.itheima.dao.IUserDao">
<!-- 开启二级缓存的支持 -->
<cache></cache>
</mapper>
```

**配置** **statement** **上面的** **useCache** **属性**

```xml
<!-- 根据 id 查询 --> 
<select id="findById" resultType="user" parameterType="int" useCache="true">
select * from user where id = #{uid}
</select>
```

将 UserDao.xml 映射文件中的<select>标签中设置 useCache=”true”代表当前这个 statement 要使用

二级缓存，如果不使用二级缓存可以设置为 false。

**注意：针对每次查询都需要最新的数据 sql，要设置成 useCache=false，禁用二级缓存。**

## 十二、 **Mybatis** **注解开发** 

### 12.1 **mybatis** **的常用注解说明**

**@Insert:**实现新增

**@Update:**实现更新

**@Delete:**实现删除

**@Select:**实现查询

**@Result:**实现结果集封装

**@Results**:可以与@Result 一起使用，封装多个结果集

**@ResultMap:**实现引用@Results 定义的封装

**@One:**实现一对一结果集封装

**@Many:**实现一对多结果集封装

**@SelectProvider:** 实现动态 SQL 映射

**@CacheNamespace:**实现注解二级缓存的使用

### 12.2 **使用注解实现复杂关系映射开发**

实现复杂关系映射之前我们可以在映射文件中通过配置<resultMap>来实现，在使用注解开发时我们需要借

助@Results 注解，@Result 注解，@One 注解，@Many 注解。
