package bl;
import com.thinking.machines.socket.framework.annotations.*;
@Path("/school/student")
public class StudentManager
{
@Path("add")
public void add(Student s)
{
System.out.println("School Manager : add got call");
}
@Path("getCount")
@Produces(Data.STRING)
public int getCount()
{
System.out.println("School Manager : getCount got call");
return 29;
}
public void doSomething()
{
}
}