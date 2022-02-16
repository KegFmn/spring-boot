package com.likc.mapper;

import com.likc.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author likc
 * @since 2022-02-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
