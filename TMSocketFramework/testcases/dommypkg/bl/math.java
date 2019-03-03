package bl;
import com.thinking.machines.socket.framework.annotations.*;
@Path("/school/math")
@SessionAware
public class math
{
com.thinking.machines.socket.framework.model.Session session;
private String name=null;
private int x;
@Path("add")
@SessionAware
public void add(int n1,int n2[],com.thinking.machines.socket.framework.model.Session session)
{
n2[1]=494;
System.out.println(session.getClientID());
System.out.println("name is ----- "+name);
if(session.getAttribute(name)!=null)
{
x=(Integer)(session.getAttribute(name));
x+=(n1+n2[0]);
System.out.println("sum is "+x);
session.setAttribute("add",(n1+n2[0]));
}
if(name==null)
{
session.setAttribute("add",(n1+n2[0]));
System.out.println("sum is "+(n1+n2[0]));
name="add";
}
}
@Path("sub")
@Produces(Data.STRING)
@SessionAware
public void subtract(int num,com.thinking.machines.socket.framework.model.Session session)
{
session.setAttribute("sub",(Integer)session.getAttribute("add"));
session.removeAttribute("add");
System.out.println("sub happen  "+session.getAttribute("add"));
}
@Path("get")
@Produces(Data.STRING)
@SessionAware
public int get(com.thinking.machines.socket.framework.model.Session session)
{
System.out.println("get s :");
int i=(Integer)session.getAttribute("sub");
session.removeAttribute("sub");
System.out.println("gat : "+i);
return i;
}
}