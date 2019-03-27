package ro.utcn.sd.mid.assign1.slackoverflow.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.Question;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.Tag;
import ro.utcn.sd.mid.assign1.slackoverflow.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryQuestionRepository extends InMemoryAbstractRepository<Question>
        implements QuestionRepository {
    @Override
    public List<Question> findByTitle(String title) {
        List<Question> questions = new ArrayList<>(super.getData().values());
        List<Question> result = new ArrayList<>();
        for(Question q : questions) {
            if (q.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(q);
            }
        }
        return result;
    }

    @Override
    public List<Tag> findTagsByQuestion(Question question) {
        return question.getTags();
    }
}
