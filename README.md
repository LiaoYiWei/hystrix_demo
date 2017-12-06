# Hystrix Demo

## 项目介绍
* hystrixdemo_api  
  定义RPC接口
* hystrixdemo_server  
  * 提供Dubbo服务
    * Config接口提供创建类型接口随机抛出异常设置，抛出概率设置为0-10分别表示0%-100%  
      提供查询类接口超时时间设置0-maxInt
    * DemoOrderService提供模拟的订单服务
    * DemoUserService提供模拟的用户服务
* hystrixdemo_example
  * 使用隔离机制调用hystrixdemo_server的服务
* hystrix-dashboard  
  * 监控仪表板，提供请求数据统计，断路器状态，线程池使用等数据的展示
  * 使用参照hystrix-dashboard下的README.md
  
## Hystrix介绍
### 什么是Hystrix
在分布式环境中，服务依赖中的一些不可避免地会失败.Hystrix是一个库，通过添加延迟容差和容错逻辑，帮助您控制这些分布式服务之间的交互。 Hystrix通过隔离服务之间的访问点，阻止它们之间的级联故障以及提供备用选项来实现这一点，所有这些都可以提高系统的整体弹性。
### Hystrix是用来做什么的
* 保护并控制通过第三方客户端库访问的依赖关系（通常通过网络）的延迟和失败。
* 阻止复杂的分布式系统的级联失败
* 快速失败并且迅速恢复
* 可能的情况下回退并优雅地降级
* 启用接近实时的监控，警报和操作控制。
### 为什么需要Hystrix
在大中型分布式系统中，通常系统很多依赖(HTTP,hession,Netty,Dubbo等)，如下图:
![soa-1-640](https://raw.githubusercontent.com/LiaoYiWei/hystrix_demo/master/doc/soa-1-640.png)  
在高并发访问下,这些依赖的稳定性与否对系统的影响非常大,但是依赖有很多不可控问题:如网络连接缓慢，资源繁忙，暂时不可用，服务脱机等.
如下图：QPS为50的依赖 I 出现不可用，但是其他依赖仍然可用.
![soa-2-640](https://raw.githubusercontent.com/LiaoYiWei/hystrix_demo/master/doc/soa-2-640.png)  
当依赖I 阻塞时,大多数服务器的线程池就出现阻塞(BLOCK),影响整个线上服务的稳定性.如下图:
![soa-3-640](https://raw.githubusercontent.com/LiaoYiWei/hystrix_demo/master/doc/soa-3-640.png)  
在复杂的分布式架构的应用程序有很多的依赖，都会不可避免地在某些时候失败。高并发的依赖失败时如果没有隔离措施，当前应用服务就有被拖垮的风险。  
例如:一个依赖30个SOA服务的系统,每个服务99.99%可用。
```  
99.99%的30次方 ≈ 99.7%  
0.3% 意味着一亿次请求 会有 3,000,00次失败  
换算成时间大约每月有2个小时服务不稳定.  
随着服务依赖数量的变多，服务不稳定的概率会成指数性提高.  
```
### hystrix如何解决这个问题
Hystrix包装每个基础依赖关系时，上图中所示的体系结构更改为类似于下图。 每个依赖都是相互隔离的，在延迟发生时可以使其饱和的资源受到限制，并在降级逻辑中进行了说明，该降级逻辑决定在依赖关系中发生任何类型的故障时要做出什么响应：
![soa-4-isolation-640](https://raw.githubusercontent.com/LiaoYiWei/hystrix_demo/master/doc/soa-4-isolation-640.png)

### hystrix如何实现的
* 将所有对外部系统（或“依赖关系”）的调用包装在HystrixCommand或HystrixObservableCommand对象中，该对象通常在单独的线程中执行（这是命令模式的一个示例）。
* 调用时间比设定的临界值更长将调用超时。 有一个默认值，但是对于大多数依赖项，您可以通过“属性”来自定义这些超时值，以使它们比每个依赖项的实测99.5％百分点性能略高。
* 为每个依赖维护一个小的线程池（或信号量）; 如果它变满了，发往这个依赖关系的请求将被立即拒绝而不是排队。
* 计量成功，失败（客户端抛出的异常），超时和线程拒绝。
* 跳闸断路器可以在一段时间内停止对特定服务的所有请求，无论是手动还是自动，如果服务的错误百分比超过阈值。
* 请求失败，被拒绝，超时或短路时执行备用逻辑。
* 几乎实时监控指标和配置更改。

### command流程图
![hystrix-command-flow-chart](https://raw.githubusercontent.com/LiaoYiWei/hystrix_demo/master/doc/hystrix-command-flow-chart.png)

### 断路器流程图
![circuit-breaker-1280](https://raw.githubusercontent.com/LiaoYiWei/hystrix_demo/master/doc/circuit-breaker-1280.png)

### 线程池隔离

 
















  
  

      
    
  
  
