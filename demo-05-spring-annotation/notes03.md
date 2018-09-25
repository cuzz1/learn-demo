# Spring注解驱动开发（三）

## 1. 属性赋值@value赋值

使用@Value赋值

- 基本数值
- 可以写SPEL表达式 #{}
- 可以${}获取配置文件信息（在运行的环境变量中的值）



使用xml时候导入配置文件是

```XML
<context:property-placeholder location="classpath:person.properties"/>
```

使用注解可以在配置类添加一个@PropertySource注解把配置文件中k/v保存到运行的环境中

使用${key}来获取

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/24 18:43
 * @Description:
 */
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValue {

    @Bean
    public Person person() {
        return new Person();
    }
}
```





```java
@Data
public class Person {

    @Value("vhuj")
    private String name;

    @Value("#{20-2}")
    private Integer age;

    @Value("${person.nickName}")
    private String nickName;
}
```

测试

```java
    @Test
    public void test01() {
        printBean(applicationContext);
        System.out.println("---------------------------");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        System.out.println("---------------------------");

    }
```

输出

```
---------------------------
Person(name=vhuj, age=18, nickName=三三)
---------------------------
```

## 2. 自动装配@Autowired@Qualifier@Primary

自动转配：

​	Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值

 @Autowired自动注入:

​	a. 默认优先按照类型去容器中寻找对应的组件，如果找到去赋值

​	b. 如果找到到相同类型的组件，再将属性名（`BookDao bookdao`）作为组件的id去容器中查找

​	c. 接下来还可以使用`@Qualifier("bookdao")`明确指定需要装配的id

​	d. 默认是必须的，我们可以指定    `@Autowired(required=false)`，指定非必须

@Primary让Spring自动装配时首先装配

## 3. 自动装配@Resource和@Inject

Spring还支持使用@Resource (JSR250) 和@Inject (JSR330) 注解，这两个是java规范



@Resource和@Autowired一样实现自动装配功能，默认是按组件名称进行装配的

没有支持@Primary和@Autowird(required=false)的功能



## 4. 自动装配其他地方的自动装配

@Autowired：构造器、参数、方法属性等

标注到方法位子上@Bean+方法参数，参数从容器中获取

```java
/**
 * @Author: cuzz
 * @Date: 2018/9/24 20:57
 * @Description:
 */
public class Boss {
    // 属性
    @Autowired
    private Car car;
	
    // 构造器 如果构造器只有一个有参构造器可以省略
    @Autowired
    public Boss(@Autowired ar car) {
    }

    public Car getCar() {
        return car;
    }
	
    // set方法
    @Autowired		 // 参数
    public void setCar(@Autowired Car car) {
        this.car = car;
    }
}
```

