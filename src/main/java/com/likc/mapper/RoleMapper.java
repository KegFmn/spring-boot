package com.likc.mapper;

import com.likc.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author likc
 * @since 2024-03-11
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> findPermsByUserId(Long userId);
}
