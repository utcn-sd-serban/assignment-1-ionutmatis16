package ro.utcn.sd.mid.assign1.virtualclassroom.repository;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;

import java.util.List;

public interface AnswerRepository extends AbstractRepository<Answer> {
    List<Answer> listAnswersForQuestion(Question question);
}
