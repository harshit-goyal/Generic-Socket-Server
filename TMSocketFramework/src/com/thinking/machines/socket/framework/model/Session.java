package com.thinking.machines.socket.framework.model;
import java.util.*;
public class Session extends NameValueStore implements Comparable<Session>
{
private String clientID;
private int sessionTimeout;
private long lastAccessTime;
public Session(String clientID,int sessionTimeout)
{
this.clientID=clientID;
this.sessionTimeout=sessionTimeout;
}
public String getClientID()
{
return this.clientID;
}
public boolean equals(Object object)
{
if(!(object instanceof Session)) return false;
Session other=(Session)object;
return this.clientID.equals(other.clientID);
}
public void setSessionTimeout(int sessionTimeout)
{
if(sessionTimeout<0)
{
sessionTimeout=0;
}
this.sessionTimeout=sessionTimeout;
}
public int getSessionTimeout()
{
return this.sessionTimeout;
}
public int compareTo(Session other)
{
return this.clientID.compareTo(other.clientID);
}
public int hashCode()
{
return this.clientID.hashCode();
}
public void invalidate()
{
super.removeAllAttributes();
this.sessionTimeout=0;
}
public boolean isDead()
{
return this.sessionTimeout==0 || ((new Date().getTime()-lastAccessTime)/60000)>=sessionTimeout;
}
public void updateLastAccessTime()
{
this.lastAccessTime=new Date().getTime();
}
}