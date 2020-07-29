package web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import web.config.PersistenceJPAConfig;
import web.model.User;
import web.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.addUser(new User("User1", "secondName1", 15));
        userService.addUser(new User("User2", "secondName2", 20));
        userService.addUser(new User("User3", "secondName3", 25));


        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getSecondName());
            System.out.println("Age = "+user.getAge());
        }

        context.close();
    }
}
