package com.thinking.machines.socket.framework.model;
import com.thinking.machines.socket.framework.util.*;
import java.lang.reflect.*;
public class Path
{
private String url;
private String type;
private Method method;
private Class parameters[];
private Object object=null;
private boolean isApplicationAware;
private boolean isSessionAware;
public Path(String url,String type,Method method,Class parameters[],Object object,boolean isApplicationAware,boolean isSessionAware)
{
this.url=url;
this.type=type;
this.method=method;
this.parameters=parameters;
this.object=object;
this.isApplicationAware=isApplicationAware;
this.isSessionAware=isSessionAware;
}
public Path(String url,String type,Method method,Class parameters[],boolean isApplicationAware,boolean isSessionAware)
{
this.isApplicationAware=isApplicationAware;
this.isSessionAware=isSessionAware;
this.url=url;
this.type=type;
this.method=method;
this.parameters=parameters;
this.object=null;
}
public String getURL()
{
return this.url;
}
public String getType()
{
return this.type;
}
public Method getMethod()
{
return this.method;
}
public Object getObject()
{
if(object==null)
{
object=Pools.getObject(type);
}
return this.object;
}
public boolean isApplicationAware()
{
return this.isApplicationAware;
}
public boolean isSessionAware()
{
return this.isSessionAware;
}
}