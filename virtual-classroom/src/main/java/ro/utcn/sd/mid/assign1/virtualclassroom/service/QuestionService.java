package ro.utcn.sd.mid.assign1.virtualclassroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public Question askQuestion(Question question) {
        return repositoryFactory.createQuestionRepository().save(question);
    }

    @Transactional
    public List<Question> listAllQuestions() {
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        Collections.sort(questions);
        return questions;
    }

    /*@Transactional
    public List<Question> filterByTag(Tag tag) {
        List<Tag> tags = repositoryFactory.createTagRepository().findAll();
        List<Question> result = new ArrayList<>();
        for(Tag t : tags) {
            if (t.getTagName().equals(tag.getTagName())) {
                for(Integer id : tag.getTaggedQuestions()) {
                    result.add(repositoryFactory.createQuestionRepository().findById(id).get());
                }
            }
        }
        return result;
    }*/

    @Transactional
    public List<Question> filterByQuestionTextTitle(String text) {
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        List<Question> result = new ArrayList<>();
        for(Question q : questions) {
            if (q.getTitle().toLowerCase().contains(text.toLowerCase())) {
                result.add(q);
            }
        }
        return result;
    }


}
