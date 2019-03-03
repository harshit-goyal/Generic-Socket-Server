package com.thinking.machines.socket.framework.server;
import java.util.*;
import com.thinking.machines.socket.framework.model.*;
public class SessionManager
{
HashMap<String,Session> sessions=new HashMap<String,Session>();
private static final int DEFAULT_SESSION_TIME_OUT=60;
private int sessionTimeout;
public SessionManager(int sessionTimeout)
{
if(sessionTimeout<=0)
{
sessionTimeout=DEFAULT_SESSION_TIME_OUT;
}
this.sessionTimeout=sessionTimeout;
}
synchronized public Session getSession(String clientID)
{
Session session=sessions.get(clientID);
if(session!=null && session.isDead())
{
sessions.remove(clientID);
session.removeAllAttributes();
session=null;
}
if(session==null)
{
session=new Session(clientID,sessionTimeout);
sessions.put(clientID,session);
}
return session;
}
synchronized public void updateLastAccessTime(String clientID)
{
sessions.get(clientID).updateLastAccessTime();
}
}