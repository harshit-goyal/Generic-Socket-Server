package com.thinking.machines.socket.framework.factory;
import com.thinking.machines.socket.framework.common.interfaces.*;
public class ClassFactory implements FactoryInterface<String,Class>
{
public Class get(String className)
{
Class c=null;
try
{
c=Class.forName(className);
}catch(ClassNotFoundException classNotFoundException)
{
System.out.println(classNotFoundException);
}
return c;
}
}