package ro.utcn.sd.mid.assign1.virtualclassroom.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.SOUserRepository;

public interface DataSOUserRepository extends Repository<SOUser, Integer>,
        SOUserRepository {
}
