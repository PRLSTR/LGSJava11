package ja.ui;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ja.dao.DAOException;
import ja.domain.Magazine;
import ja.service.MagazineService;
import ja.service.impl.MagazineServiceImpl;

@WebServlet("/magazine")
public class MagazineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MagazineService magazineService = MagazineServiceImpl.getMagazineService();
	private Logger log = Logger.getLogger(MagazineServlet.class);
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Getting fields values from Magazine creation Form...");
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String publishDate = request.getParameter("publishDate");
		String subPrice = request.getParameter("subPrice");
		
		try {
			log.trace("Saving magazine in database...");
			magazineService.insert(new Magazine(title, description, LocalDate.parse(publishDate), Integer.parseInt(subPrice)));
		} catch (DAOException e) {
			log.error("Creating magazine failed", e);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Magazine \"" + title + "\" has been added to database");		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Getting id value from Magazine Card...");
		String magazineID = request.getParameter("id");
		Magazine magazine = null;
		
		try {
			log.trace("Getting magazine by id from database...");
			magazine = magazineService.readByID(Integer.parseInt(magazineID));
		} catch (NumberFormatException | DAOException e) {
			log.error("Getting magazine by id failed", e);		}
		
		if (magazine == null) {
			log.warn("There is no magazine with id=" + magazineID + " in database");
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("There is no magazine with id=" + magazineID + " in database");
		} else {
			log.trace("Redirecting to Magazine's card page...");
			request.setAttribute("magazine", magazine);
			request.getRequestDispatcher("magazineCard.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}