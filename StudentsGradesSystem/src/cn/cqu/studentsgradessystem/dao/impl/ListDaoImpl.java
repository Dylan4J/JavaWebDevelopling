package cn.cqu.studentsgradessystem.dao.impl;

import cn.cqu.studentsgradessystem.dao.ListDao;
import cn.cqu.studentsgradessystem.domain.Student;
import cn.cqu.studentsgradessystem.utils.JDBCUtil;
import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListDaoImpl implements ListDao {
    private  JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    /**
     * 查询数据库中所有的学生记录
     * @return
     */
    @Override
    public List<Student> findAll() {
        String sql = "select * from students";
        List<Student> students = template.query(sql, new BeanPropertyRowMapper<>(Student.class));
        return students;
    }

    /**
     * 修改某个学生的信息
     * @param s
     */
    @Override
    public void updateSingle(Student s) {
        String sql = "update students set name = ?," +
                "gender = ?,address = ?,chinese = ?,math = ?,english = ? where id = ?";
        template.update(sql,s.getName(),
                s.isGender(),s.getAddress(),s.getChinese(),s.getMath(),s.getEnglish(),s.getId());
    }

    /**
     * 删除某个学生的信息
     * @param id
     */
    @Override
    public void deleteStuById(int id) {
        String sql = "delete from students where id = ?";
        template.update(sql,id);

    }

    /**
     * 添加某个学生的信息进入数据库
     * @param stu
     */
    @Override
    public void add(Student stu) {
        String sql = "insert into students(name,gender,address,chinese,math,english) " +
                "values(?,?,?,?,?,?)";
        template.update(sql,stu.getName(),stu.isGender(),
                stu.getAddress(),stu.getChinese(),stu.getMath(),stu.getEnglish());

    }

    /**
     * 通过该id查询某个学生
     * @param id
     * @return
     */
    @Override
    public Student findById(int id) {
        String sql = "select * from students where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
    }

    @Override
    public List<Student> findByPage(int currentPage, int rows) {
        String sql = "select * from students limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(Student.class),
                (currentPage - 1) * rows, rows);


    }

    @Override
    public List<Student> findByPage(int currentPage, int rows, Map<String,String[]> condition) {
        StringBuilder s = new StringBuilder("select * from students where 1 = 1");
        List paras = new ArrayList();
        for (Map.Entry<String,String[]> entry : condition.entrySet()) {
            if("currentPage".equals(entry.getKey()) || "rows".equals(entry.getKey())){
                continue;
            }
            String value = entry.getValue()[0];
            if ("男".equals(value)){
                value = "false";
            }
            if ("女".equals(value)){
                value = "true";
            }
            if (value!=null && !"".equals(value)){
                s.append(" and "+entry.getKey()+" like ?");
                paras.add("%"+value+"%");
            }
        }
        paras.add((currentPage - 1) * rows);
        paras.add(rows);
        s.append(" limit ?,?");
        String sql = s.toString();
        return template.query(sql, new BeanPropertyRowMapper<>(Student.class),paras.toArray());

    }

    @Override
    public int getTotalCount(Map<String,String[]> condition) {
        StringBuilder s = new StringBuilder("select count(*) from students where 1 = 1");
        List paras = new ArrayList();

        for (Map.Entry<String,String[]> entry : condition.entrySet()) {
            if("currentPage".equals(entry.getKey()) || "rows".equals(entry.getKey())){
                continue;
            }
            String value = entry.getValue()[0];
            if ("男".equals(value)){
                value = "false";
            }
            if ("女".equals(value)){
                value = "true";
            }
            if (value!=null && !"".equals(value)){
                s.append(" and "+entry.getKey()+" like ?");
                paras.add("%"+value+"%");
            }
        }
        String sql = s.toString();
        Integer count = template.queryForObject(sql, Integer.class,paras.toArray());
        return count;
    }


}
