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
    class ListNode {
      int val;
      ListNode next;
      ListNode() {};
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*两数之和等于target，返回两数数组下标*/
    int[] sumOfTwoNum(int[] nums){
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
    Boolean sameNumberAtBeginningEnd(int num){
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
    int romanTransformNum(String str){
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
    String longPrefix(String[] strs){
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
    Boolean efficientBrackets(String s){

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
            // hashmap包含弹出，如果栈为空，或者栈的第一个不等于对应的括号则不是有效括号
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
    ListNode orderListNode(ListNode list1, ListNode list2){
        if(list1 == null) {
            return list2;
        } else if(list2 == null) {
            return list1;
        } else if(list1.val < list2.val) {
            list1.next = orderListNode(list1.next, list2);
            return list1;
        } else {
            list2.next = orderListNode(list1 , list2.next);
            return list2;
        }
    }

    /*删除数组重复项，返回新数组长度，不考虑新长度后面的数组*/
    int deletRepeatArray(int[] nums){
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
    int removeElement(int[] nums, int val) {
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
    void reverseString(char[] s) {
        int n = s.length;
        for(int left = 0, right = n - 1 ; left < right; left++, right--){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }

    /*给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写*/
    boolean isPalindrome(String s) {
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
    List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, used, path, res);
        return res;
    }
    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
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
            dfs(nums, len, depth + 1, used, path, res);
            path.removeLast();
            used[i] = false;
        }
    }

    /**
     *  求子集
     *  给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）
     * @param nums
     * @return
     */
    List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, nums.length, 0, path, res);
        return res;
    }
    private void dfs(int[] nums, int len, int begin, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i=begin; i<len; i++) {
            path.add(nums[i]);
            dfs(nums, len, i+1, path, res);
            path.remove(path.size()-1);
        }
    }

    /*给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1*/
    int strStr(String haystack, String needle) {
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

    /**
     * 使用递归的二分查找
     *title:recursionBinarySearch
     *@param ints 升序数组
     *@param key 待查找关键字
     *@return 找到的位置
     */
    int binarySearch(int[] ints, int key, int low, int high){
        if (key < ints[low] || key > ints[high] || low > high){
            return -1;
        }

        int middle = (low + high) / 2;
        if (ints[middle] > key){
            return binarySearch(ints , key, low, middle-1);
        } else if (ints[middle] < key){
            return binarySearch(ints, key, middle+1, high);
        } else {
            return middle;
        }
    }

    /*冒泡排序*/
    int[] bubbingSort(int[] nums){
        if (nums.length == 0 || nums == null){
            return null;
        }

        int temp = 0;
        for (int i = 0; i < nums.length-1; i++){
            for (int j = 0; j < nums.length-1-i; j++){
                if (nums[j] > nums[j+1]){
                    temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }

    /*递归快速排序*/
    int[] fastSort(int[] nums, int left, int right){
        int i, j, t, temp;
        if(left > right){
            return null;
        }

        temp = nums[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while(i != j) { //顺序很重要，要先从右边开始找
            while(nums[j] >= temp && i < j)
                j--;
            while(nums[i] <= temp && i < j)//再找右边的
                i++;
            if(i < j)//交换两个数在数组中的位置
            {
                t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        //最终将基准数归位
        nums[left] = nums[i];
        nums[i] = temp;
        fastSort(nums, left, i-1); //继续处理左边的，这里是一个递归的过程
        fastSort(nums,i+1, right); //继续处理右边的 ，这里是一个递归的过程
        return nums;
    }
    
    ListNode twoNumAdd(ListNode l1, ListNode l2){
        // 初始化头结点，我们需要的结果是pre.next
        ListNode pre = new ListNode(0);
        // 将cur同样指向pre所指向的地址
        ListNode cur = pre;

        // 初始化进位
        int carry = 0;
        // 用或者是因为 可能有一个先为null也就是短一些
        while (l1 != null || l2 != null){
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            // 取进位
            carry = sum / 10;
            // 取下一个节点的值
            sum = sum % 10;
            // 将cur的下一节点为 ListNode z，pre和cur指向同一地址所以相当于吧pre的也改了
            cur.next = new ListNode(sum);

            // 将cur的指向地址改为ListNode z，形成pre->cur的局面
            cur = cur.next;

            // 不为空节点后移
            if (l1 != null){
                l1 = l1.next;
            }
            // 不为空节点后移
            if (l2 != null){
                l2 = l2.next;
            }

        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }

        // 返回pre的下一个节点，也就是我们需要的一整条链表
        return pre.next;
    }

    /*最长不重复子串*/
    int lengthOfLongestSubstring(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;//用于记录最大不重复子串的长度
        int left = 0;//滑动窗口左指针
        for (int i = 0; i < s.length() ; i++)
        {
            /**
             1、首先，判断当前字符是否包含在map中，如果不包含，将该字符添加到map（字符，字符在数组下标）,
             此时没有出现重复的字符，左指针不需要变化。此时不重复子串的长度为：i-left+1(+1是因为i-left是比较的下标+1才是真实长度)，与原来的maxLen比较，取最大值；

             2、如果当前字符包含在 map中，此时有2类情况：
             1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
             那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
             2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
             而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
             随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
             应该不变，left始终为2，子段变成 ba才对。

             为了处理以上2类情况，我们每次更新left，left=Math.max(left , map.get(ch)+1).
             另外，更新left后，不管原来的 s.charAt(i) 是否在最长子段中，我们都要将 s.charAt(i) 的位置更新为当前的i，
             因此此时新的 s.charAt(i) 已经进入到 当前最长的子段中！
             */
            if(map.containsKey(s.charAt(i)))
            {
                left = Math.max(left , map.get(s.charAt(i))+1);
            }
            //不管是否更新left，都要更新 s.charAt(i) 的位置！
            map.put(s.charAt(i) , i);
            maxLen = Math.max(maxLen , i-left+1);
        }

        return maxLen;
    }

    /*给定一个含有 n 个正整数的数组和一个正整数 target 。
    找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0*/
    int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /*给你两个二进制字符串，返回它们的和（用二进制表示）*/
    String addBinary(String a, String b){
        if (a == null || a.length() ==0) return b;
        if (b == null || a.length() ==0) return a;

        StringBuffer stb = new StringBuffer();

        int i = a.length()-1;
        int j = b.length()-1;

        int c = 0;
        while (i >= 0 || j >= 0){
            if (i >= 0){
                c += a.charAt(i--) - '0';
            }
            if (j >= 0){
                c += b.charAt(j--) - '0';
            }
            // 取余
            stb.append(c % 2);
            // 取整 相当于 /2
            c >>= 1;
        }

        String string = stb.reverse().toString();
        return c > 0 ? "1"+string : string;
    }

    /*给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。*/
    ListNode temp;
    boolean isPalindrome(ListNode head) {
        temp = head;
        return check(head);
    }
    boolean check(ListNode head) {
        if (head == null)
            return true;
        // temp 从头节点开始， head由于不停的递归，当到最后一个节点时开始，从后往前
        boolean res = check(head.next) && (temp.val == head.val);
        temp = temp.next;
        return res;
    }

    /*用两个栈实现一个队列*/
    class CQueue {

        private Deque<Integer> stack_in;
        private Deque<Integer> stack_out;

        public CQueue() {
            stack_in = new LinkedList<>();
            stack_out = new LinkedList<>();
        }

        public void appendTail(int value) {
            stack_in.push(value);
        }

        public int deleteHead() {
            // 当出栈为空时，把入栈全部元素导入出栈中
            if(stack_out.isEmpty()){
                while(!stack_in.isEmpty()){
                    stack_out.push(stack_in.pop());
                }
            }

            // 如果还为空，则返回-1；
            if(stack_out.isEmpty()){
                return -1;
            } else{
                // 否则弹出出栈的第一个元素
                int x = stack_out.pop();
                return x;
            }
        }
    }

    /*定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。*/
    class MinStack {

        Deque<Integer> stack;
        Deque<Integer> minStack;
        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
            // 利于比较大小
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            stack.push(x);
            // 使stack的每一位都对应minStack的最小值
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }

    /**
     *  给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
     *  ^ 异或运算
     *  任何数和 0做异或运算，结果仍然是原来的数。 a^0 = a
     *  任何数和其自身做异或运算，结果是 0. a^a = 0
     *  异或运算满足交换律和结合律。 a^b^a = b^a^a = b^0 = b
     * @param nums
     * @return
     */
    int singleNumber(int[] nums) {
        int res = 0;
        for(int i : nums){
            res ^= i;
        }
        return res;
    }

    /**
     *  编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）
     *  n & n-1 消除二进制最右边的一
     * @param n
     * @return
     */
    int hammingWeight(int n) {
        int res = 0;
        while(n != 0){
            n &= n-1;
            res++;
        }
        return res;
    }


    /**
     *  主方法
     * @param args
     */
    public static void main(String[] args) {
        AcBoy acBoy = new AcBoy();
        int[] nums = new int[]{6,3,3,4,6,7,8};
        String a = "10";
        String b = "11";
        String s = acBoy.addBinary(a, b);
        System.out.println(s);

    }
}
