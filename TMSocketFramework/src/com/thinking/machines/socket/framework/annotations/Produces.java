package com.thinking.machines.socket.framework.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Produces{
public Data value();
}
