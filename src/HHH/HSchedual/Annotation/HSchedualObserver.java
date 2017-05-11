package HHH.HSchedual.Annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import HHH.HSchedual.HSchedual;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HSchedualObserver {
	public HSchedual.executeType value();
}
