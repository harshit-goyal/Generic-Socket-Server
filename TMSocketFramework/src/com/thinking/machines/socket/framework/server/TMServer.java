package com.thinking.machines.socket.framework.server;
import java.net.*;
import com.thinking.machines.socket.framework.model.*;
import com.thinking.machines.socket.framework.common.exceptions.*;
import com.thinking.machines.socket.framework.server.event.*;
import com.thinking.machines.socket.framework.server.event.interfaces.*;
import com.thinking.machines.socket.framework.util.*;
import com.thinking.machines.socket.framework.util.exceptions.*;
import java.util.*;
import com.thinking.machines.socket.framework.common.*;
public class TMServer implements ProcessorListener,Server
{
private SessionManager sessionManager;
private ServerListener serverListener;
private enum SERVER_STATE{ON,OFF};
private SERVER_STATE serverState;
private int portNumber;
private ServerSocket serverSocket;
private FrameworkModel frameworkModel;
private HashSet<Processor> processors;
public TMServer(int portNumber) throws ServerException
{
this.portNumber=portNumber;
try
{
frameworkModel=new FrameworkModel();
}
catch(ConfigurationException configurationException)
{
throw new ServerException(configurationException.getMessage());
}
sessionManager=new SessionManager(frameworkModel.getSessionTimeout());
}
public void start() throws ServerException
{
initialize();
startListening();
}
private void initialize() throws ServerException
{
System.out.println("initilaize() m");
this.processors=new HashSet<Processor>();
try
{
serverSocket=new ServerSocket(this.portNumber);
this.serverState=SERVER_STATE.OFF;
}catch(Exception exception)
{
throw new ServerException(exception.getMessage());
}
}
public void startListening()
{
raiseServerStartedEvent();
Socket socket;
Processor processor;
while(true)
{
try
{
System.out.println("start Listeneing() m");
this.serverState=SERVER_STATE.ON;
socket=serverSocket.accept();
System.out.println("ai");
processor=new RequestProcessor(sessionManager,frameworkModel,socket);
processor.setProcessorListener(this);
processors.add(processor);
String clientAddress;
clientAddress=(((InetSocketAddress)socket.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
raiseClientConnectedEvent(clientAddress);
processor.start();
}catch(Exception exception)
{
if(this.serverState==SERVER_STATE.OFF) break;
System.out.println(exception);
}
}
raiseServerStoppedEvent();
}
private void stopListening()
{
this.serverState=SERVER_STATE.OFF;
try
{
serverSocket.close();
}catch(Exception exception)
{
System.out.println(exception);
}
}
public boolean areClientsConnexted()
{
return processors.size()>0;
}public void stop(boolean disconnectClients) throws ServerException
{
int size=processors.size();
if(size>0 && disconnectClients==false) throw new ServerException("clients are connected");
stopListening();
if(size>0)
{
Iterator<Processor> iterator=processors.iterator();
Processor processor;
while(iterator.hasNext())
{
processor=iterator.next();
if(processor.isWorking()) processor.stop();
}
processors.clear();
}
}
public int getCountOfClientsConnected()
{
return this.processors.size();
}
public void processingCompleted(ProcessEvent processEvent)
{
Processor processor=processEvent.getProcessor();
Socket socket=processor.getSocket();
String clientAddress;
clientAddress=(((InetSocketAddress)socket.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
raiseClientDisconnectedEvent(clientAddress);
}
public void setServerListener(ServerListener serverListener)
{
this.serverListener=serverListener;
System.out.println("Deepesh dwivedi setServerListener");
System.out.println("("+this.serverListener+")");
}
//events raise
private void raiseServerStartedEvent()
{
if(this.serverListener==null)
{
System.out.println("problam h");
return;
}
System.out.println("raiseServerStartedEvent");
ServerEvent serverEvent=new ServerEvent(this,new Date());
serverListener.serverStarted(serverEvent);
}
private void raiseServerStoppedEvent()
{
if(this.serverListener==null) return;

ServerEvent serverEvent=new ServerEvent(this,new Date());
serverListener.serverStoped(serverEvent);
}
private void raiseClientConnectedEvent(String clientAddress)
{
if(this.serverListener==null) return;
System.out.println("Event m");
ServerEvent serverEvent=new ServerEvent(this,new Date(),clientAddress);
serverListener.clientConnected(serverEvent);
}
private void raiseClientDisconnectedEvent(String clientAddress)
{
if(this.serverListener==null) return;
ServerEvent serverEvent=new ServerEvent(this,new Date(),clientAddress);
serverListener.clientDisconnected(serverEvent);
}
}