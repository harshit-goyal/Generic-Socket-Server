import com.thinking.machines.socket.framework.client.*;
import bl.*;
import java.util.*;
class ApplicationScopeTestCase1
{
public static void main(String gg[])
{
try
{
TMClient tmClient=TMClient.getInstance("localhost",5000);
String path="/item/add";
ArrayList<String> a=new ArrayList<String>();
a.add("deepesh");
a.add("d_dwivedi");
a.add("deep");
a.add("pesh");
tmClient.invoke(path,a);
Iterator<String> itrator=a.iterator();
while(itrator.hasNext())
{
String name=itrator.next();
System.out.println(name);
}
}catch(Throwable t)
{
t.printStackTrace();
}
}
}