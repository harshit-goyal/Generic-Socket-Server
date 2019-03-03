package bl;
import com.thinking.machines.socket.framework.annotations.*;
import java.util.*;
@Path("/Lamda")
public class LamdaExp
{
@Path("getName")
public String NamebyLamda(String nn,String name2,Name n)
{
System.out.println("aya");
return n.getName(nn,name2);
}
}