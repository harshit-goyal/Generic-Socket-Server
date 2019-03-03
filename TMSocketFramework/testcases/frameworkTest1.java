import com.thinking.machines.socket.framework.client.*;
class frameworkTest1
{
public static void main(String gg[])
{
try
{
TMClient tmClient=TMClient.getInstance("localhost",5000);
String path="/bbb/sam";
tmClient.invoke(path);
System.out.println("Sum computed");
}catch(Throwable t)
{
System.out.println(t);
}
}
}