package bl;
import com.thinking.machines.socket.framework.annotations.*;
import java.util.*;
@Path("/item")
public class ItemManager
{
@Path("add")
public void add(ArrayList<String> a)
{
a.add("pappu");
System.out.println("name saved");
}
@Path("getCount")
@Produces(Data.STRING)
public int getCount()
{
System.out.println("ItemManager : getCount got called");
return 20;
}
public void doSomething()
{
}
}