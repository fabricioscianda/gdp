package gdp.spring.interceptors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;

import gdp.vomodel.VOCredential;
import gdp.vomodel.VOResponse;

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
		return super.preHandle(request, response, handler);
	}
}
