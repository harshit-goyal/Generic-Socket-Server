package com.thinking.machines.socket.framework.common;
import com.thinking.machines.socket.framework.model.*;
public class RequestWrapper implements java.io.Serializable
{
private String path;
private Object arguments[];
private String clientID;
public RequestWrapper()
{
this.path=null;
this.arguments=null;
}
public RequestWrapper(String clientID,String path,Object []arguments)
{ this.path=path;
this.clientID=clientID;
this.arguments=arguments;
}
public String getPath()
{
return this.path;
}
public Object [] getArguments()
{
return this.arguments;
}
public String getClientID()
{
return this.clientID;
}
}