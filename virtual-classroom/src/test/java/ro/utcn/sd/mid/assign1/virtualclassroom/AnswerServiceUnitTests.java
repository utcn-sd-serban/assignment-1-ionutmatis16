package ro.utcn.sd.mid.assign1.virtualclassroom;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Answer;
import ro.utcn.sd.mid.assign1.virtualclassroom.exceptions.AnswerNotFoundException;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.RepositoryFactory;
import ro.utcn.sd.mid.assign1.virtualclassroom.repository.memory.InMemoryRepositoryFactory;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.AnswerService;

public class AnswerServiceUnitTests {

    private static Answer a1 = new Answer(1, 1, "Ans1");
    private static Answer a2 = new Answer(2, 1, "Ans2");

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createAnswerRepository().save(a1);
        factory.createAnswerRepository().save(a2);
        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingAnswer() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        AnswerService answerService = new AnswerService(factory);

        // act - remove an answer with a well-known ID
        answerService.deleteAnswer(a1);

        // assert - expect that the answer was removed from the repository and the other answer is still there
        Assert.assertEquals(1, factory.createAnswerRepository().findAll().size());
        Assert.assertTrue(factory.createAnswerRepository().findById(2).isPresent());
    }

    @Test(expected = AnswerNotFoundException.class)
    public void testFindThrowsWithNotExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        AnswerService answerService = new AnswerService(factory);

        // act - search for an answer with a non-existent ID
        answerService.findById(999);

        // no assert, we expect an exception (see the @Test annotation)
    }
}
