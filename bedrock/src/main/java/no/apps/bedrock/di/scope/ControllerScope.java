package no.apps.bedrock.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@SuppressWarnings("unused")
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerScope {
}
