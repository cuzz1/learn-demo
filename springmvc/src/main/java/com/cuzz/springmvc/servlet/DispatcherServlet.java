package com.cuzz.springmvc.servlet;

import com.cuzz.springmvc.anotation.Autowired;
import com.cuzz.springmvc.anotation.Controller;
import com.cuzz.springmvc.anotation.RequestMapping;
import com.cuzz.springmvc.anotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @Author: cuzz
 * @Date: 2018/9/18 15:32
 * @Description:
 */
public class DispatcherServlet extends HttpServlet{

    private Properties p = new Properties();

    private List<String> classNames = new ArrayList<>();

    // ioc容器
    private Map<String, Object> ioc = new HashMap<>();

    private Map<String, Method>  handlerMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURI();
        System.out.println("===url: "+url);
        String contextPath = req.getContextPath();
        System.out.println("====contextpath: " + contextPath);
        url = url.replace(contextPath, "").replace("/+", "/");
        if (!handlerMapping.containsKey(url)) {
            resp.getWriter().write("404");
        }

        System.out.println("获得对应的方法: " + handlerMapping.get(url));

        Method method = handlerMapping.get(url);
        // 通过反射拿到相应的方法
        // Object object = method.invoke(obj)

    }


    @Override
    public void init(ServletConfig config) throws ServletException {

        // 1. 加载配置文件
        // String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        // doLoadConfig(contextConfigLocation);

        // 2. 扫描所有的类
        // doScanner(p.getProperty("scanPackage"));
        doScanner("com.cuzz.demo");

        // 3. 初始化所有相关Class的实例，并且将其保存的IOC容器中
        doInstance();

        // 4. 自动化的依赖注入
        doAutowired();

        // 5. 初始化HandlerMappering
        initHandlerMappering();

        System.out.println("cuzz MVC init success");

    }
    private void doLoadConfig(String location) {
        System.out.println(this.getClass().getClassLoader());
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String packageName) {
        // 所有的.替换成文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        // classDir包括所有的文件和文件夹
        for (File file : classDir.listFiles()) {
            // 如果是文件夹, 递归调用
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                // 获取className
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }

        }
    }

    private void doInstance() {
        // 如果为空
        if (classNames.isEmpty()) {
            return;
        }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                // 进行实例化, 只对有注解的才实例化

                // 1. 默认从业类名的首字母小写
                // 2. 如果自己定义类名字的话, 优先使用自己定义的名字
                // 3. 根据类名来配置, 利用接口作为key
                if (clazz.isAnnotationPresent(Controller.class)) {
                    String beanName = lowerFirstString(clazz.getSimpleName());
                    ioc.put(beanName, clazz.newInstance());
                }


                if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    // 先取出自定的名字
                    String beanName = service.value();
                    // 如果为空就用类名小写
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstString(clazz.getSimpleName());
                    }

                    // 把这类加入到ioc容器中
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);

                    // 把这个类的接口添加到ioc容器中
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        ioc.put(i.getName(), instance);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 暴力反射 private也能拿到
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                   Autowired autowired = field.getAnnotation(Autowired.class);
                   String beanName = autowired.value().trim();
                   // 是一个接口
                   if ("".equals(beanName)) {
                       beanName = field.getType().getName();
                   }

                   // 私有的话 强制访问
                   field.setAccessible(true);
                    try {
                        field.set(entry.getValue(), ioc.get(beanName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    private void initHandlerMappering() {
        if (ioc.isEmpty()) {
            return;
        }

        String classUrl = "";
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                    classUrl = requestMapping.value();
                }
            }

            Method[] methods = clazz.getMethods();

            for (Method method : methods) {
                String methodUrl = "";
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    methodUrl = requestMapping.value();
                    String url = classUrl + methodUrl;
                    handlerMapping.put(url, method);
                    System.out.println("=== Mapping :" + url + "  " + method);
                }

            }
        }

    }


    private String lowerFirstString(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
