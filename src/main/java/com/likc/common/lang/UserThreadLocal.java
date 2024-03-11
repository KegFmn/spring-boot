package com.likc.common.lang;

import com.likc.po.User;

/**
 * @author likc
 */
public class UserThreadLocal {
    private static final ThreadLocal<User> OP_USER_THREAD = new ThreadLocal<>();

    public static void set(User user) {
        OP_USER_THREAD.set(user);
    }

    public static User get() {
        return OP_USER_THREAD.get();
    }

    // 防止内存泄漏
    public static void remove() {
        OP_USER_THREAD.remove();
    }
}
