package com.thinking.machines.socket.framework.server.event.interfaces;
import com.thinking.machines.socket.framework.server.event.*;

import java.net.*;
public interface Processor
{
public void start();
public void stop();
public boolean isWorking();
public void setProcessorListener(ProcessorListener ProcessorListener);
public Socket getSocket();
}