package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class HibernateAnswerRepository extends HibernateAbstractRepository<Answer>
        implements AnswerRepository {
    private final EntityManager entityManager;

    public HibernateAnswerRepository(EntityManager entityManager) {
        super(entityManager, Answer.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<Answer> listAnswersForQuestion(Question question) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Answer> query = builder.createQuery(Answer.class);

        Root<Answer> from = query.from(Answer.class);

        query.select(from);
        query.where(builder.equal(from.get("questionId"),question.getId())); // <- this will add the restriction.

        return entityManager.createQuery(query).getResultList();
    }
}
