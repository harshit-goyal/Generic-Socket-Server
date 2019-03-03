import com.thinking.machines.socket.framework.common.*;
import com.thinking.machines.socket.framework.util.*;
import com.thinking.machines.socket.framework.factory.*;
class PoolTestCase
{
public static void main(String g[])
{
Object o1=Pools.getObject("java.lang.String");
Object o2=Pools.getObject("java.lang.StringBuffer");
Object o3=Pools.getObject("java.lang.Class");
System.out.println(o1==o2);
System.out.println(o2==o3);
System.out.println(o3==o1);

}
}