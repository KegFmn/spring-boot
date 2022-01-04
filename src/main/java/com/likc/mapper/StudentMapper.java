package com.likc.mapper;

import com.likc.po.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author likc
 * @since 2022-01-04
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
