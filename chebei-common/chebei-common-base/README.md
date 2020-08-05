# common-base 基础模块

## redis缓存

- 请使用@RedisCache注解，cacheKey必填（SpEL格式）
- 参考例子：CacheServiceImpl.getSession(String);参数token作为cacheKey的一部分。
- 缓存方法的类中不能有ams的@Mapper注解，会冲突（都做切片）。