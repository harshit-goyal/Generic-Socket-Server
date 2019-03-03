package com.thinking.machines.socket.framework.server.event.interfaces;
import com.thinking.machines.socket.framework.common.exceptions.*;
import com.thinking.machines.socket.framework.server.event.*;
public interface Server
{
public void start() throws ServerException; 
public void stop(boolean disconnectClients) throws ServerException;
public void setServerListener(ServerListener serverListener);
public int getCountOfClientsConnected();
}