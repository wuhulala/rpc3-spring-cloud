#rpc 第三弹

> spring cloud 是我关注了好久的 ，虽然上个月开始写些demo ，现在准备深入学习，在有springboot的基础，并且有全家桶，何乐而不用。

## config

可以在config 服务中写入其他服务的的配置 ，比如some.yml

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