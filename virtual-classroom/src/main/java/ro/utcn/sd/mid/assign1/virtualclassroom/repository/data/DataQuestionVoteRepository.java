package ro.utcn.sd.mid.assign1.virtualclassroom.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.QuestionVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionVoteRepository;

import java.util.Optional;

public interface DataQuestionVoteRepository extends Repository<QuestionVote, Integer>,
        QuestionVoteRepository {
}
