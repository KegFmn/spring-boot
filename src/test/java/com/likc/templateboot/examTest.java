package com.likc.templateboot;

import java.util.Scanner;

/**
 * @author likc
 * @date 2022/2/20
 * @description
 */
public class examTest {

    /**
     *  next()读取字符串 nextLine读取字符串 nextInt()读取数字，nextfloat()读取float
     *  next()、nextInt、nextfloat()遇到空格、回车就会停止输入，nextLine()遇到回车就会结束，不过会把回车也读取
     * @param args
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int[] ints = new int[n];
            for (int i = 0; i < n; i++){
                ints[i] = in.nextInt();
            }
        }

        in.close();

    }

}
