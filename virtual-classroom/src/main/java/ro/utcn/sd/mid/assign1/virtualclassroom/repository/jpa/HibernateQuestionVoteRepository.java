package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.AnswerVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.QuestionVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionVoteRepository;

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
        /*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        Root<QuestionVote> from = query.from(QuestionVote.class);

        query.select(query.from(QuestionVote.class));
        query.where(builder.equal(from.get("questionId"),question.getId()));
        //query.where(builder.equal(from.get("voteType"),1));

        *//*System.out.println("UPVOTE");
        entityManager.createQuery(query).getResultList().forEach(
                q -> {
                    System.out.println(q.toString());
                    System.out.println(question.toString() + "\n");
                }
        );*//*
        List<QuestionVote> qvs = entityManager.createQuery(query).getResultList();
        Set<QuestionVote> result = new HashSet<>();

        for (QuestionVote qv : qvs) {
            if (qv.getVoteType() && (qv.getQuestionId().equals(question.getId())))
                result.add(qv);
        }

        return (long) result.size();*/
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        query.select(query.from(QuestionVote.class));

        List<QuestionVote> qvs = entityManager.createQuery(query).getResultList();
        Set<QuestionVote> result = new HashSet<>();

        for (QuestionVote qv : qvs) {
            if (qv.getVoteType() && (qv.getQuestionId().equals(question.getId())))
                result.add(qv);
        }

        return (long) result.size();
    }

    @Override @SuppressWarnings("Duplicates")
    public Long downvoteNr(Question question) {
        /*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        Root<QuestionVote> from = query.from(QuestionVote.class);

        query.select(query.from(QuestionVote.class));
        query.where(builder.equal(from.get("questionId"),question.getId()));
        query.where(builder.equal(from.get("voteType"),0));

        *//*System.out.println("UPVOTE");
        entityManager.createQuery(query).getResultList().forEach(
                q -> {
                    System.out.println(q.toString());
                    System.out.println(question.toString() + "\n");
                }
        );*//*

        List<QuestionVote> qvs = entityManager.createQuery(query).getResultList();
        Set<QuestionVote> result = new HashSet<>();

        for (QuestionVote qv : qvs) {
            if (qv.getVoteType() && (qv.getQuestionId().equals(question.getId())))
                result.add(qv);
        }

        return (long) result.size();*/

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionVote> query = builder.createQuery(QuestionVote.class);
        query.select(query.from(QuestionVote.class));

        List<QuestionVote> qvs = entityManager.createQuery(query).getResultList();
        Set<QuestionVote> result = new HashSet<>();

        for (QuestionVote qv : qvs) {
            if (!qv.getVoteType() && (qv.getQuestionId().equals(question.getId())))
                result.add(qv);
        }

        return (long) result.size();
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
