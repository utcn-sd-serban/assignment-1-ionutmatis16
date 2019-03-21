package ro.utcn.sd.mid.assign1.virtualclassroom.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerRepository;

public interface DataAnswerRepository extends Repository<Answer, Integer>,
        AnswerRepository {
}
