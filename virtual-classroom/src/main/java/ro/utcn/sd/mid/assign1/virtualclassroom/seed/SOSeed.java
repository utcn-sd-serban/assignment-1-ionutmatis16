package ro.utcn.sd.mid.assign1.virtualclassroom.seed;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(-10)
public class SOSeed implements CommandLineRunner {
    private final RepositoryFactory rf;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (rf.createSOUserRepository().findAll().isEmpty()) {
            rf.createSOUserRepository().save(new SOUser("user1", "p1"));
            rf.createSOUserRepository().save(new SOUser("user2", "p2"));
        }
        if(rf.createTagRepository().findAll().isEmpty()) {
            rf.createTagRepository().save(new Tag("tag1"));
            rf.createTagRepository().save(new Tag("tag2"));
        }
        if(rf.createQuestionRepository().findAll().isEmpty()) {
            Question q1 = new Question(1,"First Q", "Is this the first question?");
            Question q2 = new Question(2,"Second Q", "Is this the second question?");
            Optional<Tag> tag1 = rf.createTagRepository().findByTagName("tag1");
            Optional<Tag> tag2 = rf.createTagRepository().findByTagName("tag2");

            q1.addTag(tag1.get());
            q1.addTag(tag2.get());
            q2.addTag(tag2.get());

            rf.createQuestionRepository().save(q1);
            rf.createQuestionRepository().save(q2);
        }
    }
}
