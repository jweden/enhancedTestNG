package weden.jason.qa.testNG;

/**
 * Support for the @Requirements annotation
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Requirements {

    String[] reqs();

}
