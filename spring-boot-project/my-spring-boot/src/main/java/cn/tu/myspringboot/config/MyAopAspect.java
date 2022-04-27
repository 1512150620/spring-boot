package cn.tu.myspringboot.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * SpringBoot中的AOP使用
 * https://blog.csdn.net/weixin_45583303/article/details/118565966
 */
@Component
@Aspect
public class MyAopAspect {

	//定义切入点：所有service的方法。
	@Pointcut("execution(public * cn.tu.myspringboot.service.*.*(..))")
	public void pointCutAll(){
		System.out.println("pointCutAll()");
	}

	//定义切入点：AopService.m1()方法。
	@Pointcut("execution(public String cn.tu.myspringboot.service.AopService.m1(String))")
	public void pointCutM1(){
		System.out.println("pointCutM1()");
	}

	//定义切入点：所有使用了注解com.sun.istack.internal.NotNull的方法。
	@Pointcut("@annotation(com.sun.istack.internal.NotNull)")
	public void pointCutAnnotation(){
		System.out.println("pointCutAnnotation()");
	}

	//定义通知：前置通知
	@Before("pointCutM1()")
	public void doBefore(){
		System.out.println("doBefore()");
	}

	//定义通知：后置通知
	@After("pointCutM1()")
	public void doAfter(){
		System.out.println("doAfter()");
	}

	//定义通知：前置通知
	@Before("pointCutAll()")
	public void doBeforePointCutAll(){
		System.out.println("doBeforePointCutAll()");
	}

	//定义通知：前置通知
	@Before("pointCutAnnotation()")
	public void doBeforePointCutAnnotation(){
		System.out.println("doBeforePointCutAnnotation()");
	}
}
