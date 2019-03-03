package com.thinking.machines.socket.framework.util;
import com.thinking.machines.socket.framework.common.*;
import com.thinking.machines.socket.framework.factory.*;
final public class Pools
{
private static Pool<String,Class> classPool;
private static Pool<String,Object> objectPool;
static
{
classPool=new Pool<String,Class>(new ClassFactory());
objectPool=new Pool<String,Object>(new ObjectFactory(classPool));
}
public static Object getObject(String type)
{
return objectPool.get(type);
}
public static Class instanceOf(String type)
{
return classPool.get(type);
}
}