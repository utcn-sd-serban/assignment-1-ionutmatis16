package ro.utcn.sd.mid.assign1.virtualclassroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.mid.assign1.virtualclassroom.entity.SOUser;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.AnswerService;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.QuestionService;
import ro.utcn.sd.mid.assign1.virtualclassroom.service.SOUserService;

import java.util.Scanner;


@Component
@RequiredArgsConstructor
@Order(0)
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final SOUserService sOUserService;
    private SOUser loggedInUser;
    private boolean loggedIn = false;

    @Override
    public void run(String... args) {
        print("Welcome to the Stack Overflow like application.");

        while (!loggedIn) {
            print("Type \"login\" to enter the application or \"register\" to register as a new user.");
            String loginRegisterCommand = scanner.next().trim().toLowerCase();
            switch (loginRegisterCommand) {
                case "login":
                    try {
                        System.out.print("Username: ");
                        String username = scanner.next().trim();
                        System.out.print("Password: ");
                        String password = scanner.next().trim();
                        loggedInUser = sOUserService.loginSOUser(username, password);
                        loggedIn = true;    // exit while
                    } catch (RuntimeException e) {
                        print(e.getMessage());
                    }
                    break;

                case "register":
                    System.out.print("Username: ");
                    String username = scanner.next().trim();
                    System.out.print("Password: ");
                    String password = scanner.next().trim();
                    if (sOUserService.registerSOUser(username, password)) { // registered successfully
                        loggedInUser = sOUserService.loginSOUser(username,password);
                        loggedIn = true;    // exit while
                    } else { // The name of the user already exists in the DB
                        print("Name already taken, please choose a different one!");
                    }
                    break;

                default:
                    print("Invalid command.");
                    //print("Type \"login\" to enter the application or \"register\" to register as a new user.");
            }
        }

        //sOUserService.listSOUsers().forEach(u -> System.out.println(u));

        print("\nWelcome! Logged in as \"" + loggedInUser.getSOUsername() + "\".");
        print("Type \"help\" to see a list of available commands");
        loggedIn = false;
        while(!loggedIn) {
            System.out.print("\nCommand: ");
            String command = scanner.next().trim();
            switch (command) {
                case "help":
                    print("Available commands:");
                    print("\"list\" - shows all questions");
                    print("\"ask\" - ask a new question");
                    print("\"filter\" - filter the questions");
                    print("\"exit\" - stop the application");
                    break;
                case "list":
                    print("\nThese are the questions:");
                    questionService.listAllQuestions().forEach(System.out::println);
                    break;
                case "ask":
                    //handleAsk();
                    break;
            }
        }
    }

    /*private boolean handleCommand(String command) {
        switch (command) {
            case "list":
                handleList();
                return false;
            case "add":
                handleAdd();
                return false;
            case "update":
                handleUpdateEmail();
                return false;
            case "remove":
                handleRemove();
                return false;
            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }*/

    private void print(String value) {
        System.out.println(value);
    }
}
