package ja;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ja.dao.DAOException;
import ja.domain.Magazine;
import ja.domain.Subscribe;
import ja.domain.User;
import ja.service.MagazineService;
import ja.service.SubscribeService;
import ja.service.UserService;
import ja.service.impl.MagazineServiceImpl;
import ja.service.impl.SubscribeServiceImpl;
import ja.service.impl.UserServiceImpl;

public class Main {

	public static void main(String[] args) throws DAOException {
		Logger log = Logger.getLogger(Main.class);
		PropertyConfigurator.configure("src/resources/log4j.properties");
		log.trace("Starting application...");
	
		List<User> userList = new ArrayList<>();
		userList.add(new User("Samus", "Aran", "Samus_prime_hunter@gmail.com", "AM2RMPR", "USER"));
		userList.add(new User("Isa", "Itou", "itisolde560@gmail.com", "ITISLD-V-560524", "USER"));

		UserService userService = UserServiceImpl.getUserService();
		userList.forEach(user -> {
			try {
				System.out.println(userService.insert(user));
			} catch (DAOException e) {
				log.error("Error occured!", e);
				e.printStackTrace();
			}
		});
		
		System.out.println(userService.readByID(2));
		System.out.println(userService.readByEmail("Samus_prime_hunter@gmail.com"));
		userService.updateByID(new User(1, "Adam", "Malko", "Samus_prime_hunter@gmail.com", "AM2RMPR", "АDMIN"));
		userService.updateByEmail(new User("Isolde", "White", "itisolde560@gmail.com", "ITISLD-V-560524", "USER"));
		userService.delete(1);
		userService.readAll().forEach(System.out::println);

		MagazineService magazineService = MagazineServiceImpl.getMagazineService();
		System.out.println(
				magazineService.insert(new Magazine("name", "description",
						LocalDate.parse("2042-05-03"), 6005)));
		magazineService.readAll().forEach(System.out::println);

		SubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();
		System.out.println(subscribeService.insert(new Subscribe(2, 1, true, LocalDate.parse("2042-05-03"), 12)));
		subscribeService.readAll().forEach(System.out::println);
	}
}