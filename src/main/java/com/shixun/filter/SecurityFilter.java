package com.shixun.filter;

import java.io.IOException;


/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter("/*")
public class SecurityFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SecurityFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession();
		StringBuffer requestURL = httpRequest.getRequestURL();
		// 获取session
		Object sessionId = session.getAttribute("accountIndex");
		// 放行登录，以及登录资源
		if (requestURL.indexOf("login.jsp") > 0 || requestURL.indexOf("dologin") > 0 || requestURL.indexOf("images") > 0
				|| requestURL.indexOf("css") > 0) {
			chain.doFilter(request, response);
			return;
		}
		// 拦截没有登录的
		if (sessionId == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
