package com.blockchain.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class GlobalAspect {


	private static final Logger log = Logger.getLogger(GlobalAspect.class);

	@Pointcut("execution(* com.blockchain.*.*.*(..))")
	private void pointCutMethod() {

	}

	
	@AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("未捕捉到的异常", e);
	}


	


}