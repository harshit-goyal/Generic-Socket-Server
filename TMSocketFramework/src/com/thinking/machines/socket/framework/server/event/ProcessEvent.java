package com.thinking.machines.socket.framework.server.event;
import com.thinking.machines.socket.framework.server.event.interfaces.*;
public class ProcessEvent
{
private Processor processor;
public ProcessEvent(Processor processor)
{
this.processor=processor;
}
public Processor getProcessor()
{
return this.processor;
}
}