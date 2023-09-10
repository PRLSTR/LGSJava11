package ja.filter;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import ja.domain.AccessType;

@WebFilter("/createMagazine.jsp")
public class CreateMagazineFilter implements Filter {
	private FilterService filterService = FilterService.getFilterService();
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		filterService.doFilterValidation(request, response, chain, Arrays.asList(AccessType.ADMIN));
	}
}