package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SOUserMapper implements RowMapper<SOUser> {
    @Override
    public SOUser mapRow(ResultSet rs, int i) throws SQLException {
        return new SOUser(rs.getInt("id"),
                rs.getString("sousername"),
                rs.getString("sopassword"));
    }
}
