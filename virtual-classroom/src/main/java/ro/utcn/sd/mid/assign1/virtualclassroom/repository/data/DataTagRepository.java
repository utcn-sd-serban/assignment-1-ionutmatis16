package ro.utcn.sd.mid.assign1.virtualclassroom.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.TagRepository;

public interface DataTagRepository extends Repository<Tag, Integer>,
        TagRepository {
}
