package com.likc.templateboot.service.impl;

import com.likc.templateboot.po.Student;
import com.likc.templateboot.mapper.StudentMapper;
import com.likc.templateboot.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likc
 * @since 2021-12-27
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
