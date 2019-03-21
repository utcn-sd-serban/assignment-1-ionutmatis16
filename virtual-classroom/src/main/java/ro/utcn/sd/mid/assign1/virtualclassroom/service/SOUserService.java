package ro.utcn.sd.mid.assign1.virtualclassroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.SOUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SOUserService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public boolean registerSOUser(String sOUsername, String sOPassword) {
        SOUserRepository sr = repositoryFactory.createSOUserRepository();
        Optional<SOUser> sOUser = sr.findBySOUsername(sOUsername);
        if (sOUser.isPresent()) {
            return false;
        } else {
            sr.save(new SOUser(sOUsername, sOPassword));
            return true;
        }
    }

    @Transactional
    public SOUser loginSOUser(String sOUsername, String sOPassword) {
        SOUserRepository sr = repositoryFactory.createSOUserRepository();
        Optional<SOUser> sOUser = sr.findBySOUsername(sOUsername);
        if (sOUser.isPresent() && sOUser.get().getSOPassword().equals(sOPassword))
            return sOUser.get();
        else
            throw new RuntimeException("Invalid user or password");
    }

    @Transactional
    public List<SOUser> listSOUsers() {
        return repositoryFactory.createSOUserRepository().findAll();
    }



}
