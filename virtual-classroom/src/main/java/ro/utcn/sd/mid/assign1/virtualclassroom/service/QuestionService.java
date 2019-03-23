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
        for (Question q : questions) {
            q.setTags(repositoryFactory.createQuestionRepository().findTagsByQuestion(q));
        }
        Collections.sort(questions);
        return questions;
    }

    @Transactional
    public List<Question> filterByTag(Tag tag) {
        List<Question> questions = repositoryFactory.createTagRepository().findQuestionsByTag(tag);
        for (Question q : questions) {
            q.setTags(repositoryFactory.createQuestionRepository().findTagsByQuestion(q));
        }
        return questions;
    }

    @Transactional
    public List<Question> filterByTitle(String title) {
        List<Question> questions = repositoryFactory.createQuestionRepository().findByTitle(title);
        for (Question q : questions) {
            q.setTags(repositoryFactory.createQuestionRepository().findTagsByQuestion(q));
        }
        return questions;
    }

    @Transactional
    public void addTagToQuestion(Tag tag, Question question) {
        repositoryFactory.createTagRepository().addTagToQuestion(tag, question);
    }


}
