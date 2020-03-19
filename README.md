# spring-boot-drools
java,spring,spring-boot,drools


# 目标
简单 添加购物车功能
如果遇到以下其中一个问题 则 跳出 规则，不在执行后续规则，返回错误信息

1. 购买数量

2. 最大购买数

3. 最小购买数

4. 库存

5. 商品是否存在

6. 商品名称包含 【奶粉】关键字不允许购买

# 访问链接

goodsId: 商品id

num: 购买数量

name: 商品名称

```bash
http://localhost:8080/demo
```
url 上没有任何参数时，使用默认数据，默认 `cart` 数据 在 `CartController` 中 定义

访问后返回
```json
{"code":500,"message":"商品名称包含 【奶粉】关键字不允许购买","data":null}
```


```bash
http://localhost:8080/demo?goodsId=2&num=2&name=
```
url 上有参数时，使用url参数

访问后返回
```json
{"code":500,"message":"商品名称不能为空","data":null}
```

```bash
http://localhost:8080/demo?goodsId=2&num=2&name=德国爱他美白金版1+段奶粉
```
url 上有参数时，使用url参数

访问后返回
```json
{"code":500,"message":"商品名称包含 【奶粉】关键字不允许购买","data":null}
```


# 文章

https://blog.csdn.net/fenglailea/article/details/104972256