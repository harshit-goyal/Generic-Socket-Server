package com.thinking.machines.socket.framework.server.event;
import com.thinking.machines.socket.framework.server.event.interfaces.*;

import java.util.*;
public class ServerEvent
{
private Server server;
private String clientAddress;
private Date date;
public ServerEvent(Server server,Date date)
{
this(server,date,null);
}
public ServerEvent(Server server,Date date,String clientAddress)
{
this.date=date;
this.server=server;
this.clientAddress=clientAddress;
}
public Server getServer()
{
return this.server;
}
public Date getDate()
{
return this.date;
}
public String getClientAddress()
{
return this.clientAddress;
}
}