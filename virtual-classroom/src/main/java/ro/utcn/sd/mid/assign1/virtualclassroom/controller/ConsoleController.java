package ro.utcn.sd.mid.assign1.virtualclassroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Question;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.Tag;
import ro.utcn.sd.mid.assign1.virtualclassroom.exceptions.InvalidNameOrPasswordException;
import ro.utcn.sd.mid.assign1.virtualclassroom.exceptions.NameAlreadyExistsException;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.AnswerService;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.QuestionService;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.SOUserService;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.TagService;

import java.util.Optional;
import java.util.Scanner;


@Component
@RequiredArgsConstructor
@Order(0)
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final SOUserService sOUserService;
    private final TagService tagService;
    private SOUser loggedInUser;
    private boolean loggedIn = false;

    @Override
    @SuppressWarnings("Duplicates")
    public void run(String... args) {
        println("Welcome to the Stack Overflow like application.");

        while (!loggedIn) {
            println("Type \"login\" to enter the application or \"register\" to register as a new user.");
            String loginRegisterCommand = scanner.next().trim().toLowerCase();
            switch (loginRegisterCommand) {
                case "login":
                    try {
                        print("Username: ");
                        String username = scanner.next().trim();
                        print("Password: ");
                        String password = scanner.next().trim();
                        loggedInUser = sOUserService.loginSOUser(username, password);
                        loggedIn = true;    // exit while
                    } catch (InvalidNameOrPasswordException e) {
                        print("Invalid name or password!");
                    }
                    break;

                case "register":
                    try {
                        print("Username: ");
                        String username = scanner.next().trim();
                        print("Password: ");
                        String password = scanner.next().trim();
                        loggedInUser = sOUserService.registerSOUser(username, password);
                        loggedIn = true;
                    } catch (NameAlreadyExistsException e) {
                        print("Name already taken, please choose a different one!");
                    }
                    break;
                default:
                    print("Invalid command.");
            }
        }


        println("\nWelcome! Logged in as \"" + loggedInUser.getSOUsername() + "\".");
        println("Type \"help\" to see a list of available commands");
        loggedIn = false;
        while (!loggedIn) {
            print("\nCommand: ");
            String command = scanner.next().trim();
            switch (command) {
                case "help":
                    println("Available commands:");
                    println("\"list\" - shows all questions");
                    println("\"ask\" - ask a new question");
                    println("\"filter title\" - filter the questions by text title");
                    println("\"filter tag\" - filter the questions by tag");
                    println("\"exit\" - stop the application");
                    break;
                case "list":
                    println("\nThese are the questions:");
                    questionService.listAllQuestions().forEach(System.out::println);
                    break;
                case "ask":
                    handleAsk();
                    break;
                case "filter title":
                    print("Word to search for: ");
                    String word = scanner.next();
                    questionService.filterByTitle(word).forEach(System.out::println);
                    break;
                case "filter tag":
                    print("Tag to search for: ");
                    String tagName = scanner.next();
                    Optional<Tag> tag = tagService.findTagByTagName(tagName);
                    if (tag.isPresent()) {
                        questionService.filterByTag(tag.get()).forEach(System.out::println);
                    } else {
                        println("There are no tags with that name.");
                    }
                    break;
            }
        }
    }

    public void handleAsk() {
        boolean answered = false;
        print("Question title: ");
        String title = scanner.next();
        print("Question text: ");
        String text = scanner.next();
        Question questionToBeCreated = new Question(loggedInUser.getId(), title, text);
        questionService.askQuestion(questionToBeCreated);
        print("Available tags: ");
        tagService.listTags().forEach(tag -> print(tag.getTagName() + " "));
        //println("");
        while (!answered) {
            println("Do you wish to add any tags to your question? [yes/no]");
            String yesNo = scanner.next().trim().toLowerCase();
            if (yesNo.indexOf('y') >= 0) {
                println("Specify the tags by separating them with a space \" \". " +
                        "The tags that are not existent will be created and assigned " +
                        "to your question.");
                print("Tags to be added: ");
                String tagLine = scanner.next();
                String[] tags = tagLine.split(" ");
                for (String tagString : tags) {
                    //println("tag: " + tagString);
                    Optional<Tag> retrievedTag = tagService.findTagByTagName(tagString);
                    if (retrievedTag.isPresent()) {
                        questionService.addTagToQuestion(retrievedTag.get(), questionToBeCreated);
                    } else {
                        questionService.addTagToQuestion(tagService.
                                createTag(new Tag(tagString)), questionToBeCreated);
                    }
                }
                answered = true;
            } else {
                if (yesNo.indexOf('n') >= 0) {
                    questionService.askQuestion(questionToBeCreated);
                    answered = true;
                } else
                    println("Invalid command.");
            }
        }
    }

    private void println(String value) {
        System.out.println(value);
    }

    private void print(String value) {
        System.out.print(value);
    }
}
