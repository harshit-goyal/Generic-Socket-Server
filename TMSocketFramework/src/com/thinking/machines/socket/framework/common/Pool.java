package com.thinking.machines.socket.framework.common;
import com.thinking.machines.socket.framework.common.interfaces.*;
import java.util.*;
public class Pool<T1,T2>
{
private HashMap<T1,T2> hashMap=new HashMap<T1,T2>();
private FactoryInterface<T1,T2> factoryInterface;
public Pool(FactoryInterface<T1,T2> factoryInterface)
{
this.factoryInterface=factoryInterface;
}
public int size()
{
return this.hashMap.size();
}
public T2 get(T1 key)
{
T2 t=hashMap.get(key);
if(t==null)
{
t=factoryInterface.get(key);
if(t!=null) hashMap.put(key,t);
}
return t;
}
}