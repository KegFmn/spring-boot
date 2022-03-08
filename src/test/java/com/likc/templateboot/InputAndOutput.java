package com.likc.templateboot;

import java.util.Scanner;

/**
 * @author likc
 * @date 2022/2/20
 * @description
 */
public class InputAndOutput {

    /**
     *  next()读取字符串 nextLine读取字符串 nextInt()读取数字，nextfloat()读取float
     *  next()、nextInt、nextfloat()遇到空格、回车就会停止输入，nextLine()遇到回车就会结束，不过会把回车也读取
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 链接：https://ac.nowcoder.com/acm/contest/5657/D
         *     来源：牛客网
         *
         *     输入数据包括多组。
         *     每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
         *     接下来n个正整数,即需要求和的每个正整数。
         *
         * 输入
         *    4 1 2 3 4
         *    5 1 2 3 4 5
         *    0
         * 输出
         *    10
         *    15
         */
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int t = in.nextInt();
            if(t == 0) break;
            int sum = 0;
            for (int i = 0; i < t; i++){
                sum += in.nextInt();
            }
            System.out.println(sum);
        }


        /**
         * 链接：https://ac.nowcoder.com/acm/contest/5657/G
         *    来源：牛客网
         *
         *       输入数据有多组, 每行表示一组输入数据。
         *       每行不定有n个整数，空格隔开。(1 <= n <= 100)。
         *
         *    输入
         *       1 2 3
         *       4 5
         *       0 0 0 0 0
         *    输出
         *       6
         *       9
         *       0
         */
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextLine()){
            int num = 0;
            String[] s = sc.nextLine().split(" ");
            for (int i=0; i<s.length; i++){
                num += Integer.valueOf(s[i]);
            }
            System.out.println(num);
        }

    }

}
