package com.thinking.machines.socket.framework.factory;
import com.thinking.machines.socket.framework.common.interfaces.*;
import com.thinking.machines.socket.framework.common.*;
public class ObjectFactory implements FactoryInterface<String,Object>
{
private Pool<String,Class> classPool;
public ObjectFactory(Pool<String,Class> classPool)
{
this.classPool=classPool;
}
public Object get(String className)
{
Object o=null;
try
{
Class c=classPool.get(className);
if(c!=null) o=c.newInstance();
}catch(Exception exception)
{
System.out.println(exception);
}
return o;
}
}
