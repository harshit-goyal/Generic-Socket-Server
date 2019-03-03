package bl2;
import com.thinking.machines.socket.framework.annotations.*;
@Path("/school/faculty")
public class FacultyManager
{
@Path("add")
public void add(Faculty f)
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