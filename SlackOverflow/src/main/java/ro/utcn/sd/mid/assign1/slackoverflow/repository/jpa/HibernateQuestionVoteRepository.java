package ro.utcn.sd.mid.assign1.slackoverflow.repository.jpa;

import ro.utcn.sd.mid.assign1.slackoverflow.entity.Question;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.QuestionVote;
import ro.utcn.sd.mid.assign1.slackoverflow.repository.QuestionVoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class HibernateQuestionVoteRepository extends HibernateAbstractRepository<QuestionVote>
implements QuestionVoteRepository {
    private final EntityManager entityManager;

    public HibernateQuestionVoteRepository(EntityManager entityManager) {
        super(entityManager, QuestionVote.class);
        this.entityManager = entityManager;
    }


    @Override @SuppressWarnings("Duplicates")
    public Long upvoteNr(Question question) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        Root<QuestionVote> from = query.from(QuestionVote.class);

        query.select(query.from(QuestionVote.class)).
                where(builder.equal(from.get("questionId"),question.getId())).
                where(builder.equal(from.get("voteType"),true));

        /*System.out.println("\n\nUPVOTE");
        entityManager.createQuery(query).getResultList().forEach(
                q -> {
                    System.out.println("Question din foreach " + q.toString());
                    System.out.println("Question din arg " + question.toString() + "\n");
                }
        );*/


        return (long) entityManager.createQuery(query).getResultList().size();
    }

    @Override @SuppressWarnings("Duplicates")
    public Long downvoteNr(Question question) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        Root<QuestionVote> from = query.from(QuestionVote.class);

        query.select(query.from(QuestionVote.class));
        query.where(builder.equal(from.get("questionId"),question.getId()));
        query.where(builder.equal(from.get("voteType"),0));

        /*System.out.println("\n\nDOWNVOTE");
        entityManager.createQuery(query).getResultList().forEach(
                q -> {
                    System.out.println("Question din foreach " + q.toString());
                    System.out.println("Question din arg " + question.toString() + "\n");
                }
        );*/
        return (long) entityManager.createQuery(query).getResultList().size();

    }

    @Override
    public Optional<QuestionVote> findByQuestionSOUser(Integer questionId, Integer soUserId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        Root<QuestionVote> from = query.from(QuestionVote.class);

        query.select(query.from(QuestionVote.class));
        query.where(builder.equal(from.get("questionId"), questionId));
        query.where(builder.equal(from.get("userId"), soUserId));

        List<QuestionVote> qvs = entityManager.createQuery(query).getResultList();



        return qvs.isEmpty() ? Optional.empty() : Optional.of(qvs.get(0));
    }
}
