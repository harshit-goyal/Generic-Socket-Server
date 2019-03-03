import com.thinking.machines.socket.framework.server.event.interfaces.*;
import com.thinking.machines.socket.framework.server.event.*;
import com.thinking.machines.socket.framework.server.*;
class TMServerTestCase implements ServerListener
{
static private TMServer tmserver;
int counter=0;
public TMServerTestCase()
{

}
public void clientConnected(ServerEvent serverEvent)
{
System.out.println("////////////////////////////////////");
System.out.println("client connected");
System.out.println(serverEvent.getDate());
System.out.println(serverEvent.getClientAddress());
System.out.println("////////////////////////////////////");
}
public void clientDisconnected(ServerEvent serverEvent)
{
System.out.println("////////////////////////////////////");
System.out.println("client disconnected");
System.out.println(serverEvent.getDate());
System.out.println(serverEvent.getClientAddress());
System.out.println("////////////////////////////////////");
counter++;
if(counter==30)
{
try
{
tmserver.stop(true);
}catch(Exception e)
{
}
}
}
public void serverStarted(ServerEvent serverEvent)
{

System.out.println("////////////////////////////////////");
System.out.println("client started");
System.out.println(serverEvent.getDate());
System.out.println("////////////////////////////////////");


}
public void serverStoped(ServerEvent serverEvent)
{

System.out.println("////////////////////////////////////");
System.out.println("client stop");
System.out.println(serverEvent.getDate());
System.out.println("////////////////////////////////////");
}
public static void main(String g[])
{
try
{
tmserver=new TMServer(5000);
System.out.println("-----------------------Deepesh");
tmserver.setServerListener(new TMServerTestCase());
tmserver.start();
System.out.println("Deepesh-----------------------");
}catch(Exception e)
{
e.printStackTrace();
}
}
}
