package ro.utcn.sd.mid.assign1.virtualclassroom.repository.jpa;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.TagRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class HibernateTagRepository extends HibernateAbstractRepository<Tag>
implements TagRepository {
    private final EntityManager entityManager;

    public HibernateTagRepository(EntityManager entityManager) {
        super(entityManager, Tag.class);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Tag> findByTagName(String tagName) {
        CriteriaQuery<Tag> cq = entityManager.getCriteriaBuilder().createQuery(Tag.class);
        Root<Tag> from = cq.from(Tag.class);

        cq.select(from);
        cq.where(entityManager.getCriteriaBuilder().equal(from.get("tagName"),tagName)); // <- this will add the restriction.

        return Optional.ofNullable(entityManager.createQuery(cq).getResultList().get(0));
    }

    @Override
    public List<Question> findQuestionsByTag(Tag tag) {
        return null;
    }

    @Override
    public void addTagToQuestion(Tag tag, Question q) {

    }
}
