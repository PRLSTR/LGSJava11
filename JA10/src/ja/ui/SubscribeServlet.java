package js.ui;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import js.dao.DAOException;
import js.domain.Magazine;
import js.domain.Subscribe;
import js.service.SubscribeService;
import js.service.impl.SubscribeServiceImpl;

@WebServlet("/subscribe")
public class SubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();
	private Logger log = Logger.getLogger(SubscribeServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Getting fields values...");
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userID");
		String magazineID = request.getParameter("magazineID");
		String subPeriod = request.getParameter("subPeriod");

		Subscribe subscribe = new Subscribe(userId, Integer.parseInt(magazineID), false, LocalDate.now(), Integer.parseInt(subPeriod));

		try {
			log.trace("Saving subscribe in database...");
			subscribeService.insert(subscribe);
		} catch (DAOException e) {
			log.error("Creating subscribe failed", e);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		Subscribe subscribe = null;
		boolean subscribeUpdate = false;
		boolean subscribeStatus = false;

		try {
			log.trace("Getting subscribe by id from database...");
			subscribe = subscribeService.readByID(Integer.parseInt(id));
		} catch (NumberFormatException | DAOException e) {
			log.error("Getting subscribe by id failed", e);
		}

		if (subscribe == null) {
			log.warn("There is no subscribe with id=" + id + " in database");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("There is no subscribe with id=" + id + " in database");
		} else {
			try {
				log.info("Updating subscribe by id in database...");
				if (subscribe.getSubStatus()) {
					subStatus = false;
				} else {
					subStatus = true;
				}				
			
				subscribeUpdate = subscribeService.updateByID(new Subscribe(subscribe.getId(), subscribe.getUserID(),
					subscribe.getMagazineID(), subStatus, subscribe.getSubDate(), subscribe.getSubPeriod()));				
			} catch (DAOException e) {
				log.error("Updating subscribe by id failed", e);
			}

			if (subscribeUpdate) {
				String json = new Gson().toJson(subStatus);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		try {
			log.trace("Deleting subscribe item from database...");
			subscribeService.delete(Integer.parseInt(id));
		} catch (NumberFormatException | DAOException e) {
			log.error("Deleting subscribe failed", e);
		}
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}
}