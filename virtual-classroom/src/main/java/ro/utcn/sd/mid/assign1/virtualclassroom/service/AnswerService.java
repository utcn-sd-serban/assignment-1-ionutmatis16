package ro.utcn.sd.mid.assign1.virtualclassroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.exceptions.AnswerNotFoundException;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Answer> listAnswersForQuestion(Question question) {
        List<Answer> answers = repositoryFactory.createAnswerRepository().listAnswersForQuestion(question);
        for (Answer a : answers) {
            int score = (int) (repositoryFactory.createAnswerVoteRepository().upvoteNr(a) -
                    repositoryFactory.createAnswerVoteRepository().downvoteNr(a));
            a.setScore(score);
            repositoryFactory.createAnswerRepository().save(a);
        }
        answers.sort((o1, o2) -> -o1.getScore().compareTo(o2.getScore()));
        return answers;
    }

    @Transactional
    public Answer saveAnswer(Answer answer) {
        return repositoryFactory.createAnswerRepository().save(answer);
    }

    @Transactional
    public Answer findById(Integer answerId) {
        Optional<Answer> answer = repositoryFactory.createAnswerRepository().findById(answerId);
        if (answer.isPresent()) {
            int score = (int) (repositoryFactory.createAnswerVoteRepository().upvoteNr(answer.get()) -
                    repositoryFactory.createAnswerVoteRepository().downvoteNr(answer.get()));
            answer.get().setScore(score);
            return repositoryFactory.createAnswerRepository().save(answer.get());
        } else
            throw new AnswerNotFoundException();
    }

    @Transactional
    public void deleteAnswer(Answer answer) {
        Answer a = repositoryFactory.createAnswerRepository().findById(answer.getId()).get();
        repositoryFactory.createAnswerRepository().delete(a);
    }

}
