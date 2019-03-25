package ro.utcn.sd.mid.assign1.virtualclassroom.repository.memory;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.*;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerVoteRepository;

import java.util.Optional;

public class InMemoryAnswerVoteRepository extends InMemoryAbstractRepository<AnswerVote>
        implements AnswerVoteRepository {
    @Override
    public Long upvoteNr(Answer answer) {
        long nr = 0;
        for(AnswerVote av : getData().values()) {
            if (av.getAnswerId().equals(answer.getId()) && av.getVoteType()) {
                nr++;
            }
        }
        return nr;
    }

    @Override
    public Long downvoteNr(Answer answer) {
        long nr = 0;
        for(AnswerVote av : getData().values()) {
            if (av.getAnswerId().equals(answer.getId()) && !av.getVoteType()) {
                nr++;
            }
        }
        return nr;
    }

    @Override
    public Optional<AnswerVote> findByAnswerSOUser(Integer answerId, Integer soUserId) {
        for(AnswerVote answerVote : getData().values()) {
            if (answerVote.getAnswerId().equals(answerId)
            && answerVote.getUserId().equals(soUserId))
                return Optional.of(answerVote);
        }
        return Optional.empty();
    }
}
