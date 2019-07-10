package org.xiaoyao.bigdata.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengJie
 * @desciption
 * @date 2019/1/29 18:28
 **/
@Configuration
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes = "";
    @Value("${spring.redis.timeout}")
    private int timeout = 0;

    @Value("${spring.redis.commandTimeout}")
    private int commandTimeout = 0;

    @Value("${spring.redis.password}")
    private String password = "";

    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive=1;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int maxWait=1;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle=1;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle=1;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        List<RedisNode> list=new ArrayList<>();
        String[] hostAndPortList=nodes.split(",");
        String host="";
        String port="";
        // 添加节点
        for(String  hostAndPort:hostAndPortList){
            String[] temp=hostAndPort.split(":");
            host=temp[0];
            port=temp[1];
            RedisNode redisNode =new RedisNode(host,Integer.parseInt(port));
            list.add(redisNode);
        }

        redisClusterConfiguration.setClusterNodes(list);
        redisClusterConfiguration.setPassword(RedisPassword.of(password));
        redisClusterConfiguration.setMaxRedirects(5);
        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration);
        return factory;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
