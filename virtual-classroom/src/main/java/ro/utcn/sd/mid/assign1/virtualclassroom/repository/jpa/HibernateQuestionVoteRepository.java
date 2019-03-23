package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.QuestionVote;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionVoteRepository;

import javax.persistence.EntityManager;

public class HibernateQuestionVoteRepository extends HibernateAbstractRepository<QuestionVote>
implements QuestionVoteRepository {
    public HibernateQuestionVoteRepository(EntityManager entityManager) {
        super(entityManager, QuestionVote.class);
    }
}
