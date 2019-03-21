package ro.utcn.sd.mid.assign1.virtualclassroom.repository;

import org.springframework.stereotype.Component;

public interface RepositoryFactory {
    AnswerRepository createAnswerRepository();

    AnswerVoteRepository createAnswerVoteRepository();

    QuestionRepository createQuestionRepository();

    QuestionVoteRepository createQuestionVoteRepository();

    SOUserRepository createSOUserRepository();

    TagRepository createTagRepository();
}
