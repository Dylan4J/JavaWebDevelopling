package cn.cqu.studentsgradessystem.service.impl;

import cn.cqu.studentsgradessystem.dao.AdministratorDao;
import cn.cqu.studentsgradessystem.dao.impl.AdministratorDaoImpl;
import cn.cqu.studentsgradessystem.domain.Administator;
import cn.cqu.studentsgradessystem.service.AdministatorService;
import cn.cqu.studentsgradessystem.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdministratorServiceImpl implements AdministatorService {
    @Override
    public Administator login(Administator loginAdministator) {
        AdministratorDao administratorDao = new AdministratorDaoImpl();
        return administratorDao.login(loginAdministator);
    }
}
