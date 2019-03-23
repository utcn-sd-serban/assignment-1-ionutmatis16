package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateQuestionRepository extends HibernateAbstractRepository<Question>
        implements QuestionRepository {
    private final EntityManager entityManager;

    public HibernateQuestionRepository(EntityManager entityManager) {
        super(entityManager, Question.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<Question> findByTitle(String title) {
        //We want question entities
        CriteriaQuery<Question> cq = entityManager.getCriteriaBuilder().createQuery(Question.class);
        Root<Question> from = cq.from(Question.class);

        cq.select(from);
        cq.where(entityManager.getCriteriaBuilder().equal(from.get("title"),title)); // <- this will add the restriction.


        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Tag> findTagsByQuestion(Question question) {
        return null;
    }
}
