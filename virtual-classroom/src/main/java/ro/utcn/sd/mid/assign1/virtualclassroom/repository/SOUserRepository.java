package ro.utcn.sd.mid.assign1.virtualclassroom.repository;

import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;

import java.util.Optional;

public interface SOUserRepository extends AbstractRepository<SOUser> {
    Optional<SOUser> findBySOUsername(String name);
}
