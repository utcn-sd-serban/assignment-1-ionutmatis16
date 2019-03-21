package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.AnswerVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerVoteRepository;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc.mapper.AnswerVoteMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcAnswerVoteRepository implements AnswerVoteRepository {
    private final JdbcTemplate template;

    @Override
    public AnswerVote save(AnswerVote answerVote) {
        if (answerVote.getId() == null) {
            answerVote.setId(insert(answerVote));
        } else {
            update(answerVote);
        }
        return answerVote;
    }

    @Override
    public Optional<AnswerVote> findById(Integer id) {
        List<AnswerVote> answerVotes = template.query("SELECT * FROM answerVotes WHERE id = ?",
                new AnswerVoteMapper(), id);
        return answerVotes.isEmpty() ? Optional.empty() : Optional.of(answerVotes.get(0));
    }

    @Override
    public void delete(AnswerVote answerVote) {
        template.update("DELETE FROM answerVotes WHERE id = ?", answerVote.getId());
    }

    @Override
    public List<AnswerVote> findAll() {
        return template.query("SELECT * from answerVotes", new AnswerVoteMapper());
    }

    private Integer insert(AnswerVote answerVote) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("answerVotes");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("answerId", answerVote.getAnswerId());
        map.put("userId", answerVote.getUserId());
        map.put("voteType", answerVote.getVoteType());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(AnswerVote answerVote) {
        template.update("UPDATE answerVotes SET answerId = ?, userId = ?, voteType = ?",
                answerVote.getAnswerId(), answerVote.getUserId(), answerVote.getVoteType());
    }
}