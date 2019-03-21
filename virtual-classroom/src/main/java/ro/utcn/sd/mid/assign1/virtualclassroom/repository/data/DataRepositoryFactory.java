package ro.utcn.sd.mid.assign1.virtualclassroom.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "sd_assign1.repository-type", havingValue = "DATA")
public class DataRepositoryFactory implements RepositoryFactory {
    private final DataAnswerRepository answerRepo;
    private final DataAnswerVoteRepository answerVoteRepo;
    private final DataQuestionRepository questionRepo;
    private final DataQuestionVoteRepository questionVoteRepo;
    private final DataSOUserRepository soUserRepo;
    private final DataTagRepository tagRepo;

    @Override
    public AnswerRepository createAnswerRepository() {
        return answerRepo;
    }

    @Override
    public AnswerVoteRepository createAnswerVoteRepository() {
        return answerVoteRepo;
    }

    @Override
    public QuestionRepository createQuestionRepository() {
        return questionRepo;
    }

    @Override
    public QuestionVoteRepository createQuestionVoteRepository() {
        return questionVoteRepo;
    }

    @Override
    public SOUserRepository createSOUserRepository() {
        return soUserRepo;
    }

    @Override
    public TagRepository createTagRepository() {
        return tagRepo;
    }
}
