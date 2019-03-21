package ro.utcn.sd.mid.assign1.virtualclassroom.repository.memory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.TagRepository;

import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryTagRepository extends InMemoryAbstractRepository<Tag>
        implements TagRepository {
    private Map<Integer, Tag> data = super.getData();

    @Override
    public Optional<Tag> findByTagName(String tagName) {
        for (Tag t : data.values()) {
            if (t.getTagName().equals(tagName))
                return Optional.of(t);
        }
        return Optional.empty();
    }
}
