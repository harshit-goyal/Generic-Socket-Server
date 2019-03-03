package com.thinking.machines.socket.framework.util;
import java.util.*;
public class Configuration
{
private HashSet<String> packages;
private int sessionTimeout;
public Configuration(HashSet<String> packages,int sessionTimeout)
{
this.packages=packages;
this.sessionTimeout=sessionTimeout;
}
public int getSessionTimeout()
{
return this.sessionTimeout;
}
public HashSet<String> getPackages()
{
return this.packages;
}
}