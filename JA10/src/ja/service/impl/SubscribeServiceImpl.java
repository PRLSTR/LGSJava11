package ja.service.impl;
import java.util.List;
import ja.dao.DAOException;
import ja.dao.SubscribeDAO;
import ja.dao.impl.SubscribeDAOImpl;
import ja.domain.Subscribe;
import ja.service.SubscribeService;

public class SubscribeServiceImpl implements SubscribeService {
	private static SubscribeService subscribeService;
	private SubscribeDAO subscribeDAO;
	private SubscribeServiceImpl() {
		this.subscribeDAO = new SubscribeDAOImpl();
	}

	public static SubscribeService getSubscribeService() {
		if (subscribeService == null) {
			subscribeService = new SubscribeServiceImpl();
		}
		return subscribeService;
	}
	
	@Override
	public Subscribe insert(Subscribe t) throws DAOException {
		return subscribeDAO.insert(t);
	}

	@Override
	public List<Subscribe> readAll() throws DAOException {
		return subscribeDAO.readAll();
	}

	@Override
	public Subscribe readByID(int id) throws DAOException {
		return subscribeDAO.readByID(id);
	}

	@Override
	public boolean updateByID(Subscribe t) throws DAOException {
		return subscribeDAO.updateByID(t);
	}

	@Override
	public boolean delete(int id) throws DAOException {
		return subscribeDAO.delete(id);
	}
}