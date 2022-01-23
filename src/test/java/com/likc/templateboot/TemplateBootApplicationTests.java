package com.likc.templateboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // websocket测试必加，因为需要真实的tomcat
class TemplateBootApplicationTests {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        String str = "2";
        //System.out.println(str.length() - 1);
        //System.out.println(str.charAt(str.length() - 1));
        //System.out.println(str.charAt(0) - '0');
        System.out.println((int)str.charAt(0));
        System.out.println((int)'0');
    }

    @Test
    void logTest() {
        log.info("我是info日志");
        log.warn("我是warn日志");
        log.error("我是error日志");
    }

    @Test
    void jackon() throws JsonProcessingException {
        String message = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\",\"age\":30}";
        // 解析发送的报文
        JsonNode jsonNode = objectMapper.readTree(message);
        // 追加发送人(防止串改)
        ((ObjectNode)jsonNode).put("fromUserId","1");
        // 获取发给谁
        String toUserId = ((ObjectNode)jsonNode).get("firstname").toString();
        System.out.println(toUserId);
        System.out.println(objectMapper.writeValueAsString(((ObjectNode)jsonNode)));
    }

    @Test
    void study() {

        /*两数之和等于target，返回两数数组下标*/
        //static {
        //    int [] nums = new int[]{1,2,3,4};
        //    int target = 5;
        //    HashMap<Integer, Integer> hashMap = new HashMap<>();
        //    for(int i = 0; i< nums.length; i++) {
        //        if (hashMap.containsKey(target - nums[i])) {
        //            System.out.println((new int[]{hashMap.get(target-nums[i]), i}).toString());
        //            break;
        //        }
        //        hashMap.put(nums[i], i);
        //    }
        //}

        /*回文数，判断返回boolen*/
        //static {
        //    int x = 121;
        //
        //    if (x < 0 || (x % 10 == 0 && x != 0)) {
        //        System.out.println(false);
        //        return;
        //    }
        //
        //    int reverseNumber = 0;
        //    while (x > reverseNumber) {
        //        reverseNumber = reverseNumber * 10 + x % 10;
        //        x /= 10;
        //    }
        //
        //    System.out.println(x == reverseNumber || x == reverseNumber / 10); //revertedNumber/10 是为了去除奇数的中位数
        //    return;
        //}




    }
}
