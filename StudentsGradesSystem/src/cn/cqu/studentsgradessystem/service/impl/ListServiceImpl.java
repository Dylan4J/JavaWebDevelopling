package cn.cqu.studentsgradessystem.service.impl;

import cn.cqu.studentsgradessystem.dao.ListDao;
import cn.cqu.studentsgradessystem.dao.impl.ListDaoImpl;
import cn.cqu.studentsgradessystem.domain.PageBean;
import cn.cqu.studentsgradessystem.domain.Student;
import cn.cqu.studentsgradessystem.service.ListService;

import java.util.List;
import java.util.Map;

public class ListServiceImpl implements ListService {
    private ListDao listDao = new ListDaoImpl();
    @Override
    public List<Student> finAll() {

        return listDao.findAll();
    }

    @Override
    public void updataSingle(Student s) {
        listDao.updateSingle(s);
    }

    @Override
    public void deleteSingle(int id) {
        listDao.deleteStuById(id);
    }

    @Override
    public void add(Student stu) {
        listDao.add(stu);
    }

    @Override
    public Student findById(int id) {
        return listDao.findById(id);
    }

    @Override
    public void delSelectedStu(String[] checks) {
        for (int i=0;i<checks.length;i++) {
            listDao.deleteStuById(Integer.parseInt(checks[i]));
        }
    }

    @Override
    public PageBean findByPage(int currentPage, int rows, Map<String,String[]> condition) {
        PageBean pb = new PageBean();
        int totalCount = listDao.getTotalCount(condition);//总记录数
        int totalPage = totalCount%rows == 0 ? totalCount/rows : totalCount/rows + 1;
        if (currentPage <= 0){
            currentPage=1;
        }
        if (currentPage>totalPage){
            currentPage=totalPage;
        }
        List<Student> stusByPage = listDao.findByPage(currentPage, rows,condition);
        pb.setStuByPage(stusByPage);
        pb.setRows(rows);
        pb.setCurrentPage(currentPage);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        return pb;
    }


}
