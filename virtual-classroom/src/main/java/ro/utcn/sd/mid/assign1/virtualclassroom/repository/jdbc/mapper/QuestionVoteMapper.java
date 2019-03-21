package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.QuestionVote;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionVoteMapper implements RowMapper<QuestionVote> {
    @Override
    public QuestionVote mapRow(ResultSet rs, int rowNr) throws SQLException {
        return new QuestionVote(rs.getInt("id"),
                rs.getInt("questionId"),
                rs.getInt("userId"),
                rs.getBoolean("voteType"));
    }
}
