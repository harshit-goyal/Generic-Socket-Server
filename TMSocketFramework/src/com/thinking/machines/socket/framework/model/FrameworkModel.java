package com.thinking.machines.socket.framework.model;
import com.thinking.machines.socket.framework.util.*;
import com.thinking.machines.socket.framework.util.exceptions.*;
import com.thinking.machines.socket.framework.annotations.*;
import com.thinking.machines.socket.framework.common.*;
import com.thinking.machines.socket.framework.common.interfaces.*;
import com.thinking.machines.socket.framework.common.exceptions.*;
import com.thinking.machines.socket.framework.factory.*;
import java.io.*;
import java.util.*;
public class FrameworkModel
{
private Configuration configuration;
private HashSet<String> fullPackageNames;
private HashSet<String> partialPackageNames;
private HashMap<String,Path> paths;
private Application application=new Application();
public FrameworkModel() throws ConfigurationException
{
this.configuration=ConfigurationReader.getConfiguration(new File("Configuration.xml"));
HashSet<String> packages=new HashSet<String>();
fullPackageNames=new HashSet<String>();
partialPackageNames=new HashSet<String>();
// we will determine the folders/jars in classpath, we will scan
// all of them for properly packages classes whose package name
// is in the HashSet
packages=configuration.getPackages();
Iterator<String> iterator=packages.iterator();
String packageName;
while(iterator.hasNext())
{
packageName=iterator.next();
System.out.println("----"+packageName+"----------");
if(packageName.endsWith(".*"))
{
partialPackageNames.add(packageName.substring(0,packageName.length()-2));
}
else
{
fullPackageNames.add(packageName);
}
}
PathReader pathReader=new PathReader(fullPackageNames,partialPackageNames);
paths=pathReader.getPaths();
}
public int getSessionTimeout()
{
return this.configuration.getSessionTimeout();
}
public HashMap<String,Path> getPaths()
{
return this.paths;
}
public Path getPath(String path)
{
return paths.get(path);
}
public Application getApplication()
{
return this.application;
}
}