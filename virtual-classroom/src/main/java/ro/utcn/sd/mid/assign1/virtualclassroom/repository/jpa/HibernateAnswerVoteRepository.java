package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.AnswerVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerVoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


public class HibernateAnswerVoteRepository extends HibernateAbstractRepository<AnswerVote>
        implements AnswerVoteRepository {
    private final EntityManager entityManager;

    public HibernateAnswerVoteRepository(EntityManager entityManager) {
        super(entityManager, AnswerVote.class);
        this.entityManager = entityManager;
    }

    // DOES NOT WORK, IT ADDS THE VOTE TO ALL ANSWERS
    // SEE HibernateQuestionVoteRepository for another (failed) implementation
    @Override @SuppressWarnings("Duplicates")
    public Long upvoteNr(Answer answer) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(AnswerVote.class)));
        Root<AnswerVote> from = query.from(AnswerVote.class);

        query.where(builder.equal(from.get("answerId"),answer.getId()));
        query.where(builder.equal(from.get("voteType"),true));

        return entityManager.createQuery(query).getResultList().get(0);
    }

    // DOES NOT WORK, IT ADDS THE VOTE TO ALL ANSWERS
    // SEE HibernateQuestionVoteRepository for another (failed) implementation
    @Override @SuppressWarnings("Duplicates")
    public Long downvoteNr(Answer answer) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(AnswerVote.class)));
        Root<AnswerVote> from = query.from(AnswerVote.class);

        query.where(builder.equal(from.get("answerId"),answer.getId()));
        query.where(builder.equal(from.get("voteType"),false));

        return entityManager.createQuery(query).getResultList().get(0);
    }

    @Override
    public Optional<AnswerVote> findByAnswerSOUser(Integer answerId, Integer soUserId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AnswerVote> query = builder.createQuery(AnswerVote.class);
        Root<AnswerVote> from = query.from(AnswerVote.class);

        query.select(query.from(AnswerVote.class));
        query.where(builder.equal(from.get("answerId"), answerId));
        query.where(builder.equal(from.get("userId"), soUserId));

        List<AnswerVote> av = entityManager.createQuery(query).getResultList();

        return av.isEmpty() ? Optional.empty() : Optional.of(av.get(0));
    }
}
