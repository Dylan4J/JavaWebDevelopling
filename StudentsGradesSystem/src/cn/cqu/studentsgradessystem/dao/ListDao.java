package cn.cqu.studentsgradessystem.dao;

import cn.cqu.studentsgradessystem.domain.Student;

import java.util.List;
import java.util.Map;

public interface ListDao {
    List<Student> findAll();

    void updateSingle(Student s);

    void deleteStuById(int id);

    void add(Student stu);

    Student findById(int id);

    List<Student> findByPage(int currentPage, int rows);

    List<Student> findByPage(int currentPage, int rows, Map<String,String[]> condition);

    int getTotalCount(Map<String,String[]> condition);
}
