package ja.ui;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import ja.dao.DAOException;
import ja.domain.Magazine;
import ja.domain.Subscribe;
import ja.dto.SubscribesDTO;
import ja.service.MagazineService;
import ja.service.SubscribeService;
import ja.service.impl.MagazineServiceImpl;
import ja.service.impl.SubscribeServiceImpl;

@WebServlet("/subscribes")
public class SubscribesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MagazineService magazineService = MagazineServiceImpl.getMagazineService();
	private SubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();
	private Logger log = Logger.getLogger(SubscribesServlet.class);

	public List<SubscribesDTO> map(List<Subscribe> subscribes, Map<Integer, Magazine> magazinesMap) {

		return subscribes.stream().map(value -> {
			SubscribesDTO subscribesDTO = new SubscribesDTO();
			subscribesDTO.id = value.getId();
			subscribesDTO.title = magazinesMap.get(value.getMagazineID()).getTitle();
			subscribesDTO.description = magazinesMap.get(value.getMagazineID()).getDescription();
			subscribesDTO.publishDate = magazinesMap.get(value.getMagazineID()).getPublishDate();
			subscribesDTO.subPrice = magazinesMap.get(value.getMagazineID()).getSubPrice();
			subscribesDTO.subStatus = value.getSubStatus();
			subscribesDTO.subDate = value.getSubDate();
			subscribesDTO.subPeriod = value.getSubPeriod();
			return subscribesDTO;
		}).collect(Collectors.toList());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Subscribe> subscribes = null;

		try {
			log.trace("Getting list of subscribes from database...");
			subscribes = subscribeService.readAll();
		} catch (DAOException e) {
			log.error("Getting list of subscribes failed", e);
		}

		Map<Integer, Magazine> magazinesMap = null;

		try {
			log.trace("Getting map of magazines from database...");
			magazinesMap = magazineService.readAllMap();
		} catch (DAOException e) {
			log.error("Getting map of magazines failed", e);
		}

		List<SubscribesDTO> subscribesDTO = map(subscribes, magazinesMap);

		if (subscribes == null || magazinesMap == null) {
			log.warn("There is no any magazine or subscribe in database registered");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("There is no any magazine or subscribe in database registered");
		} else {
			log.trace("Returning list of subscribes DTO...");
			String json = new Gson().toJson(subscribesDTO);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}
}