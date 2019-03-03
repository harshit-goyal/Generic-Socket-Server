package com.thinking.machines.socket.framework.client;
import java.util.*;
import java.lang.reflect.*;
public class PojoCopyer
{
private static boolean isGetter(String name)
{
if(name.length()<4)
{
return false;}
if( name.charAt(3)<=122 && name.charAt(3)>=97)
{
return false;
}
if(!(name.startsWith("get")))
{
return false;}
return true;
}
private static boolean isSetter(String name)
{
if(name.length()<4)
{
return false;
}
if(name.charAt(3)<=122 && name.charAt(3)>=97) return false;
if(!(name.startsWith("set")))
{
return false;
}
return true;
}
private static List<Pair<Method,Method>> getMethods(Class targetClass,Class sourceClass)
{
Method targetMethods[]=targetClass.getMethods(); 
Method sourceMethods[]=sourceClass.getMethods();
List<Pair<Method,Method>> methods=new ArrayList<Pair<Method,Method>>();
Pair<Method,Method> pair=null;
for(int e=0;e<targetMethods.length;e++)
{
if(isSetter(targetMethods[e].getName()))
{
for(int x=0;x<sourceMethods.length;x++)
{
if(isGetter(sourceMethods[x].getName()) && (sourceMethods[x].getName().substring(3).equals(targetMethods[e].getName().substring(3))))
{
if((sourceMethods[x].getReturnType()!=null) || (!(targetMethods[e].getParameterCount()>1) || !(targetMethods[e].getParameterCount()<1)))
{
pair=new Pair<Method,Method>(targetMethods[e],sourceMethods[x]);
methods.add(pair);
}
}
}
}
}
return methods;
}
public static void copy(Object target,Object source)
{
List<Pair<Method,Method>> methods=getMethods(target.getClass(),source.getClass());
Iterator iterator=methods.iterator();
Pair<Method,Method> pair=null;
Class c[]=null;
while(iterator.hasNext())
{
pair=(Pair<Method,Method>)iterator.next();
c=pair.getFrist().getParameterTypes();
if(c[0].getSimpleName().equals(pair.getTwo().getReturnType().getSimpleName()))
{
try
{
pair.getFrist().invoke(target,pair.getTwo().invoke(source));
}catch(Exception e)
{
System.out.println(e);
}
}
}
}
}