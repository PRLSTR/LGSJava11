package ja.service;
import java.util.Map;
import ja.dao.DAOAbstractCRUD;
import ja.dao.DAOException;
import ja.domain.Magazine;

public interface MagazineService extends DAOAbstractCRUD<Magazine> {
	public Map<Integer, Magazine> readAllMap() throws DAOException;
}