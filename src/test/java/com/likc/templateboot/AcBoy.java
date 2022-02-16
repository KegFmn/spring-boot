package com.likc.templateboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

@Slf4j
@SpringBootTest
public class AcBoy {

    /**
     *  链表对象
     */
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {};
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*两数之和等于target，返回两数数组下标*/
    static int[] sumOfTwoNum(int[] nums){
        int target = 5;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return (new int[]{hashMap.get(target-nums[i]), i});
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }

    /*回文数，判断返回boolen*/
    static Boolean SameNumberAtBeginningEnd(int num){
        if (num < 0 || (num % 10 == 0 && num != 0)) {
            return false;
        }

        int reverseNumber = 0;
        while (num > reverseNumber) {
            reverseNumber = reverseNumber * 10 + num % 10;
            num /= 10;
        }

        //revertedNumber/10 是为了去除奇数的中位数
        return num == reverseNumber || num == reverseNumber / 10;
    }

    /*罗马数字转整数*/
    static int romanTransformNum(String str){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('I', 1);
        hashMap.put('V', 5);
        hashMap.put('X', 10);
        hashMap.put('L', 50);
        hashMap.put('C', 100);
        hashMap.put('D', 500);
        hashMap.put('M', 1000);

        int n = str.length();
        int res = 0;

        for (int i = 0; i < n; i++) {
            int value = hashMap.get(str.charAt(i));
            if (i < n-1 && value < hashMap.get(str.charAt(i+1))) { // n-1是因为后面value判断要去下一个值，不加就越界了
                res -= value;
            }else {
                res += value;
            }
        }
        return res;
    }

    /*最长公共前缀*/
    static String longPrefix(String[] strs){
        if (strs == null || strs.length == 0) {
            return " ";
        }

        String prefix = strs[0];

        for (int i = 1; i <strs.length; i++ ) {
            int min = Math.min(prefix.length(), strs[i].length());
            int index = 0;
            while (index < min && prefix.charAt(index) == strs[i].charAt(index)) {
                index++;
            }
            prefix = prefix.substring(0, index);
        }
        return prefix;
    }

    /*有效的括号*/
    static Boolean efficientBrackets(String s){

        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        HashMap<Character, Character> hashMap = new HashMap<>();
        hashMap.put(')','(');
        hashMap.put('}','{');
        hashMap.put(']','[');

        Deque<Character> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            if (hashMap.containsKey(a)) {
                if (list.isEmpty() || list.peek() != hashMap.get(a)) {
                    return false;
                }
                list.pop();
            } else {
                list.push(a);
            }
        }
        return list.isEmpty();
    }

    /*合并两个有序链表*/
    static ListNode OrderListNode(ListNode list1, ListNode list2){
        if(list1 == null) {
            return list2;
        } else if(list2 == null) {
            return list1;
        } else if(list1.val < list2.val) {
            list1.next = OrderListNode(list1.next, list2);
            return list1;
        } else {
            list2.next = OrderListNode(list1 , list2.next);
            return list2;
        }
    }

    /*删除数组重复项*/
    static int deletRepeatArray(int[] nums){
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int s = 0;
        int x = 1;

        while(x < nums.length) {
            if(nums[s] != nums[x]) {
                nums[s+1] = nums[x];
                s++;
            }
            x++;
        }
        return s+1;
    }

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);
    }
}
