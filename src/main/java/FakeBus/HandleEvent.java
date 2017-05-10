package FakeBus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import Events.Event;

/**
 * @author Darkness
 * @date 2017年5月10日 下午5:05:24
 * @version 1.0
 * @since 1.0 
 */
@Target(ElementType.METHOD)      
@Retention(RetentionPolicy.RUNTIME)  
public @interface HandleEvent {  
	Class<? extends Event>[] events() default {};
	String[] eventNames() default {};
}  
