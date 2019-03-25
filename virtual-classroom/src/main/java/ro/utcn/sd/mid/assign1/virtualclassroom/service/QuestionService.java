package ro.utcn.sd.mid.assign1.virtualclassroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.exceptions.QuestionNotFoundException;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;

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
    public Question findById(Integer id) {
        Optional<Question> OptQuestion = repositoryFactory.createQuestionRepository().findById(id);
        if (OptQuestion.isPresent()) {
            Question q = OptQuestion.get();
            q.setTags(repositoryFactory.createQuestionRepository().findTagsByQuestion(q));
            int score = (int) (repositoryFactory.createQuestionVoteRepository().upvoteNr(q) -
                    repositoryFactory.createQuestionVoteRepository().downvoteNr(q));
            q.setScore(score);
            return repositoryFactory.createQuestionRepository().save(q);
        }

        else
            throw new QuestionNotFoundException();
    }

    @Transactional
    public List<Question> listAllQuestions() {
        List<Question> questions = repositoryFactory.createQuestionRepository().findAll();
        Collections.sort(questions);
        for (Question q : questions) {
            q.setTags(repositoryFactory.createQuestionRepository().findTagsByQuestion(q));

            int score = (int) (repositoryFactory.createQuestionVoteRepository().upvoteNr(q) -
                    repositoryFactory.createQuestionVoteRepository().downvoteNr(q));
            q.setScore(score);
            //System.out.println(q.getTitle() + " score: " + score);

            repositoryFactory.createQuestionRepository().save(q);
        }
        return repositoryFactory.createQuestionRepository().findAll();
    }

    @Transactional
    public List<Question> filterByTag(Tag tag) {
        List<Question> questions = repositoryFactory.createTagRepository().findQuestionsByTag(tag);
        for (Question q : questions) {
            q.setTags(repositoryFactory.createQuestionRepository().findTagsByQuestion(q));
            repositoryFactory.createQuestionRepository().save(q);
        }
        return repositoryFactory.createTagRepository().findQuestionsByTag(tag);
    }

    @Transactional
    public List<Question> filterByTitle(String title) {
        List<Question> questions = repositoryFactory.createQuestionRepository().findByTitle(title);
        for (Question q : questions) {
            q.setTags(repositoryFactory.createQuestionRepository().findTagsByQuestion(q));
            repositoryFactory.createQuestionRepository().save(q);
        }
        return repositoryFactory.createQuestionRepository().findByTitle(title);
    }

    @Transactional
    public void addTagToQuestion(Tag tag, Question question) {
        repositoryFactory.createTagRepository().addTagToQuestion(tag, question);
    }


}
