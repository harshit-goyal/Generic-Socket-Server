import com.thinking.machines.socket.framework.common.*;
import com.thinking.machines.socket.framework.factory.*;
class classFactoryTestCase
{
public static void main(String g[])
{
Pool<String,Class> pool;
pool=new Pool<String,Class>(new ClassFactory());
Class c1=pool.get("java.lang.String");
Class c2=pool.get("java.io.File");
System.out.println(c1==c2);

}
}