import java.io.*;
import java.net.*;
class DummyClient
{
public static void main(String data[])
{
String request="Whatever#";
String serverName="localhost";
int portNumber=5000;
try
{
Socket socket=new Socket(serverName,portNumber);
OutputStream os;
OutputStreamWriter osw;
InputStream is;
InputStreamReader isr;
StringBuffer sb;
String response;
int x;
os=socket.getOutputStream();
osw=new OutputStreamWriter(os);
osw.write(request);
osw.flush(); // request sent
System.out.println("Deepesh");
socket.close();
}catch(Exception e)
{
System.out.println(e);
}}
}