package com.likc.service.impl;

import com.likc.po.User;
import com.likc.mapper.UserMapper;
import com.likc.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likc
 * @since 2022-02-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void asyntest() {
        try {
            System.out.println("异步入库开始");
            Thread.sleep(10000);
            System.out.println("异步入库结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
