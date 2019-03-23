package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.AnswerVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerVoteRepository;

import javax.persistence.EntityManager;

public class HibernateAnswerVoteRepository extends HibernateAbstractRepository<AnswerVote>
        implements AnswerVoteRepository {
    public HibernateAnswerVoteRepository(EntityManager entityManager) {
        super(entityManager, AnswerVote.class);
    }
}
