### AopDemo
展示切面的各种使用方式

### proxyCustom

模拟JDK接口代理的方式，生成代理对象

com.holybell.proxy.ProxyUtil : 假定增强逻辑不变，生成代理对象的工具类

com.holybell.proxy.ProxyUtil2 : 模拟JDK的Proxy类，使用接口和InvocationHanlder的方式，实现可以动态改变增强逻辑的代理类生成工具类

### MockSpring

com.holybell.org.spring.util.BeanFactory : 模拟Spring扫描解析XML文件，生成bean对象，模拟自动装配特性

com.holybell.org.spring.util.AnnotationConfigBeanFactory : 模拟Spring通过扫描注解，生成bean对象

### TestSpring

1. com.holybell.demo.genmapper包

1.1 使用ImportBeanDefinitionRegistrar实现类MyImportBeanDefinitionRegistrar暴露Spring
    的BeanDefinitionRegistry（可以向Spring注册需要实例化的bean定义）
    这个类可以通过扫描获取配置的包下的所有接口信息，生成BeanDefinition，修改beanClass属性和构造器属性
    
1.2 由于使用接口生成的BeanDefinition，Spring无法实例化接口，借助BeanFactory接口的特性，
    将BeanDefinition的beanClass修改为一个统一的 MyFactoryBean，
    传入本次要生成代理对象的接口作为成员变量

1.3 修改生成BeanDefinition的构造器信息，将要代理的接口作为构造器参数传给 MyFactoryBean， 
    这样不同的接口都可以使用同一个 MyFactoryBean 生成动态代理对象
    
1.4 MyFactoryBean 中模拟使用JDK接口动态代理返回生成的代理对象给Spring容器管理

1.5 最后，通过 AnnotationConfigApplicationContext 可以正常的获取到 cardDao 名称的代理对象
    com.holybell.demo.genmapper.service.CardService 也成功的Autowired了这个对象
    
2. com.holybell.demo.import_demo.MyImportSelector

    模拟在Bean的生命周期中，替换Spring实例化的bean对象，使用生成的动态代理对象替换。
    
3. com.holybell.demo.configuration.config.Config

    测试Spring中配置类标注@Configuration与否的区别