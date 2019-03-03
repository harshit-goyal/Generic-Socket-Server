package bl2.bl3.bl4;
import com.thinking.machines.socket.framework.annotations.*;
@Path("/enrol")
public class enrolManager
{
@Path("add")
public void add(enrol f)
{
System.out.println("Faculty Manager : add got Called");
}
@Path("getCount")
@Produces(Data.STRING)
public int getCount()
{
System.out.println("Faculty Manager : getCount got called");
return 29;
}
public void doSomething()
{
}
}