package ro.utcn.sd.mid.assign1.virtualclassroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TagService {
    private final RepositoryFactory rf;

    @Transactional
    public List<Tag> listTags() {
        return rf.createTagRepository().findAll();
    }

    @Transactional
    public Optional<Tag> findTagByTagName(String tagName) {
        return rf.createTagRepository().findByTagName(tagName);
    }

    @Transactional
    public Tag createTag(Tag tag) {
        return rf.createTagRepository().save(tag);
    }
}
