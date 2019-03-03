package com.thinking.machines.socket.framework.util;
import com.thinking.machines.socket.framework.util.exceptions.*;
import java.io.*;
import java.util.*;
import javax.xml.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
public class ConfigurationReader
{
private static Configuration configuration=null;
private ConfigurationReader()
{
}
public  static Configuration getConfiguration(File file) throws ConfigurationException
{
if(configuration!=null) return configuration;
try
{
if(file.exists()==false)
{
throw new ConfigurationException("Configuration file is missing : "+file.getAbsolutePath());
}
DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
Document document=documentBuilder.parse(file);
Node rootNode=document.getDocumentElement();
String rootNodeName=rootNode.getNodeName();
if(!(rootNodeName.equals("configuration")))
{
throw new ConfigurationException("invalid root Node,<configuration> expected");
}
validateConfiguration(rootNode);
Node packagesNode=getNode(rootNode.getChildNodes(),"packages");
validatePackages(packagesNode);
Node sessionTimeoutNode=getNode(rootNode.getChildNodes(),"session-timeout");
int sessionTimeout=0;
if(sessionTimeoutNode!=null)
{
try
{
sessionTimeout=Integer.parseInt(sessionTimeoutNode.getTextContent().replaceAll("\r","").replaceAll("\n","").trim());
if(sessionTimeout<0)
{
throw new ConfigurationException("invalid session-timeout : "+sessionTimeout);
}
}catch(NumberFormatException numberFormatException)
{
throw new ConfigurationException("invalid session-timeout : "+sessionTimeout);
}
}
HashSet<String> packages=getPackages(packagesNode);
configuration=new Configuration(packages,sessionTimeout);
}catch(Exception exception)
{
configuration=null;
throw new ConfigurationException("invalid Configuration : "+exception.getMessage());
}
return configuration;
}
private static void validateConfiguration(Node rootNode) throws ConfigurationException
{
HashMap<String,Boolean> validTags=new HashMap<String,Boolean>();
HashSet<String> processedTags=new HashSet<String>();
Boolean b=null;
validTags.put("packages",false); //false means it should be occure in ouns only and true means it can be occure multiple times
validTags.put("session-timeout",false); //false means it should be occure in ouns only and true means it can be occure multiple times
NodeList nodeList=rootNode.getChildNodes();
String nodeText=null;
for(int x=0;x<nodeList.getLength();x++)
{
nodeText=nodeList.item(x).getNodeName();
System.out.println("nodeText=nodeList.item(x).getNodeName()  ; "+nodeText);
if(nodeText.equals("#text")) continue;
b=validTags.get(nodeText);
if(b==null)
{
throw new ConfigurationException("Only two tags <packages> or <session-timeout> expected under<configuration> tag.");
}
if(b==false)
{
if(processedTags.contains(nodeText))
{
throw new ConfigurationException(nodeText+" can only occure ones under the <configuration> tag");
}
System.out.println("processedTags.add(nodeText) ;  k phle");
processedTags.add(nodeText);
System.out.println("processedTags.add(nodeText) ;  k bad  "+nodeText);
}
}
if(processedTags.contains("packages")==false)
{
throw new ConfigurationException("<packages> tag missing under <configuration> tag.");
}
}
private static void validatePackages(Node packagesNode) throws ConfigurationException
{
NodeList nodeList=packagesNode.getChildNodes();
Node node;
String nodeName;
for(int x=0;x<nodeList.getLength();x++)
{
node=nodeList.item(x);
nodeName=node.getNodeName();
if(nodeName.equals("#text")) continue;
if(nodeName.equals("package")==false)
{
throw new ConfigurationException("<packages> tag should only cotain <package> tag,found <"+nodeName+">");
}
}
}
private static Node getNode(NodeList nodeList,String name)
{
Node node=null;
for(int x=0;x<nodeList.getLength();x++)
{
node=nodeList.item(x);
if(node.getNodeName().equals(name))
{
return node;
}
}
return null;
}
public static HashSet<String> getPackages(Node packagesNode)
{
HashSet<String> packages=new HashSet<String>();
NodeList nodeList=packagesNode.getChildNodes();
Node node;
String nodeName;
for(int x=0;x<nodeList.getLength();x++)
{
node=nodeList.item(x);
nodeName=node.getNodeName();
if(nodeName.equals("#text")) continue;
System.out.println("("+node.getTextContent().replaceAll("\r","").replaceAll("\n","").trim()+")");
packages.add(node.getTextContent().replaceAll("\r","").replaceAll("\n","").trim());
}
return packages;
}
}