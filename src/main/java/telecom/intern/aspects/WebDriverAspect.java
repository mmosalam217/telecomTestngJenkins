package telecom.intern.aspects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import telecom.intern.annotations.Chrome;
import telecom.intern.annotations.Edge;
import telecom.intern.annotations.Firefox;
import telecom.intern.helpers.ReflectHelper;

@Aspect
public class WebDriverAspect {
	
	@Around("execution(* *(..)) && @annotation(telecom.intern.annotations.Chrome)")
	public Object createChromeInstance(ProceedingJoinPoint pjp) throws Throwable {
		var methodSignature = (MethodSignature) pjp.getSignature();
		Method method = (Method) methodSignature.getMethod();
		Chrome chromeAnnotation = method.getAnnotation(Chrome.class);
		var args = pjp.getArgs();
		var that = pjp.getThis();
		boolean objHasDriver = ReflectHelper.objectHasProperty(that, "driver");
		if(objHasDriver) {
			Object currentFieldValue = ReflectHelper.getCurrentFieldValue(that, "driver");
			if (currentFieldValue != null && currentFieldValue instanceof WebDriver) {
				return pjp.proceed();
        	}
			ChromeDriver chromeDriver = new ChromeDriver();
			ReflectHelper.updateFieldValue(that, "driver", chromeDriver);
		}
		return pjp.proceed();
	}
	
	@Around("execution(* *(..)) && @annotation(telecom.intern.annotations.Edge)")
	public Object createEdgeInstance(ProceedingJoinPoint pjp) throws Throwable {
		var methodSignature = (MethodSignature) pjp.getSignature();
		Method method = (Method) methodSignature.getMethod();
		Edge edgeAnnotation = method.getAnnotation(Edge.class);
		var args = pjp.getArgs();
		var that = pjp.getThis();
		boolean objHasDriver = ReflectHelper.objectHasProperty(that, "driver");
		if(objHasDriver) {
			Object currentFieldValue = ReflectHelper.getCurrentFieldValue(that, "driver");
			if (currentFieldValue != null && currentFieldValue instanceof WebDriver) {
				return pjp.proceed();
        	}
			EdgeDriver edgeDriver = new EdgeDriver();
			ReflectHelper.updateFieldValue(that, "driver", edgeDriver);
		}
		return pjp.proceed();
	}
	
	@Around("execution(* *(..)) && @annotation(telecom.intern.annotations.Firefox)")
	public Object createFirefoxInstance(ProceedingJoinPoint pjp) throws Throwable {
		var methodSignature = (MethodSignature) pjp.getSignature();
		Method method = (Method) methodSignature.getMethod();
		Firefox firefoxAnnotation = method.getAnnotation(Firefox.class);
		var args = pjp.getArgs();
		var that = pjp.getThis();
		boolean objHasDriver = ReflectHelper.objectHasProperty(that, "driver");
		if(objHasDriver) {
			Object currentFieldValue = ReflectHelper.getCurrentFieldValue(that, "driver");
			if (currentFieldValue != null && currentFieldValue instanceof WebDriver) {
				return pjp.proceed();
        	}
			FirefoxDriver firefoxDriver = new FirefoxDriver();
			ReflectHelper.updateFieldValue(that, "driver", firefoxDriver);
		}
		return pjp.proceed();
	}

}
