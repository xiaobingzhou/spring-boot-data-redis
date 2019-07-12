package com.bell.springboot.web;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class RedisController {
	
	@Autowired
	RedisTemplate redisTemplate;


	@GetMapping({"/","/index"})
	public String index(Map<String, Object> model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = session.getId();
		log.info("redis session id=" + id);
		model.put("keys", keys("*"));
		return "index";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam String wd, Map<String, Object> model) {
		model.put("keys", keys(wd));
		return "index";
	}
	
	@ResponseBody
	@GetMapping("/keys/{pattern}")
	public Set<String> keys(@PathVariable(name="pattern") String pattern) {
		return redisTemplate.keys(pattern);
	}
	
	@ResponseBody
	@GetMapping("/{key}")
	public String key(@PathVariable(name="key") String key) {
		Object object = redisTemplate.opsForValue().get(key);
//		log.info(map.toString());
		
		String string = (String) redisTemplate.opsForValue().get(key);
		log.info(string);
//		RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
//		Object deserialize = valueSerializer.deserialize(string.getBytes());
//		log.info(deserialize.toString());
		return string;
	}
	
	
	@ResponseBody
	@PostMapping("/{key}")
	public String add(
			@PathVariable(name="key") String key,
			@RequestParam(name="offset", required=false, defaultValue = "-1") Long offset,
			@RequestBody String value
			) {
		redisTemplate.opsForValue().set(key, value, offset, TimeUnit.MILLISECONDS);
		return value;
	}
	
	@ResponseBody
	@DeleteMapping("/{key}")
	public String del(@PathVariable(name="key") String key) {
		redisTemplate.delete(key);
		return "{\"code\":0,\"msg\":\"ok\",\"data\":\""+key+"\"}";
	}
}
