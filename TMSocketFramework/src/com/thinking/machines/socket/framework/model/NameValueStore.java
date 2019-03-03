package com.thinking.machines.socket.framework.model;
import java.util.*;
public class NameValueStore implements java.io.Serializable
{
private HashMap<String,Object> collection=new HashMap<String,Object>();
synchronized public void setAttribute(String name,Object value)
{
if(this.collection.get(name)!=null) this.collection.remove(name);
this.collection.put(name,value);
}
synchronized public void removeAttribute(String name)
{
this.collection.remove(name);
}
synchronized public Object getAttribute(String name)
{
return this.collection.get(name);
}
synchronized public void removeAllAttributes()
{
this.collection.clear();
}
}