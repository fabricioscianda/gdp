package mseg.erp.spring.interceptors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mseg.erp.vomodel.VOCredential;
import mseg.erp.vomodel.VOResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;

public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger _logger = LoggerFactory.getLogger(RequestHandlerInterceptor.class);

	@Inject
	private Gson gson;

	public RequestHandlerInterceptor() {
		_logger.info("creating");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		response.setCharacterEncoding("UTF-8");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		_logger.info("REQUEST INTERCEPTOR URI: {}", request.getRequestURI());

		VOResponse responsevo = new VOResponse();

		if ((!request.getRequestURI().equals("/msegErp/rest/loginService/login"))
		 && (!request.getRequestURI().equals("/msegErp/rest/loginService/logout"))) {
			
			String clientToken = request.getHeader("authorization");
			if (clientToken != null) {
				VOCredential serverToken = (VOCredential) request.getSession().getAttribute("credentials");
				if (serverToken == null) {
					responsevo.setOk(false);
					responsevo.setErrorCode("2222");
					responsevo.setErrorMessage("Usuario no autenticado");
					response.getWriter().println(gson.toJson(responsevo));
					return false;
				}
			} else {
				responsevo.setOk(false);
				responsevo.setErrorCode("2222");
				responsevo.setErrorMessage("Usuario no autenticado");
				response.getWriter().println(gson.toJson(responsevo));
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}
}
