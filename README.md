# Spring MVC Custom

> A Simple Implementation of Spring MVC

## 介绍

  基于Spring IOC 和 原生Servlet 完成的一个简单的 Spring MVC。

  包含简单处理流程和注解处理流程。

## 项目整体结构

```text

├── adapter           适配器实现
├── annotation        自定义注解实现
├── handler           处理器实现，包含Controller测试
├── ioc               IOC容器实现，基于Spring本身
├── model             ModelAndView
├── servlet           分发器
```



## 初始化流程

对于这个自制的 `Spring MVC` 框架的初始化流程来说，主要可以分为以下几个步骤。

1. 读取 `web.xml` 文件，进行 `DispatcherServlet` 的初始化。
2. 对于 `DispatcherServlet` 的初始化主要分为两步——`BeanFactory` 和 `handlerMappings` 、 `handlerAdapters` 的初始化。
3. 对于 `BeanFactory` 的初始化，主要是获取 `springmvc.xml` 配置文件的路径，然后通过 `spring` 进行容器初始化。需要注意的在 `IOC容器` 进行初始化的时候需要进行 `RequestMappingHandlerMapping` 类的初始化，而这个类的初始化方法会依赖于整个 `IOC容器`(也就是说它会在初始化的时候就去容器中读取数据，但是这个时候 `IOC容器` 还未初始化完毕，所以就会抛出空指针异常)，所以我们需要设置这个类进行懒加载初始化，也就是 `lazy-init= true`。
4. 当 `IOC容器` 初始化完毕之后，`DispatcherServlet` 会从容器中获取所有的 `HandlerMapping` 和 `HandlerAdapter` 然后放入自己对应的 `list` 中。

![自定义mvc框架的初始化流程](C:\Users\lin\Desktop\自定义mvc框架的初始化流程.jpg)



## 请求流程

对于请求的流程来说，主要核心就是通过 `request` 在 `handlerMapping` 中寻找对应的处理器，并封装成处理器适配器最终得到处理。

1. 进入 `doDipatch` 方法然后通过 `request` 寻找对应的处理器。其中这里使用过遍历 `handlerMappings` 来获取对应的处理器的。
2. 将处理器封装为处理器适配器 `handlerAdapter` ，然后通过处理器适配器的统一方法进行处理。请注意这里最终还是调用的原处理器的处理方法，而做适配器的原因是因为对于 `handler` 来说，可以来自不同框架(本身我们也是定义的 `Object`)，所以为了良好的扩展性，框架中使用了适配器模式。
3. 等到处理完成的时候，我们可以根据结果将内容写入`response` 中。

![自定义mvc框架的请求流程](C:\Users\lin\Desktop\自定义mvc框架的请求流程.jpg)

## 使用简介

### 运行初始化项目

您可以在直接拉取项目并运行，请确保本机在 `8080` 端口没有运行其余项目，当然你也可以更改端口，因为本项目是运行在 `Tomcat` 提供的 `maven` 插件中的，所以需要您在 `pom.xml` 文件中进行相应的修改。

```xml
<plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat7-maven-plugin</artifactId>
  <version>2.2</version>
  <configuration>
    <port>8080</port>
    <path>/</path>
  </configuration>
</plugin>
```

> 还需要注意的是，请在运行之前，通过 `tomcat7:run` 命令进行配置，否则无法运行成功。

### 实践

推荐您输入 `127.0.0.1:8080/helloWorld` 在浏览器中进行访问，并且在 `HelloWorldHandler` 中打下相应的断点调试。

如果需要自定义的话，您需要将自己实现的类写入 `springmvc.xml` 配置文件中。

如果需要查看整个项目的流程，推荐您在 `DispatcherServlet` 中的初始化方法中进行断点调试。