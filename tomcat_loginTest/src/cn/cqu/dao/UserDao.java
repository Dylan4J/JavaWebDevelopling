package cn.cqu.dao;

import cn.cqu.domain.User;
import cn.cqu.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDatasource());

    public User login(User loginUser){
        try {
            String sql = "select * from users where username =? and password =?";
            User user = jdbcTemplate.queryForObject(sql
                    , new BeanPropertyRowMapper<User>(User.class)
                    , loginUser.getUsername()
                    , loginUser.getPassword());
            return user;
        }catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
