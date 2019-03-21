package ro.utcn.sd.mid.assign1.virtualclassroom.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionRepository;

public interface DataQuestionRepository extends Repository<Question, Integer>,
        QuestionRepository {
}
