package ro.utcn.sd.mid.assign1.virtualclassroom;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionRepository;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.SOUserRepository;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.QuestionService;

@SpringBootApplication
@RequiredArgsConstructor
public class VirtualClassroomApplication {
    public static void main(String[] args) {
        SpringApplication.run(VirtualClassroomApplication.class, args);
    }
}
