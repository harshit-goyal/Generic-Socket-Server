import com.thinking.machines.socket.framework.client.*;
class ApplicationScopeTestCase2
{
public static void main(String gg[])
{ try
{
TMClient tmClient=TMClient.getInstance("localhost",5000);
String path="/school/math/subtract";
tmClient.invoke(path,5);
System.out.println("Deduction done");
}catch(Throwable t)
{
System.out.println(t);
}
}
}