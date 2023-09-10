package ja.service;
import ja.dao.DAOAbstractCRUD;
import ja.dao.DAOException;
import ja.domain.User;

public interface UserService extends DAOAbstractCRUD<User>{

	User readByEmail(String email) throws DAOException;
	boolean updateByEmail(User t) throws DAOException;
}