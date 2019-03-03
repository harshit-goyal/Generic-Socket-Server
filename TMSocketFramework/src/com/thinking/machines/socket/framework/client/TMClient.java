package com.thinking.machines.socket.framework.client;
import com.thinking.machines.socket.framework.common.*;
import com.thinking.machines.socket.framework.common.exceptions.*;
import java.net.*;
import java.util.*;
import java.io.*;
public class TMClient
{
private String host;
private int port;
private String clientID;
public static TMClient getInstance(String host,int port)
{
TMClient t=new TMClient(host,port);
return t;
}
private TMClient(String host,int port)
{
this.clientID=UUID.randomUUID().toString();
this.host=host;
this.port=port;
}
public Object invoke(String path,Object ...arguments) throws ServerException,ClassNotFoundException,Throwable
{
if(arguments==null) arguments=new Object[0];
if(path==null) throw new NullPointerException("Path is null");
if(path.trim().length()==0) throw new NullPointerException("Path is empty");
byte responseBytes[]=null;
RequestWrapper requestWrapper=null;
requestWrapper=new RequestWrapper(clientID,path,arguments);
ByteArrayOutputStream byteArrayOutputStream;
byteArrayOutputStream=new ByteArrayOutputStream();
ObjectOutputStream objectOutputStream=null;
try
{
objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
objectOutputStream.writeObject(requestWrapper);
objectOutputStream.flush();
}catch(IOException ioException)
{
throw new ServerException(ioException.getMessage());
}
byte contentBytes[]=byteArrayOutputStream.toByteArray();
int requestContentLength=contentBytes.length;
byte requestHeader[]=Protocol.Request.createHeader(requestContentLength);
Socket socket=null;
try
{
socket=new Socket(host,port);
}catch(UnknownHostException unknownHostException)
{
throw new ServerException("invalid host or port details ("+host+"/"+port+")");
}
OutputStream outputStream=null;
try
{
outputStream=socket.getOutputStream();
outputStream.write(requestHeader,0,requestHeader.length);
outputStream.flush();
}catch(IOException ioException)
{
try
{
socket.close();
}catch(IOException ioException2)
{
}
throw new ServerException("unable to connect,contact administration");
}
byte acknowledgement[]=new byte[Protocol.acknowledgement.length];
int totalBytesRead,bytesRead;
InputStream inputStream=null;
try
{
inputStream=socket.getInputStream();
bytesRead=inputStream.read(acknowledgement);
}catch(IOException ioException)
{
try
{
socket.close();
}catch(IOException ioException2)
{}
throw new ServerException("unable to connect ,connect to administrator.");
}
int bufferSize=Protocol.Request.CONTENT_SEGMENT_SIZE;
int numberOfBytesToWrite=bufferSize;
int i=0;
while(i<requestContentLength)
{
if(i+bufferSize>requestContentLength)
{
numberOfBytesToWrite=requestContentLength-i;
}
try
{
outputStream.write(contentBytes,i,numberOfBytesToWrite);
outputStream.flush();
}catch(IOException ioException)
{
try
{
socket.close();
}catch(IOException ioException2)
{
}
throw new ServerException("cant connect to server,contact toadministrator.");
}
i=i+bufferSize;
}
byte responseHeader[]=new byte[Protocol.Response.HEADER_LENGTH];
int responseContentLength=0;
try
{
bytesRead=inputStream.read(responseHeader);
responseContentLength=Protocol.Response.getContentLength(responseHeader);
outputStream.write(Protocol.acknowledgement,0,Protocol.acknowledgement.length);
outputStream.flush();
}catch(IOException ioException)
{
try
{
socket.close();
}catch(IOException ioException2)
{}
throw new ServerException("unable to connect,contact administrator.");
}
responseBytes=new byte[Protocol.Response.CONTENT_SEGMENT_SIZE];
totalBytesRead=0;
byteArrayOutputStream=new ByteArrayOutputStream();
try
{
while(true)
{
bytesRead=inputStream.read(responseBytes);
totalBytesRead+=bytesRead;
byteArrayOutputStream.write(responseBytes,0,bytesRead);
byteArrayOutputStream.flush();
if(totalBytesRead==responseContentLength) break;
}
}catch(IOException ioException)
{
try
{
socket.close();
}catch(IOException ioException2)
{}
throw new ServerException("unable to connect,contact administrator.");
}
responseBytes=byteArrayOutputStream.toByteArray();
try
{
socket.close();
}catch(IOException ioException)
{
}
ByteArrayInputStream byteArrayInputStream=null;
ObjectInputStream objectInputStream=null;
byteArrayInputStream=new ByteArrayInputStream(responseBytes);
try
{
objectInputStream=new ObjectInputStream(byteArrayInputStream);
}catch(IOException ioException)
{
throw new ServerException("unable to connect,contact administrator.");
}
ResponseWrapper responseWrapper;
try
{
responseWrapper=(ResponseWrapper)objectInputStream.readObject();
}catch(IOException ioException)
{
throw new ServerException(ioException.getMessage());
}
Object response=responseWrapper.getResponse();
if(responseWrapper.isException())
{
throw ((Throwable)response);
}
Object responseArguments[]=responseWrapper.getArguments();
for(int e=0;e<arguments.length;e++)
{
if(responseArguments[e]!=null)
{
if(responseArguments[e].getClass().isArray())
{
ArrayCopy.copy(arguments[e],responseArguments[e]);
}
else if(responseArguments[e] instanceof Collection)
{
Collection sourceCollection=(Collection)responseArguments[e];
Collection targetCollection=(Collection)arguments[e];
targetCollection.clear();
targetCollection.addAll(sourceCollection);
}else if(responseArguments[e] instanceof Map)
{
Map sourceMap=(Map)responseArguments[e];
Map targetMap=(Map)arguments[e];
targetMap.clear();
targetMap.putAll(sourceMap);
}
else
{
PojoCopyer.copy(arguments[e],responseArguments[e]);
}
}
}
return response;
}
}