package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerRepository;

import javax.persistence.EntityManager;

public class HibernateAnswerRepository extends HibernateAbstractRepository<Answer>
        implements AnswerRepository {
    public HibernateAnswerRepository(EntityManager entityManager) {
        super(entityManager, Answer.class);
    }
}
