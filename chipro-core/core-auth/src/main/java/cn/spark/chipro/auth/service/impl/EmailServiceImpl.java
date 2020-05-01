package cn.spark.chipro.auth.service.impl;


import cn.spark.chipro.auth.constant.SmsCodeConstant;
import cn.spark.chipro.auth.mapper.UserMapper;
import cn.spark.chipro.auth.service.EmailService;
import cn.spark.chipro.core.mail.vo.MailVo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * @author liliguang
 * @description
 * @date 2019/6/27 10:41
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void sendEmailCode(String email) {
//        UserInfo userInfo = userMapper.selectUserByEmail(email);
//        if (userInfo==null){
//             throw new CoreException(-1,"当前邮箱未绑定用户");
//        }

         //缓存Key
        String cacheKey = SmsCodeConstant.EMAIL_CODE_CACHE_PREFIX + email;

//        //防止频繁获取验证码
//        if (redisTemplate.hasKey(cacheKey)){
//            throw new CoreException("验证码还在有效期内，请勿重复获取！");
//        }
        //随机码
        String cacheSmsCode = EmailServiceImpl.randomCode();

        //存入redis
        ValueOperations valueOperations = redisTemplate.opsForValue();
        log.info("缓存 key:[{}],value:[{}],有效期:[{}天]",cacheKey,cacheSmsCode,SmsCodeConstant.SMS_CODE_CACHE_DURATION);
        valueOperations.set(cacheKey,cacheSmsCode,SmsCodeConstant.SMS_CODE_CACHE_DURATION, TimeUnit.DAYS);

        String message = String.format("少儿编程线上学习平台，邮箱登录验证码：%s，此验证码24小时内有效，请勿将验证码泄露给他人。", cacheSmsCode);
        MailVo mailVo = new MailVo();
        mailVo.setFrom("973067294@qq.com");
        mailVo.setTo(email);
        mailVo.setSubject("邮箱验证码");
        mailVo.setText(message);
        String msg = JSON.toJSONString(mailVo);
        log.info("发送消息到队列 queue_name:[{}] ,msg :[{}]", "chipro_send_mail_queue",msg);
        rabbitTemplate.convertAndSend("chipro_send_mail_queue",mailVo);
    }


    /**
     * 生成随机码
     * @return 随机码
     */
    private static String randomCode(){
        int flag = new Random().nextInt(999999);
        if (flag < 100000){
            flag += 100000;
        }
        return Integer.toString(flag);
    }
}
