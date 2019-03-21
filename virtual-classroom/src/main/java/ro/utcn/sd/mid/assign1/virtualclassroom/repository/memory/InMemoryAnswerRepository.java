package ro.utcn.sd.mid.assign1.virtualclassroom.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerRepository;

@Component
public class InMemoryAnswerRepository extends InMemoryAbstractRepository<Answer>
        implements AnswerRepository {
}
