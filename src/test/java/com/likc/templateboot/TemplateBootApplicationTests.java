package com.likc.templateboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.*;

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
    void StringTest() {
        int c = 10;
        c>>=1;
        System.out.println(c);
        Deque<Integer> integers = new ArrayDeque<>();
        integers.addLast(12);
        integers.peekFirst();

        Deque<Object> list = new LinkedList<>();


    }

    @Test
    void Test(){
        String str = "addc";
        Deque<Character> stack = new LinkedList<>();


        for (int i = 0; i < str.length(); i++){
            char chr = str.charAt(i);
            if (!stack.isEmpty()){
                if (stack.peekFirst() == chr){
                    stack.pollFirst();
                    continue;
                }
            }
            stack.addFirst(chr);
        }
        System.out.println(!stack.isEmpty());
    }

}
