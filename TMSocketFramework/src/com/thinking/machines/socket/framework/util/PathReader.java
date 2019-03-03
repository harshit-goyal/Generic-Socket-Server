package com.thinking.machines.socket.framework.util;
import com.thinking.machines.socket.framework.util.exceptions.*;

import java.net.*;
import java.io.*;
import java.util.zip.*;
import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
public class PathReader
{
private HashSet<String> fullPackageNames;
private HashSet<String> partialPackageNames;
public PathReader(HashSet<String> fullPackageNames,HashSet<String> partialPackageNames)
{
this.fullPackageNames=fullPackageNames;
this.partialPackageNames=partialPackageNames;
System.out.println("Path reader m h");
}
public HashMap<String,com.thinking.machines.socket.framework.model.Path> getPaths()
{
ClassLoader systemClassLoader=ClassLoader.getSystemClassLoader();
URL url[]=((URLClassLoader)systemClassLoader).getURLs();
ArrayList<File> directories=new ArrayList<File>();
ArrayList<File> jars=new ArrayList<File>();
File file;
for(int i=0;i<url.length;i++)
{
file=new File(url[i].getFile());
if(file.isDirectory())
{
directories.add(file);
}
else
{
if(file.getName().endsWith(".jar"))
{
jars.add(file);
}
}
}
System.out.println("Number of directories : "+directories.size());
System.out.println("Number of jars : "+jars.size());
HashMap<String,com.thinking.machines.socket.framework.model.Path> paths=new HashMap<String,com.thinking.machines.socket.framework.model.Path>();
for(int e=0;e<directories.size();e++)
{
populatePathsFromDirectory(directories.get(e),paths,directories.get(e).getAbsolutePath());
}
populatePathsFromJars(jars,paths);
// display results
Iterator iterator=paths.entrySet().iterator();
System.out.println(paths.size());
Map.Entry entry;
while(iterator.hasNext())
{
entry=(Map.Entry)iterator.next();
}
return paths;
}
public void populatePathsFromDirectory(File directory,HashMap<String,com.thinking.machines.socket.framework.model.Path> paths,String baseDirectory)
{
String classFileName;
File []files;
files=directory.listFiles();
boolean consider;
String packageName;
Iterator<String> iterator;
String partialPackageName;
for(int e=0;e<files.length;e++)
{
if(files[e].isDirectory())
{
populatePathsFromDirectory(files[e],paths,baseDirectory);
}
else
{
if(files[e].getName().endsWith(".class"))
{
classFileName=files[e].getAbsolutePath().substring(baseDirectory.length()+1).replaceAll("/",".").replaceAll("\\\\",".");
classFileName=classFileName.substring(0,classFileName.length()-6);
System.out.println(classFileName);
if(classFileName.indexOf(".")!=-1)
{
packageName=classFileName.substring(0,classFileName.lastIndexOf("."));
consider=false;
if(fullPackageNames.contains(packageName))
{
System.out.println(packageName);
consider=true;
}
else
{
iterator=partialPackageNames.iterator();
while(iterator.hasNext())
{
partialPackageName=iterator.next();
if(packageName.startsWith(partialPackageName))
{
consider=true;
break;
}
}
}
if(consider) setPaths(classFileName,paths);
}
}
}
}
}
public void populatePathsFromJars(ArrayList<File> jars,HashMap<String,com.thinking.machines.socket.framework.model.Path> paths)
{
boolean consider;
String packageName;
Iterator<String> iterator;
String partialPackageName;
ZipInputStream jarFile;
for(int e=0;e<jars.size();e++)
{
try
{
jarFile=new ZipInputStream(new FileInputStream(jars.get(e)));
ZipEntry entry=jarFile.getNextEntry();
String classFileName;
while(entry!=null)
{
if(entry.isDirectory()==false && entry.getName().endsWith(".class"))
{
classFileName=entry.getName();
classFileName=classFileName.substring(0,classFileName.length()-6).replaceAll("/",".").replaceAll("\\\\",".");
System.out.println(classFileName);
if(classFileName.indexOf(".")!=-1)
{
packageName=classFileName.substring(0,classFileName.lastIndexOf("."));
consider=false;
if(fullPackageNames.contains(packageName))
{
System.out.println(packageName);
consider=true;
}
else
{
iterator=partialPackageNames.iterator();
while(iterator.hasNext())
{
partialPackageName=iterator.next();
if(packageName.startsWith(partialPackageName))
{
consider=true;
break;
}
}
}
if(consider) setPaths(classFileName,paths);
}
}
entry=jarFile.getNextEntry();
}
}
catch(Exception ee)
{
}
}
}
public void setPaths(String className,HashMap<String,com.thinking.machines.socket.framework.model.Path> paths)
{ 
System.out.println("Processing :"+className);
try
{
Class c=Class.forName(className);
Annotation a=c.getAnnotation(com.thinking.machines.socket.framework.annotations.Path.class);
if(a==null) return;
String pathToClass;
pathToClass=((com.thinking.machines.socket.framework.annotations.Path)a).value();
if(pathToClass==null) return;
Method methods[]=c.getMethods();
String pathToMethod;
String path;
boolean isApplicationAware;
boolean isSessionAware;
for(int e=0;e<methods.length;e++)
{
a=methods[e].getAnnotation(com.thinking.machines.socket.framework.annotations.Path.class);
if(a!=null)
{
pathToMethod=((com.thinking.machines.socket.framework.annotations.Path)a).value();
if(pathToMethod!=null)
{
path=pathToClass+"/"+pathToMethod;
if(isValidPath(path))
{
if(paths.get(path)==null)
{
isApplicationAware=false;
}
a=methods[e].getAnnotation(com.thinking.machines.socket.framework.annotations.ApplicationAware.class);
isApplicationAware=(a!=null);
if(!isApplicationAware)
{
a=methods[e].getAnnotation(com.thinking.machines.socket.framework.annotations.ApplicationAware.class);
isApplicationAware=(a!=null);
}
isSessionAware=false; // not necessary, just for clearity
a=c.getAnnotation(com.thinking.machines.socket.framework.annotations.SessionAware.class);
isSessionAware=(a!=null);
if(!isSessionAware)
{
a=methods[e].getAnnotation(com.thinking.machines.socket.framework.annotations.SessionAware.class);
isSessionAware=(a!=null);
}
Class [] parameterTypes;
parameterTypes=methods[e].getParameterTypes();
int awarenessFactor=0;
if(isSessionAware) awarenessFactor++;
if(isApplicationAware) awarenessFactor++;

if(parameterTypes.length<awarenessFactor) return;

if(isSessionAware && isApplicationAware)
{
if(parameterTypes[parameterTypes.length-1].hashCode()-parameterTypes[parameterTypes.length-2].hashCode()==0) return;

if(parameterTypes[parameterTypes.length-1].getName().equals("com.thinking.machines.socket.framework.model.Application")==false && parameterTypes[parameterTypes.length-1].getName().equals("com.thinking.machines.socket.framework.model.Session")==false) return;

if(parameterTypes[parameterTypes.length-2].getName().equals("com.thinking.machines.socket.framework.model.Application")==false && parameterTypes[parameterTypes.length-2].getName().equals("com.thinking.machines.socket.framework.model.Session")==false) return;
}else if(isSessionAware)
{
if(parameterTypes[parameterTypes.length-1].getName().equals("com.thinking.machines.socket.framework.model.Session")==false) return;
}else if(isApplicationAware)
{
if(parameterTypes[parameterTypes.length-1].getName().equals("com.thinking.machines.socket.framework.model.Application")==false) return;
}
paths.put(path,new com.thinking.machines.socket.framework.model.Path(path,c.getName(),methods[e],parameterTypes,isApplicationAware,isSessionAware));
}
}
}
}
}
catch(Throwable throwable)
{
return;
}
}
public boolean isValidPath(String path)
{
String v="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/";
if(path==null || path.length()<=3) return false;
if(path.charAt(0)!='/') return false;
if(path.charAt(path.length()-1)=='/') return false;
for(int x=0;x<path.length();x++)
{
if(v.indexOf(path.charAt(x))==-1) return false;
}
if(path.indexOf("//")!=-1) return false;
return true;
}
}