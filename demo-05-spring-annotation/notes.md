# Spring注解

## 1. bean的注入

### 1）使用xml方式

我们一起注入一个bean使用xml来配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="person" class="com.cuzz.bean.Person">
        <property name="name" value="cuzz"></property>
        <property name="age" value="18"></property>
    </bean>
    
</beans>
```

我可以使用`ClassPathXmlApplicationContext`来获取

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:48
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 用id获取
        Person bean = (Person) applicationContext.getBean("person");
        System.out.println(bean);
    }
}
```

输出`Person(name=cuzz, age=18)`

## 2) 使用注解方式

编写一个配置类

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:55
 * @Description: 配置类
 */
@Configuration // 告诉Spring这是一个配置类
public class MainConfig {
    // 给容器中注册一个Bean,类型为返回值类型,id默认用方法名
    // 也可以指定id
    @Bean(value = "person01")
    public Person person() {
        return new Person("vhsj", 16);
    }
}
```

可以通过`AnnotationConfigApplicationContext`来获取，并且获取id

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:59
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) context.getBean(Person.class);
        System.out.println(person);

        String[] names = context.getBeanNamesForType(Person.class);
        for (String name: names) {
            System.out.println(name);
        }
    }
}
```

输出

```
Person(name=vhsj, age=16)
person01
```

由于给bean添加一个一个value，可以改变默认id

## 2. 包扫描

### 1） 使用xml

只要标注了注解就能扫描到如:@Controller @Service @Repository @component

```
<context:component-scan base-package="com.cuzz"></context:component-scan>
```

### 2） 注解

在配置类中添加

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:55
 * @Description: 配置类
 */
@Configuration // 告诉Spring这是一个配置类
@ComponentScan(value = "com.cuzz") // 指定包
public class MainConfig {
    
}
```

添加controller、service等

测试

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/23 13:03
 * @Description:
 */
public class IOCTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        // 获取所有bean定义的名字
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
    }
}
```

输出结果

```
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
mainConfig
bookController
bookDao
bookService
person01
```

可以看出添加@Controller @Service @Repository @component注解的都可以扫描到



还可以指定添加某些类，和排除某些类，进入ComponentScan注解中有下面两个方法

```java
ComponentScan.Filter[] includeFilters() default {};
ComponentScan.Filter[] excludeFilters() default {};

includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
```

配置类，排除Controller

```java
@Configuration // 告诉Spring这是一个配置类
@ComponentScan(value = "com.cuzz", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
public class MainConfig {

}
```

运行测试方法，可以得出没有Controller类的

```
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
mainConfig
bookDao
bookService
person01
```

### 3) 自定义TypeFilter指定过滤规则

第一和第二比较常用

```
FilterType.ANNOTATION：按照注解
FilterType.ASSIGNABLE_TYPE：按照给定的类型；
FilterType.ASPECTJ：使用ASPECTJ表达式
FilterType.REGEX：使用正则指定
FilterType.CUSTOM：使用自定义规则
```

新建一个MyTypeFilte类实现TypeFilter接口

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/23 15:03
 * @Description:
 */
public class MyTypeFilter implements TypeFilter{
    /**
     * metadataReader：读取到的当前正在扫描的类的信息
     * metadataReaderFactory:可以获取到其他任何类信息的
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
            throws IOException {
        //获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类资源（类的路径）
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        System.out.println("--->"+className);
        // 这些类名中包含er就返回true
        if(className.contains("er")){
            return true;
        }
        return false;
    }
}
```

使用自定义注解记得需要关闭默认过滤器`useDefaultFilters = false`

```jav
/**
 * @Author: cuzz
 * @Date: 2018/9/23 10:55
 * @Description: 配置类
 */
@Configuration 
@ComponentScan(value = "com.cuzz",
        includeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM,
                classes = MyTypeFilter.class),
        useDefaultFilters = false)
public class MainConfig {
    // 给容器中注册一个Bean,类型为返回值类型,id默认用方法名
    // 也可以指定id
    @Bean(value = "person01")
    public Person person() {
        return new Person("vhsj", 16);
    }
}
```
测试
```
--->com.cuzz.AppTest
--->com.cuzz.bean.MainTest
--->com.cuzz.config.IOCTest
--->com.cuzz.config.MainTest
--->com.cuzz.App
--->com.cuzz.bean.Person
--->com.cuzz.config.MyTypeFilter
--->com.cuzz.controller.BookController
--->com.cuzz.dao.BookDao
--->com.cuzz.sevice.BookService
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
mainConfig     // 不是扫描的 
person		   // 这个是在bean中
myTypeFilter   // 有er
bookController // 有er
bookService    // 有er
person01       // 这个是在bean中
```

## 3. 组件注册@Scope设置作用域

Spring的bean默认是单例的

```java
    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        // 获取所有bean定义的名字
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean == bean2);   // 输出true
    }
```

Scope可以去下面4个取值
```
ConfigurableBeanFactory#SCOPE_PROTOTYPE   // 多实例 每次获取时创建对象，不会放在ioc容器中
ConfigurableBeanFactory#SCOPE_SINGLETON   // 单实例 ioc容器启动是创建对象，以后从容器中获取
WebApplicationContext#SCOPE_REQUEST       // web同一次请求创建一个实例
WebApplicationContext#SCOPE_SESSION       // web同一个session创建一个实例
```


如果我们把Scope修改

```
/**
 * @Author: cuzz
 * @Date: 2018/9/23 15:40
 * @Description:
 */
@Configuration
public class MainConfig2 {

    @Scope(value = "prototype")
    @Bean
    public Person person() {
        return new Person("vhuj", 25);
    }
}
```

则测试输出false