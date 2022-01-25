package com.likc.service.impl;

import com.likc.po.Student;
import com.likc.mapper.StudentMapper;
import com.likc.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likc
 * @since 2022-01-25
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
