package jpabook.jpashop.annotation;

import com.github.gavlyukovskiy.boot.jdbc.decorator.DataSourceDecoratorAutoConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jpabook.jpashop.annotation.p6spy.P6SpyFormatter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ImportAutoConfiguration(DataSourceDecoratorAutoConfiguration.class) // 1
@Import(P6SpyFormatter.class) // 2
@TestPropertySource(properties = {
        "logging.level.org.springframework.test.context=ERROR"
}) // 3
public @interface EnableQueryLog {

    @PropertyMapping("spring.jpa.show-sql") // 4
    boolean showSql() default false;

    @PropertyMapping("decorator.datasource.p6spy.enable-logging") // 5
    boolean enableLogging() default true;

}
