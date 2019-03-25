package ro.utcn.sd.mid.assign1.virtualclassroom.repository;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.AnswerVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends AbstractRepository<Question> {
    List<Question> findByTitle(String title);
    List<Tag> findTagsByQuestion(Question question);
}
