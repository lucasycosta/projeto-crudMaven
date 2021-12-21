package br.com.lucas.projeto.teste.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {
	
	private static final String ORIGIN = "Origin";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		log.debug("======= CORSFilter =======");
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		if (request.getHeader(ORIGIN) != null) {
			log.debug("======= PRIMEIRO IF =======");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Origin", request.getHeader(ORIGIN));
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Ajax-Call, XMLHttpRequest");

			log.debug("* ORIGIN = ", request.getHeader(ORIGIN));
			log.debug("* AUTH = ", request.getHeader("Authorization"));
		}

		if (request.getMethod().equals("OPTIONS")) {
			log.debug("======= SEGUNDO IF =======");
			response.getWriter().print("OK");
			response.getWriter().flush();
			return;
		}

		chain.doFilter(req, res);
	}

}
