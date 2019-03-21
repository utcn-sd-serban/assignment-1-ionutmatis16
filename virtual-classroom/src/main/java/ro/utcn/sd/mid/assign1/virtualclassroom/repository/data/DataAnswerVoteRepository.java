package ro.utcn.sd.mid.assign1.virtualclassroom.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.AnswerVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerVoteRepository;

public interface DataAnswerVoteRepository extends Repository<AnswerVote, Integer>,
        AnswerVoteRepository {
}
