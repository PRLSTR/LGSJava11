package ja.filter;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ja.domain.AccessType;

public class FilterService {
	private static FilterService filterService;
	private FilterService() {
	}

	public static FilterService getFilterService() {
		if (filterService == null) {
			filterService = new FilterService();
		}
		return filterService;
	}

	public void doFilterValidation(ServletRequest request, ServletResponse response, FilterChain chain,
			List<AccessType> accessTypeList) throws IOException, ServletException {

		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			AccessType AccessType = AccessType.valueOf((String) session.getAttribute("AccessType"));

			if (AccessType != null && accessTypeList.contains(AccessType)) {
				chain.doFilter(request, response);
			} else {
				((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
}