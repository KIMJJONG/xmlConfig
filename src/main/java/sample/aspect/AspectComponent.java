package sample.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

//@EnableAspectJAutoProxy
//@Aspect
//@Component
public class AspectComponent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectComponent.class);
	
	public AspectComponent() {
		LOGGER.info("AspectComponent 생성자");
	}
	
//	@Before("execution(* sample.controller.DoController.*(..))")
	public void beforeMethod() {
		LOGGER.info("메소드 시작");
	}
	
//	@Around("execution(* sample.service.*Impl.*(..))")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		Object result = new Object();
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		LOGGER.info("메소드 실행 시간: {}", endTime - startTime, " ms");
		return result;
	}
	
}
