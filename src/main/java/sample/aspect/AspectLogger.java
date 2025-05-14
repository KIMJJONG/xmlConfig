package sample.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import sample.model.ReqInfo;

public class AspectLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectLogger.class);
	
	@Resource(name="reqInfo")
	private ReqInfo reqInfo;
	
	@Resource(name="messageSource")
	private MessageSource messageSource;
	
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		StringBuilder argBuf = new StringBuilder();
		
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		StringBuilder reqBuf = new StringBuilder();
		reqBuf.append(reqInfo.getUri());
		reqBuf.append(", AOP 로그");
		reqBuf.append(", ").append(className).append(".").append(methodName);
		reqBuf.append("(").append(argBuf.length() > 100 ? argBuf.substring(0, 99) : argBuf.toString()).append(") started");
		LOGGER.info(reqBuf.toString());
		
		Object returnObject = joinPoint.proceed();
		return returnObject;
	}
	
	public void before(JoinPoint joinPoint) {}
	
	public void after(JoinPoint joinPoint) {}
	
}
