package com.likc.templateboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

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

        /*罗马数字转整数*/
        //static {
        //    HashMap<Character, Integer> hashMap = new HashMap<>();
        //    hashMap.put('I', 1);
        //    hashMap.put('V', 5);
        //    hashMap.put('X', 10);
        //    hashMap.put('L', 50);
        //    hashMap.put('C', 100);
        //    hashMap.put('D', 500);
        //    hashMap.put('M', 1000);
        //
        //    String str = "XLI";
        //    int n = str.length();
        //    int res = 0;
        //
        //    for (int i = 0; i < n; i++) {
        //        int value = hashMap.get(str.charAt(i));
        //        if (i < n-1 && value < hashMap.get(str.charAt(i+1))) { // n-1是因为后面value判断要去下一个值，不加就越界了
        //            res -= value;
        //        }else {
        //            res += value;
        //        }
        //    }
        //    System.out.println(res);
        //}

        /*最长公共前缀*/
        //static {
        //    String[] strs = new String[]{"str11","str2","st"};
        //
        //    if (strs == null || strs.length == 0) {
        //        System.out.println(" ");
        //        return;
        //    }
        //
        //    String prefix = strs[0];
        //
        //    for (int i = 1; i <strs.length; i++ ) {
        //        int min = Math.min(prefix.length(), strs[i].length());
        //        int index = 0;
        //        while (index < min && prefix.charAt(index) == strs[i].charAt(index)) {
        //            index++;
        //        }
        //        prefix = prefix.substring(0, index);
        //    }
        //    System.out.println(prefix);
        //}

        /*有效的括号*/
        //static {
        //    String s = "{()";
        //    int n = s.length();
        //    if (n % 2 == 1) {
        //        System.out.println("false");
        //        return;
        //    }
        //
        //    HashMap<Character, Character> hashMap = new HashMap<>();
        //    hashMap.put(')','(');
        //    hashMap.put('}','{');
        //    hashMap.put(']','[');
        //
        //    Deque<Character> list = new LinkedList<>();
        //
        //    for (int i = 0; i < n; i++) {
        //        char a = s.charAt(i);
        //        if (hashMap.containsKey(a)) {
        //            if (list.isEmpty() || list.peek() != hashMap.get(a)) {
        //                System.out.println("不完整false");
        //                return;
        //            }
        //            list.pop();
        //        } else {
        //            list.push(a);
        //        }
        //    }
        //
        //    System.out.println("不为空"+list.isEmpty());
        //    return;
        //}

        /*合并两个有序链表*/
        //static {
        //    public class ListNode {
        //      int val;
        //      ListNode next;
        //      ListNode() {};
        //      ListNode(int val) { this.val = val; }
        //      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        //    }
        //    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //        if(list1 == null) {
        //            return list2;
        //        } else if(list2 == null) {
        //            return list1;
        //        } else if(list1.val < list2.val) {
        //            list1.next = mergeTwoLists(list1.next, list2);
        //            return list1;
        //        } else {
        //            list2.next = mergeTwoLists(list1 , list2.next);
        //            return list2;
        //        }
        //    }
        //}

        /*删除数组重复项*/
        //static {
        //    public int removeDuplicates(int[] nums) {
        //        if(nums == null || nums.length == 0) {
        //            return 0;
        //        }
        //
        //        int s = 0;
        //        int x = 1;
        //
        //        while(x < nums.length) {
        //            if(nums[s] != nums[x]) {
        //                nums[s+1] = nums[x];
        //                s++;
        //            }
        //            x++;
        //        }
        //        return s+1;
        //    }
        //}
    }
}
