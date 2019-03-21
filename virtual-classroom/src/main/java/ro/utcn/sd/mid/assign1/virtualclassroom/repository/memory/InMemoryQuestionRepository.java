package ro.utcn.sd.mid.assign1.virtualclassroom.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionRepository;

@Component
public class InMemoryQuestionRepository extends InMemoryAbstractRepository<Question>
        implements QuestionRepository {
}
