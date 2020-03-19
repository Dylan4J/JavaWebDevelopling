package cn.cqu.studentsgradessystem.dao.impl;

import cn.cqu.studentsgradessystem.dao.AdministratorDao;
import cn.cqu.studentsgradessystem.domain.Administator;
import cn.cqu.studentsgradessystem.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdministratorDaoImpl implements AdministratorDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());
    public Administator login(Administator loginAdministator){
        String sql = "select * from administator where username = ? and password = ?";
        Administator admi = jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Administator.class),
                loginAdministator.getUserName(),loginAdministator.getPassword());
        return admi;
    }
}
