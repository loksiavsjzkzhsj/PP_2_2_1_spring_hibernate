package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      CarService carService = context.getBean(CarService.class);
      Car car1 = new Car("Mercedes-Benz", 600);
      Car car2 = new Car("BMW", 700);
      Car car3 = new Car("Audi", 400);
      Car car4 = new Car("Porsche", 911);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      car1.setOwnerCar(user1);
      car2.setOwnerCar(user2);
      car3.setOwnerCar(user3);
      car4.setOwnerCar(user4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println("работа метода getUserByCar - " + carService.getUserByCar(car2));


      List<Car> cars = carService.listCars();
      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("Car model = "+car.getModel());
         System.out.println("Car series = "+car.getSeries());
         System.out.println("Car owner = " + car.getOwnerCar());
         System.out.println();
      }
      System.out.println("работа метода getUserByCar - " + carService.getUserByCar(car2));

      context.close();
   }
}
