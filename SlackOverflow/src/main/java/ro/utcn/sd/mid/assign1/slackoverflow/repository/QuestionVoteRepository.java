package ro.utcn.sd.mid.assign1.slackoverflow.repository;

import ro.utcn.sd.mid.assign1.slackoverflow.entity.*;

import java.util.Optional;

public interface QuestionVoteRepository extends AbstractRepository<QuestionVote> {
    Long upvoteNr(Question question);
    Long downvoteNr(Question question);
    Optional<QuestionVote> findByQuestionSOUser(Integer questionId, Integer soUserId);
}
