package ro.utcn.sd.mid.assign1.slackoverflow.repository.memory;

import ro.utcn.sd.mid.assign1.slackoverflow.entity.Question;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.QuestionVote;
import ro.utcn.sd.mid.assign1.slackoverflow.repository.QuestionVoteRepository;

import java.util.Optional;

public class InMemoryQuestionVoteRepository extends InMemoryAbstractRepository<QuestionVote>
        implements QuestionVoteRepository {
    @Override
    public Long upvoteNr(Question question) {
        long nr = 0;
        for(QuestionVote qv : getData().values()) {
            if (qv.getQuestionId().equals(question.getId()) && qv.getVoteType()) {
                nr++;
            }
        }
        return nr;
    }

    @Override
    public Long downvoteNr(Question question) {
        long nr = 0;
        for(QuestionVote qv : getData().values()) {
            if (qv.getQuestionId().equals(question.getId()) && !qv.getVoteType()) {
                nr++;
            }
        }
        return nr;
    }

    @Override
    public Optional<QuestionVote> findByQuestionSOUser(Integer questionId, Integer soUserId) {
        for(QuestionVote questionVote : getData().values()) {
            if (questionVote.getQuestionId().equals(questionId)
                    && questionVote.getUserId().equals(soUserId))
                return Optional.of(questionVote);
        }
        return Optional.empty();
    }
}
