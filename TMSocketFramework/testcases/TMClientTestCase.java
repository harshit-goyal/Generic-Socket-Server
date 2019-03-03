import com.thinking.machines.socket.framework.client.*;
import bl.*;
class TMClientTestCase
{
public static void main(String g[])
{
try
{
TMClient tmClient=TMClient.getInstance("localhost",5000);
String path="/item/getCount";
int numberOfItem=(Integer)tmClient.invoke(path);
System.out.println(numberOfItem);
Item item=new Item();
path="/item/add";
tmClient.invoke(path,item);
System.out.println(item.getName());
}catch(Throwable e)
{
System.out.println(e);
}
}
}