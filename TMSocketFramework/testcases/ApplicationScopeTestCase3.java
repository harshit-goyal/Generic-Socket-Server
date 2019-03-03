import com.thinking.machines.socket.framework.client.*;
class ApplicationScopeTestCase3
{
public static void main(String gg[])
{ try
{
TMClient tmClient=TMClient.getInstance("localhost",5000);
String path="/school/math/get";
System.out.println("Result : ");
int result=(Integer)tmClient.invoke(path);
System.out.println("Result : "+result);
}catch(Throwable t)
{
t.printStackTrace();
}
}
}
