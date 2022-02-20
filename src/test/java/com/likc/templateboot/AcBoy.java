package com.likc.templateboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.*;

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

    /*删除数组重复项，返回新数组长度，不考虑新长度后面的数组*/
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

    /*给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度*/
    static int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;

        while(right < nums.length){
            if(nums[right] != val){
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        return left;
    }

    /*输入的字符串反转过来，输入字符串以字符数组的形式给出*/
    static void reverseString(char[] s) {
        int n = s.length;
        for(int left = 0, right = n - 1 ; left < right; left++, right--){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }

    /*给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写*/
    static boolean isPalindrome(String s) {
        int n = 0;
        int m = s.length() - 1;

        while(n < m){
            // 不是字母和数字直接跳过
            while(n < m && !Character.isLetterOrDigit(s.charAt(n))){
                n++;
            }
            while(n < m && !Character.isLetterOrDigit(s.charAt(m))){
                m--;
            }
            if(Character.toLowerCase(s.charAt(n)) != Character.toLowerCase(s.charAt(m)))           {
                return false;
            }
            n++;
            m--;
        }
        return true;
    }

    /*
    *
    * 全排列
    * 状态变量：递归到第几层    depth
    *         已经选了什么数  path
    *         布尔数组       used
    * */
    static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, res);
        return res;
    }
    private static void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]){
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }

    }

    /*给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1*/
    static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        // i+m 的意思是匹配到原字符串剩余长度不足匹配字符串时停止
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> list = permute(nums);
        System.out.println(list.toString());
    }
}
