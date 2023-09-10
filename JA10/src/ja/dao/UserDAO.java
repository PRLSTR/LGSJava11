package ja.dao;
import ja.domain.User;

public interface UserDAO extends DAOAbstractCRUD<User>{

	User readByEmail(String email) throws DAOException;

	boolean updateByEmail(User t) throws DAOException;
}