package com.thinking.machines.socket.framework.server.event;
public interface ServerListener
{
public void clientConnected(ServerEvent serverEvent);
public void clientDisconnected(ServerEvent serverEvent);
public void serverStarted(ServerEvent serverEvent);
public void serverStoped(ServerEvent serverEvent);
}