package ro.utcn.sd.mid.assign1.virtualclassroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.AnswerRepository;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.QuestionRepository;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Answer> listAnswersForQuestion(Question question) {
        AnswerRepository ar = repositoryFactory.createAnswerRepository();
        List<Answer> answers = ar.findAll();
        List<Answer> result = new ArrayList<>();
        for (Answer a : answers) {
            if (a.getQuestionId().equals(question.getId())) {
                result.add(a);
            }
        }
        return result;
    }
}
