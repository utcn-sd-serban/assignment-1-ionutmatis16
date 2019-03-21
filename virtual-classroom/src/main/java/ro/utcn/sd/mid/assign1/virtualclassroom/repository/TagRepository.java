package ro.utcn.sd.mid.assign1.virtualclassroom.repository;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;

import java.util.Optional;

public interface TagRepository extends AbstractRepository<Tag>{
    Optional<Tag> findByTagName(String tagName);
}
