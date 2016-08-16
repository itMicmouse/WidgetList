package org.micmource.appdagger2.module;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Scope;

/**
 * Created by yakun on 2016/8/16.
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {

}
