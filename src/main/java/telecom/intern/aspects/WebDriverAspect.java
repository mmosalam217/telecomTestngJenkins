package telecom.intern.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
		boolean headless = chromeAnnotation.headless();
		String remoteOrigins = chromeAnnotation.allowRemoteOrigins();
		String windowSize = chromeAnnotation.windowSize();
		var args = pjp.getArgs();
		var that = pjp.getThis();
		boolean objHasDriver = ReflectHelper.objectHasProperty(that, "driver");
		if(objHasDriver) {
			Object currentFieldValue = ReflectHelper.getCurrentFieldValue(that, "driver");
			if (currentFieldValue != null && currentFieldValue instanceof WebDriver) {
				return pjp.proceed();
        	}
			ChromeOptions opts = new ChromeOptions();
			opts.addArguments("--remote-allow-origins=" + remoteOrigins);
			if(headless) {
				opts.addArguments("--headless");
				opts.addArguments("window-size=" + windowSize);
			} 
			
			ChromeDriver chromeDriver = new ChromeDriver(opts);
			ReflectHelper.updateFieldValue(that, "driver", chromeDriver);
		}
		return pjp.proceed();
	}
	
	@Around("execution(* *(..)) && @annotation(telecom.intern.annotations.Edge)")
	public Object createEdgeInstance(ProceedingJoinPoint pjp) throws Throwable {
		var methodSignature = (MethodSignature) pjp.getSignature();
		Method method = (Method) methodSignature.getMethod();
		Edge edgeAnnotation = method.getAnnotation(Edge.class);
		boolean headless = edgeAnnotation.headless();
		String remoteOrigins = edgeAnnotation.allowRemoteOrigins();
		String windowSize = edgeAnnotation.windowSize();
		var args = pjp.getArgs();
		var that = pjp.getThis();
		boolean objHasDriver = ReflectHelper.objectHasProperty(that, "driver");
		if(objHasDriver) {
			Object currentFieldValue = ReflectHelper.getCurrentFieldValue(that, "driver");
			if (currentFieldValue != null && currentFieldValue instanceof WebDriver) {
				return pjp.proceed();
        	}
			EdgeOptions opts = new EdgeOptions();
			opts.addArguments("--remote-allow-origins=" + remoteOrigins);
			if(headless) {
				opts.addArguments("--headless");
				opts.addArguments("window-size=" + windowSize);
			}
			EdgeDriver edgeDriver = new EdgeDriver(opts);
			ReflectHelper.updateFieldValue(that, "driver", edgeDriver);
		}
		return pjp.proceed();
	}
	
	@Around("execution(* *(..)) && @annotation(telecom.intern.annotations.Firefox)")
	public Object createFirefoxInstance(ProceedingJoinPoint pjp) throws Throwable {
		var methodSignature = (MethodSignature) pjp.getSignature();
		Method method = (Method) methodSignature.getMethod();
		Firefox firefoxAnnotation = method.getAnnotation(Firefox.class);
		boolean headless = firefoxAnnotation.headless();
		String remoteOrigins = firefoxAnnotation.allowRemoteOrigins();
		String windowSize = firefoxAnnotation.windowSize();
		var args = pjp.getArgs();
		var that = pjp.getThis();
		boolean objHasDriver = ReflectHelper.objectHasProperty(that, "driver");
		if(objHasDriver) {
			Object currentFieldValue = ReflectHelper.getCurrentFieldValue(that, "driver");
			if (currentFieldValue != null && currentFieldValue instanceof WebDriver) {
				return pjp.proceed();
        	}
			FirefoxOptions opts = new FirefoxOptions();
			opts.addArguments("--remote-allow-origins=" + remoteOrigins);
			if(headless) {
				opts.addArguments("--headless");
				opts.addArguments("window-size=" + windowSize);
			} 
			FirefoxDriver firefoxDriver = new FirefoxDriver(opts);
			ReflectHelper.updateFieldValue(that, "driver", firefoxDriver);
		}
		return pjp.proceed();
	}

}
