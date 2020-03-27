package cn.cqu.studentsgradessystem.service;

import cn.cqu.studentsgradessystem.domain.Administator;
import cn.cqu.studentsgradessystem.domain.PageBean;
import cn.cqu.studentsgradessystem.domain.Student;

import java.util.List;
import java.util.Map;

/**
 * 数据展示页面查询接口
 */
public interface ListService {
    /**
     * 查询数据库中的所有学生数据
     * @return
     */
    public List<Student> finAll();

    /**
     * 修改数据库中某个学生的信息
     */
    void updataSingle(Student s);

    void deleteSingle(int id);

    void add(Student stu);

    Student findById(int id);

    void delSelectedStu(String[] checks);

    PageBean findByPage(int currentPage, int rows, Map<String,String[]> condition);


    Administator findByName(String username);
}
