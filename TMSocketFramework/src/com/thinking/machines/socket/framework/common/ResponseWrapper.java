package com.thinking.machines.socket.framework.common;
public class ResponseWrapper implements java.io.Serializable
{
private Object response;
private boolean isException;
private Object arguments[];
public ResponseWrapper()
{
this.response=null;
this.isException=false;
}
public ResponseWrapper(Object response,boolean isException)
{
this.response=response;
this.isException=isException;
}
public Object getResponse()
{
return this.response;
}
public boolean isException()
{
return this.isException;
}
public void setArguments(Object arguments[])
{
this.arguments=arguments;
}
public Object[] getArguments()
{
return this.arguments;
}
}