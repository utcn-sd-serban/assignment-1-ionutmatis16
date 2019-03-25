package ro.utcn.sd.mid.assign1.virtualclassroom.repository;

public interface RepositoryFactory {
    AnswerRepository createAnswerRepository();

    AnswerVoteRepository createAnswerVoteRepository();

    QuestionRepository createQuestionRepository();

    QuestionVoteRepository createQuestionVoteRepository();

    SOUserRepository createSOUserRepository();

    TagRepository createTagRepository();
}
