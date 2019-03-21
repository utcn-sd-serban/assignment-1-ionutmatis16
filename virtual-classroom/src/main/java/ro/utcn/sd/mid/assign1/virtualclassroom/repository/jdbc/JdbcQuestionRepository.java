package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionRepository;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.jdbc.mapper.QuestionMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {
    private final JdbcTemplate template;

    @Override
    public Question save(Question question) {
        if (question.getId() == null) {
            question.setId(insert(question));
        } else {
            update(question);
        }
        return question;
    }

    @Override
    public Optional<Question> findById(Integer id) {
        List<Question> questions = template.query("SELECT * FROM questions WHERE id = ?",
                new QuestionMapper(), id);
        return questions.isEmpty() ? Optional.empty() : Optional.of(questions.get(0));
    }

    @Override
    public void delete(Question question) {
        template.update("DELETE FROM questions WEHRE id = ?", question.getId());
    }

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM questions", new QuestionMapper());
    }

    @SuppressWarnings("Duplicates")
    private int insert(Question question) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("questions");
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("userId", question.getUserId());
        map.put("title", question.getTitle());
        map.put("text", question.getText());
        map.put("creationDate", question.getCreationDate());
        map.put("score", question.getScore());
        return insert.executeAndReturnKey(map).intValue();
    }

    private void update(Question question) {
        template.update("UPDATE questions SET userId = ?, title = ?, text = ?, " +
                        "creationDate = ?, score = ? WHERE id = ?",
                question.getUserId(), question.getTitle(), question.getText(),
                question.getCreationDate(), question.getScore(), question.getId());
    }
}
