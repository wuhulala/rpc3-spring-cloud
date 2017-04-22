#rpc 第三弹

> spring cloud 是我关注了好久的 ，虽然上个月开始写些demo ，现在准备深入学习，在有springboot的基础，并且有全家桶，何乐而不用。

## config

#### 1. 可以在config 服务中写入其他服务的的配置

some.yml
```yaml
my:
  message:  wo shi xueaohui
```

在 some 服务中的代码

```java
@RestController
public class SomeApplication {

    @Value("${my.message}")
    private String message;
    
    @RequestMapping(value = "/getsome")
    public String getSome(){
        return message;
    }
}

```

#### 2. 查看config 的内容

通用配置
> http://ip:configPort/appName/propName

在咱们系统的

> http://localhost:8888/some/my

```json
{"name":"some","profiles":["my"],"label":null,"version":null,"state":null,"propertySources":[{"name":"classpath:/config/some.yml","source":{"my.message":"wo shi xueaohui"}}]}
```



#### 3. feign坑

1. 不支持@GetMapping
2. @PathVariable 需要设置value


#### 4. ribbon
>客户端负载均衡组件

1. 客户端
```java
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
```

2. 为不同的客户端设置不同的策略
```java
//设置不同的服务不同的负载均衡效果
//1. some服务 轮询策略
//2. person服务 随机策略
@RibbonClients({
        @RibbonClient(name = "person", configuration = PersonConfiguration.class),
        @RibbonClient(name = "some", configuration = SomeConfiguration.class)
})
```

>tip:
>1. 不能把这些配置类放入到springIOC容器能够扫描到的地方，因为他会覆盖掉所有策略，如果有多个策略被扫描到了，并且覆盖的策略为spring容器最后注册的策略。可以认为是随机的
>2. 如果由于项目结构什么的必须要放到这里面，那么可以想办法让容器不注册它，比如加个注解，把有注解设置为@ComponentScan的filter

```java
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})
```

3. ribbon 不与eureka联用 配置的方式 比较懒 官方文档

如果当前没有依赖eureka 就是没有添加eureka的包

手动设置person的访问地址
```yaml
person:
  ribbon:
    listOfServers: MSI.hs.handsome.com.cn:person:8080 , MSI.hs.handsome.com.cn:person:8081 , MSI.hs.handsome.com.cn:person:8082
```

如果我们添加了eureka的包,还需要第二步配置显式的配置

```yaml
ribbon:
  eureka:
   enabled: false
```

