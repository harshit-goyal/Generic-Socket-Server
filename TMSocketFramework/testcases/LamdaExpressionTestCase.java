import bl.*;
import com.thinking.machines.socket.framework.client.*;
public class LamdaExpressionTestCase
{
public static void main(String g[])
{
try
{
Name nameAndHello=(s1,s2)->{s1=s1+" Hello  "+s2;
System.out.println(s1);
return s1;};
Name nameAndGood=(s1,s2)->{s1=s1+" Good  "+s2;
System.out.println(s1);
return s1;
};
String path="/Lamda/getName";
TMClient tmClient=TMClient.getInstance("localhost",5000);
String s=(String)tmClient.invoke(path,"deepesh","deepesh",nameAndHello);
String ss=(String)tmClient.invoke(path,"deepesh","deepesh",nameAndGood);
System.out.println(s);
}catch(Throwable e)
{
System.out.println(e);
}
}
}