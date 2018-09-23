package com.cuzz.config;

import com.cuzz.bean.Color;
import com.cuzz.bean.ColorFactoryBean;
import com.cuzz.bean.Person;
import com.cuzz.condition.LinuxCondition;
import com.cuzz.condition.MyImportBeanDefinitionRegistrar;
import com.cuzz.condition.MyImportSelector;
import com.cuzz.condition.WindowCondition;
import org.springframework.context.annotation.*;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 15:40
 * @Description: 配置类
 */
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig2 {

    // @Scope(value = "prototype")
//    @Lazy
//    @Bean
//    public Person person() {
//        System.out.println("给容器中添加Person...");
//        return new Person("vhuj", 25);
//    }

    @Conditional({WindowCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 60);
    }
    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Person person02() {
        return new Person("linus", 45);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
