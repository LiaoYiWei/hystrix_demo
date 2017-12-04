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
  
  

      
    
  
  
