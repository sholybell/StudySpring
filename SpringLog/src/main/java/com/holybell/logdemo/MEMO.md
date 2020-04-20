笔记：

JCL ： java提供的common-logging日志包

使用门面模式，提供了统一的接口访问日志框架的接口，仅仅支持如下框架：

org.apache.commons.logging.impl.Log4JLogger
org.apache.commons.logging.impl.Jdk14Logger
org.apache.commons.logging.impl.Jdk13LumberjackLogger
org.apache.commons.logging.impl.SimpleLog

使用JCL可以在后期直接替换上述日志输出框架即可

SLF4J : 由于JCL已经不维护了，后面有了更好的SLF4J，功能类似

SLF4J提供了绑定器，使用SLF4J，额外引入slf4j-jdk14、slf4j-log4j12
等绑定器，那么SLF4J就能直接调用绑定的响应日志框架

同时！

SLF4J还提供了桥接器（可以看工程中，桥接器说明.png图片说明）

假设某个工程直接使用了log4j这个直接输出日志的框架，但是想要替换日志框架
这个时候就可以使用桥接器，其实就是调用log4j接口的时候，桥接器会将日志输出逻辑
桥接到SLF4J，而SLF4J会去查找自己绑定器绑定的日志框架。

简单来说，就是桥接器可以将某个日志框架桥接到SLF4J接口，然后统一输出到SLF4J
绑定的另一个日志输出框架。

注意：

如果你的项目中本来是用了SLF4J绑定了JCL，但是你又引入了将JCL桥接到SLF4J的桥接器，
那么这就会导致死循环，出现栈溢出!!!!!

当你的项目依赖的包很复杂的时候，就可能引入一个包含了会导致你项目产生死循环的日志桥接器。

参考这篇博文：https://shimo.im/docs/kKCgwqgQqHYTeQ8J/read

————————————————————————————————————————————————————————————————

Spring5的新特性:

Spring4使用了JCL：commons-logging.jar
Spring5则使用了Spring-jcl.jar, 默认使用JUL，可以参考Spring5的org.apache.commons.logging.LogAdapter类

这个类中默认使用JUL，使用了static静态代码块判断是否存在SLF4J，如果存在，则使用SLF4J，
此时只需要再加上SLF4J的日志绑定器，就可以使用其他的日志框架。





