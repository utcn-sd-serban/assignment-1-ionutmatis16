package ro.utcn.sd.mid.assign1.virtualclassroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.AnswerVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.QuestionVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.exceptions.AlreadyVotedException;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VoteService {
    private final RepositoryFactory rf;

    @Transactional
    public void voteQuestion(Integer questionId, Integer sOUserId, boolean value) {
        Optional<QuestionVote> qv = rf.createQuestionVoteRepository().
                findByQuestionSOUser(questionId, sOUserId);
        if (qv.isPresent()) {
            if (qv.get().getVoteType() == value) {
                throw new AlreadyVotedException();
            } else {
                qv.get().setVoteType(value);
                rf.createQuestionVoteRepository().save(qv.get());
            }
        } else {
            rf.createQuestionVoteRepository().save(new QuestionVote(questionId, sOUserId, value));
        }
    }

    @Transactional
    public void voteAnswer(Integer answerId, Integer sOUserId, boolean value) {
        Optional<AnswerVote> av = rf.createAnswerVoteRepository().
                findByAnswerSOUser(answerId, sOUserId);
        if (av.isPresent()) {
            if (av.get().getVoteType() == value) {
                throw new AlreadyVotedException();
            } else {
                av.get().setVoteType(value);
                rf.createAnswerVoteRepository().save(av.get());
            }
        } else {
            rf.createAnswerVoteRepository().save(new AnswerVote(answerId, sOUserId, value));
        }
    }
}
