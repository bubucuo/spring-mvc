curl localhost:8081/user/add -X POST -H 'content-type: application/json' -d '{"username":"test"}'

curl 'localhost:8081/user/get?name=test'

curl localhost:8081/user/update -X POST -H 'content-type: application/json' -d '{"username":"111", "id":"1"}'

curl localhost:8081/user/delete -X POST -H 'content-type: application/json' -d '{"id":"1"}'

curl 'localhost:8081/user/list?pageNo=1&pageSize=10'

// 登录接口 登录成功时候 userService中会生成一个唯一token 缓存到内存中
curl localhost:8081/login -X POST -H 'content-type: application/json' -d '{"username":"admin","password":"111111"}'

// 退出登录  header携带login接口返回的token，userService会将该token值从缓存中清除
curl localhost:8081/logout -X POST -H 'token: bca8ff46-a1b4-49b8-b205-fe9f21a969cd'

// 检测用户登录是否成功，header携带login接口返回的token，token无效会返回需要登录的提示，LoginInterceptor拦截类会在调用接口controller前检查token是否有效
curl localhost:8081/authDemo  -H 'token: bca8ff46-a1b4-49b8-b205-fe9f21a969cd'




