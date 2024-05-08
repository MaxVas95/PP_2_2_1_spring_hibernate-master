package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User first = new User("Maxim", "Vasekha", "max@mail.ru");
        User second = new User("Ivan", "Ivanov", "ivan@mail.ru");
        User third = new User("Victor", "Petrov", "petr@gmail.com");
        User fourth = new User("Olga", "Morozova", "olmoroz@gmail.com");

        Car volvo = new Car("Audi", 6);
        Car bmw = new Car("BMW", 330);
        Car suzuki = new Car("Mercedes", 63);
        Car lada = new Car("Mazda", 8);

        userService.add(first.setCar(volvo).setUser(first));
        userService.add(second.setCar(bmw).setUser(second));
        userService.add(third.setCar(suzuki).setUser(third));
        userService.add(fourth.setCar(lada).setUser(fourth));


        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }

        System.out.println(userService.getUserByCar("Audi", 6));

        context.close();
    }
}
