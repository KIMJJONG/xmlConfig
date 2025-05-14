package sample.interceptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import sample.model.ReqInfo;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);
	
	private static final Pattern ESCAPE_PATTERN = Pattern.compile("(\\.|\\\\|\\[|\\]|\\^|\\$|\\+|\\{|\\}|\\(|\\)|\\|)");
	
	private static final Pattern ASTERISK_PATTERN = Pattern.compile("\\*");
	
	private static final Pattern QUESTION_PATTERN = Pattern.compile("\\?");
	
	private static final Pattern MULTIPLE_PATTERN = Pattern.compile("\\;");
	
	@Resource(name = "reqInfo")
	private ReqInfo reqInfo;
	
	private Pattern[] skipUrlPatterns;

	public SessionInterceptor() {
		LOGGER.info("SessionInterceptor 생성자");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!checkSkip(request)) {
			response.sendRedirect(request.getContextPath() + "/goError.do");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		long startTime = reqInfo.getStartTime();
		long endTime = System.currentTimeMillis();
		
		StringBuilder strBuf = new StringBuilder();
		strBuf.append(reqInfo.getUri());
		strBuf.append(", Elapsed(").append(endTime - startTime).append("ms)");
		LOGGER.info(strBuf.toString());
	}
	
	private boolean checkSkip(HttpServletRequest request) {
		if ((this.skipUrlPatterns != null) && (matches(this.skipUrlPatterns, request.getServletPath()))) {
			LOGGER.trace("{} session check skip", request.getServletPath());
			return true;
		}
		
		LOGGER.trace("{} session check", request.getServletPath());
		return false;
	}
	
	public void setSkipUrls(List<String> urls) {
		this.skipUrlPatterns = compileWildcardPattern(urls);
	}
	
	public static boolean matches(Pattern[] patterns, String string) {
		for (int i = 0; i < patterns.length; ++i) {
			if (patterns[i].matcher(string).matches())
				return true;
		}	
		return false;
	}
	
	public static Pattern[] compileWildcardPattern(List<String> wildcard) {
		Pattern[] patterns = new Pattern[wildcard.size()];
		for (int i = 0; i < wildcard.size(); ++i) {
			patterns[i] = compileWildcardPattern(((String) wildcard.get(i)).trim(), false);
		}
		return patterns;
	}
	
	public static Pattern compileWildcardPattern(String wildcard, boolean ignoreCase) {
		String wildcardLocal = wildcard;
		String wildcardPattern = "";
		
		Matcher escapeMatcher = ESCAPE_PATTERN.matcher(wildcardLocal);
		wildcardLocal = escapeMatcher.replaceAll("\\\\$1");
		
		Matcher asterisMatcher = ASTERISK_PATTERN.matcher(wildcardLocal);
		wildcardLocal = asterisMatcher.replaceAll("(.*)");
		
		Matcher questionMatcher = QUESTION_PATTERN.matcher(wildcardLocal);
		wildcardLocal = questionMatcher.replaceAll("(.)$1");
		
		Matcher multipleMatcher = MULTIPLE_PATTERN.matcher(wildcardLocal);
		wildcardLocal = multipleMatcher.replaceAll(")|(");
		
		wildcardPattern = "(" + wildcardLocal + ")";
		
		if (!(ignoreCase)) {
			return Pattern.compile(wildcardPattern);
		}
		return Pattern.compile(wildcardPattern, 2);
	}
	
}
