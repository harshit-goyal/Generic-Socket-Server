import com.thinking.machines.socket.framework.client.*;
import com.thinking.machines.inventory.dl.interfaces.*;
import com.thinking.machines.inventory.dl.exception.*;
import com.thinking.machines.inventory.dl.*;
import java.util.*;
class TMClientTestCase
{
public static void main(String g[])
{
try
{
System.out.println("dss12ds");
ArrayList<ItemDTOInterface> list;
TMClient tmClient=TMClient.getInstance("localhost",5000);
String path="/item/getAll";
System.out.println("dssds");
list=(ArrayList<ItemDTOInterface>)tmClient.invoke(path);
for(ItemDTOInterface itemDTOInterface : list)
{
System.out.println(itemDTOInterface.getCode()+","+itemDTOInterface.getName()+","+itemDTOInterface.getCategory()+","+itemDTOInterface.getPrice());
}
path="/item/update";
ItemDTOInterface idi=new ItemDTO();
idi.setName("laptop");
idi.setCategory("electronic");
idi.setPrice(121212);
idi.setCode(12);
tmClient.invoke(path,idi);
}catch(Throwable e)
{
System.out.println(e);
}
}
}