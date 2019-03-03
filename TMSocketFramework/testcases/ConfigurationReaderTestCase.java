import com.thinking.machines.socket.framework.util.*;
import java.io.*;
import java.util.*;
class ConfigurationReaderTestCase
{
public static void main(String g[])
{
File file=new File("Configuration.xml");
Configuration configuration=null;
HashSet<String> packages=null;
try
{
configuration=ConfigurationReader.getConfiguration(file);
packages=configuration.getPackages();
Iterator<String> iterator=packages.iterator();
while(iterator.hasNext())
{
System.out.println(iterator.next());
}
}catch(ConfigurationException configurationException)
{
System.out.println(configurationException);
}
}
}