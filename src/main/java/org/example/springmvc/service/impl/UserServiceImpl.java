package org.example.springmvc.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.mapper.UserMapper;
import org.example.springmvc.model.PageModel;
import org.example.springmvc.model.PageRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 *  @Transactional 注解加载类上 则类内所有的方法，加在方法上则只作用与该方法二不会影响其他方法
 * @Slf4j日志框架 可以查看target/下的UserServiceImpl文件： private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
 * @Service把当前 UserServiceImpl 注册为一个Spring Bean
 */
@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService {

//    把 UserMapper 这个Spring Bean Set到这里，其实就是依赖注入。相当于以前的实例化方法(new)参考。
    @Autowired
    private UserMapper userMapper;

    /**
     * 线程安全 k-v
     * 缓存登录的session
     * 当前仅为内存保存，用户数量众多时候 可考虑其他缓存方式，例如redis/db等
     */
    private Cache<String, User> sessionCache;

    /**
     * spring 在初始化对象时候调用该方法对变量sessionCache进行初始化
     */
    @PostConstruct
    public void init() {
        log.info("init GuavaServiceImpl");
        sessionCache = CacheBuilder.newBuilder()
                .maximumSize(10000)  // 数量最大值
                .expireAfterWrite(Duration.ofHours(24)) // 有效期24小时
                .concurrencyLevel(20) // 数值越大并发性能越好 越耗费cpu与内存资源
                .build();
    }

    @Override
    public PageModel<User> list(PageRequest pageRequest) {
        Long total = userMapper.count();
        List<User> list = userMapper.find(pageRequest.getPageNo() -1, pageRequest.getPageSize());
        return new PageModel<User>().setContent(list)
                .setTotal(total)
                .setPageNo(pageRequest.getPageNo())
                .setPageSize(pageRequest.getPageSize());
    }

    @Override
    public User add(User user) {
        userMapper.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        userMapper.update(user);
        return user;
    }

    @Override
    public Object delete(User user) {
        userMapper.delete(user);
        return user;
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public User login(User param) {

        if (!StringUtils.hasText(param.getUsername())){
            throw new RuntimeException("用户名不能为空");
        }
        if (!StringUtils.hasText(param.getPassword())){
            throw new RuntimeException("密码不能为空");
        }
        User user = userMapper.findByName(param.getUsername());
        if (user == null){
            throw new RuntimeException("用户不存在");
        }
        // 该处暂使用明文比对，生产项目可使用rsa算法 或者其他算法来替代明文比对
        if (!Objects.equals(user.getPassword(), param.getPassword())) {
            throw new RuntimeException("密码不正确");
        }
        // 密码不能返回，此处置空
        user.setPassword(null);

        // 生成 唯一id 作为鉴权token，缓存到sessionCache中
        user.setToken(UUID.randomUUID().toString());
        sessionCache.put(user.getToken(), user);
        log.info("用户:{} 登录成功", user.getUsername());
        return user;//param;
    }

    @Override
    public void logout(String token) {
        User user = sessionCache.getIfPresent(token);
        if (user != null){
            log.info("用户:{} 退出登录", user.getUsername());
            sessionCache.invalidate(token);
        }
    }

    @Override
    public User findByToken(String token) {
        try {
            return sessionCache.getIfPresent(token);
        }catch (Exception e) {
            return null;
        }
    }
}
