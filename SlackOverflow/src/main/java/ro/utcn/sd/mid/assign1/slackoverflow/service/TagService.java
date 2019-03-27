package ro.utcn.sd.mid.assign1.slackoverflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.Tag;
import ro.utcn.sd.mid.assign1.slackoverflow.exceptions.TagNotFoundException;
import ro.utcn.sd.mid.assign1.slackoverflow.repository.RepositoryFactory;

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
    public Tag findTagByTagName(String tagName) {
        Optional<Tag> tag = rf.createTagRepository().findByTagName(tagName);
        if(tag.isPresent())
            return tag.get();
        else
            throw new TagNotFoundException();
    }

    @Transactional
    public Tag createTag(Tag tag) {
        return rf.createTagRepository().save(tag);
    }
}
