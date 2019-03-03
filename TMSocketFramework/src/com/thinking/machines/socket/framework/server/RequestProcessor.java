package com.thinking.machines.socket.framework.server;
import com.thinking.machines.socket.framework.server.event.*;
import com.thinking.machines.socket.framework.common.exceptions.*;
import com.thinking.machines.socket.framework.common.*;
import com.thinking.machines.socket.framework.server.event.interfaces.*;
import java.lang.reflect.*;
import java.io.*;
import java.net.*;
import com.thinking.machines.socket.framework.model.*;
public class RequestProcessor implements Processor,Runnable
{
private Thread thread;
private FrameworkModel frameworkModel;
private Socket socket;
private boolean isWorking=false;
private ProcessorListener processorListener;
private SessionManager sessionManager;
public RequestProcessor(SessionManager sessionManager,FrameworkModel frameworkModel,Socket socket)
{
this.sessionManager=sessionManager;
this.frameworkModel=frameworkModel;
this.socket=socket;
this.processorListener=null;
}
public void setProcessorListener(ProcessorListener processorListener)
{ this.processorListener=processorListener;
}
public void start()
{ thread=new Thread(this);
thread.start();
}
public void stop()
{ isWorking=false;
if(thread.isAlive()) thread.stop();
}
public void run()
{
this.isWorking=true;
InputStream inputStream;
OutputStream outputStream;
try
{
int totalBytesRead,bytesRead;
byte requestHeader[]=new byte[Protocol.Request.HEADER_LENGTH];
inputStream=socket.getInputStream();
bytesRead=inputStream.read(requestHeader);
int requestContentLength=Protocol.Request.getContentLength(requestHeader);
outputStream=socket.getOutputStream();
outputStream.write(Protocol.acknowledgement,0,Protocol.acknowledgement.length);
outputStream.flush();
byte contentBytes[]=new byte[Protocol.Request.CONTENT_SEGMENT_SIZE];
totalBytesRead=0;
ByteArrayOutputStream byteArrayOutputStream;
byteArrayOutputStream=new ByteArrayOutputStream();
while(true)
{
bytesRead=inputStream.read(contentBytes);
totalBytesRead+=bytesRead;
byteArrayOutputStream.write(contentBytes,0,bytesRead);
byteArrayOutputStream.flush();
if(totalBytesRead==requestContentLength) break;
} contentBytes=byteArrayOutputStream.toByteArray();
// logic to deserialize
ByteArrayInputStream byteArrayInputStream;
byteArrayInputStream=new ByteArrayInputStream(contentBytes);
ObjectInputStream objectInputStream;
objectInputStream=new ObjectInputStream(byteArrayInputStream);
RequestWrapper requestWrapper;
requestWrapper=(RequestWrapper)objectInputStream.readObject();
String requestPath=requestWrapper.getPath();
Path path=frameworkModel.getPath(requestPath);
ResponseWrapper responseWrapper=null;
if(path==null)
{
responseWrapper=new ResponseWrapper(new ServerException("Invalid path : "+requestPath),true);
} else
{
// think about the code that should go here
Object arguments[]=requestWrapper.getArguments();
Object object=path.getObject();
Method method=path.getMethod();
Class parameters[]=method.getParameterTypes();
String clientID=requestWrapper.getClientID();
boolean isApplicationAware=path.isApplicationAware();
boolean isSessionAware=path.isSessionAware();
if(isSessionAware && isApplicationAware)
{
Object [] t=new Object[arguments.length+2];
for(int i=0;i<arguments.length;i++) t[i]=arguments[i];
if(parameters[parameters.length-1].getName().equals("com.thinking.machines.socket.framework.model.Application"))
{
t[arguments.length+1]=frameworkModel.getApplication();
t[arguments.length]=sessionManager.getSession(clientID);
}
else
{
t[arguments.length]=frameworkModel.getApplication();
t[arguments.length+1]=sessionManager.getSession(clientID);
}
arguments=t;
}
else if(isApplicationAware)
{
System.out.println("aya");
Object [] t=new Object[arguments.length+1];
for(int i=0;i<arguments.length;i++) t[i]=arguments[i];
t[arguments.length]=frameworkModel.getApplication();
arguments=t;
}
else if(isSessionAware)
{
Object [] t=new Object[arguments.length+1];
for(int i=0;i<arguments.length;i++) t[i]=arguments[i];
t[arguments.length]=sessionManager.getSession(clientID);
arguments=t;
}
if(parameters.length!=arguments.length)
{
System.out.println("paramenter length : "+parameters.length);
System.out.println("arguments length : "+arguments.length);
responseWrapper=new ResponseWrapper(new ServerException("Incorrect number of arguments,required : "+(parameters.length-((isApplicationAware)?1:0))),true);
}
else
{
try
{
Object response;
if(arguments.length>0)
{
response=method.invoke(object,arguments);
}
else
{
response=method.invoke(object);
}
responseWrapper=new ResponseWrapper(response,false);
Object reArguments[]=new Object[arguments.length];
for(int x=0;x<arguments.length;x++)
{
if(isCopyable(arguments[x]))
{
reArguments[x]=arguments[x];
}
else reArguments[x]=null;
}
responseWrapper.setArguments(reArguments);
}
catch(InvocationTargetException invocationTargetException)
{
responseWrapper=new ResponseWrapper(invocationTargetException.getCause(),true);
}catch(Throwable throwable)
{
responseWrapper=new ResponseWrapper(throwable,true);
}
}
byteArrayOutputStream=new ByteArrayOutputStream();
ObjectOutputStream objectOutputStream;
objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
objectOutputStream.writeObject(responseWrapper);
byte responseBytes[]=byteArrayOutputStream.toByteArray();
int responseContentLength=responseBytes.length;
byte responseHeader[];
responseHeader=Protocol.Response.createHeader(responseContentLength);
outputStream.write(responseHeader,0,responseHeader.length);
outputStream.flush();
byte acknowledgement[];
acknowledgement=new byte[Protocol.acknowledgement.length];
bytesRead=inputStream.read(acknowledgement);
// think about the code that should go here
int bufferSize=Protocol.Response.CONTENT_SEGMENT_SIZE;
int numberOfBytesToWrite=bufferSize;
int i=0;
while(i<responseContentLength)
{
if(i+bufferSize>responseContentLength)
{
numberOfBytesToWrite=responseContentLength-i;
}
outputStream.write(responseBytes,i,numberOfBytesToWrite);
outputStream.flush();
i=i+bufferSize;
}
sessionManager.updateLastAccessTime(clientID);
socket.close();
}
}catch(Exception exception)
{
System.out.println(exception);
}
isWorking=false;
if(this.processorListener!=null)
{
processorListener.processingCompleted(new ProcessEvent(this));
}
}
public boolean isWorking()
{
return this.isWorking;
}
public Socket getSocket()
{
return this.socket;
}
public boolean isCopyable(Object object)
{
String type=object.getClass().getSimpleName();
if(type.equals("int") || type.equals("Integer")) return false;
if(type.equals("byte")|| type.equals("Byte"))return false;
if(type.equals("float") || type.equals("Float"))  return false;
if(type.equals("boolean") || type.equals("Boolean"))  return false;
if(type.equals("double") || type.equals("java.lang.Double"))  return false;
if(type.equals("short") || type.equals("Short"))  return false;
if(type.equals("long") || type.equals("Long"))  return false;
if(type.equals("char") || type.equals("Character"))  return false;
if(type.equals("String")) return false;
return true;
}
}
