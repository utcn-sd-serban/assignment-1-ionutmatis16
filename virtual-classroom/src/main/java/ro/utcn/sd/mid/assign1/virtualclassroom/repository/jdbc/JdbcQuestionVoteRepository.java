package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.QuestionVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionVoteRepository;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc.mapper.QuestionVoteMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcQuestionVoteRepository implements QuestionVoteRepository {
    private final JdbcTemplate template;

    @Override
    public QuestionVote save(QuestionVote questionVote) {
        if (questionVote.getId() == null) {
            questionVote.setId(insert(questionVote));
        } else {
            update(questionVote);
        }
        return questionVote;
    }

    @Override
    public Optional<QuestionVote> findById(Integer id) {
        List<QuestionVote> questionVotes = template.query("SELECT * FROM questionVotes WHERE id = ?",
                new QuestionVoteMapper(), id);
        return questionVotes.isEmpty() ? Optional.empty() : Optional.of(questionVotes.get(0));
    }

    @Override
    public void delete(QuestionVote questionVote) {
        template.update("DELETE FROM questionVotes WEHRE id = ?", questionVote.getId());
    }

    @Override
    public List<QuestionVote> findAll() {
        return template.query("SELECT * FROM questionVotes", new QuestionVoteMapper());
    }

    @SuppressWarnings("Duplicates")
    private int insert(QuestionVote questionVote) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("questionVotes");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("questionId", questionVote.getQuestionId());
        map.put("userId", questionVote.getUserId());
        map.put("voteType", questionVote.getVoteType());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(QuestionVote questionVote) {
        template.update("UPDATE questionVotes SET questionId = ?, userId = ?, voteType = ? " +
                        "WHERE id = ?",
                questionVote.getQuestionId(), questionVote.getUserId(), questionVote.getVoteType(),
                questionVote.getId());
    }
}