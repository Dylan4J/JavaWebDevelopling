package cn.cqu.dao;

import cn.cqu.domain.User;
import cn.cqu.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class userDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDatasource());

    public User login(User loginUser){

        return null;
    }
}
