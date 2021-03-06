package ro.utcn.sd.mid.assign1.slackoverflow.repository;

import ro.utcn.sd.mid.assign1.slackoverflow.entity.Question;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.Tag;

import java.util.List;

public interface QuestionRepository extends AbstractRepository<Question> {
    List<Question> findByTitle(String title);
    List<Tag> findTagsByQuestion(Question question);
}
