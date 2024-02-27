package telecom.intern.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Edge {
	boolean headless() default false;
	String allowRemoteOrigins() default "*";
	String windowSize() default "1200,1100";
}
