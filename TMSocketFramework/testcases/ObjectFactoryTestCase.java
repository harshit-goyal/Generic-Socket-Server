import com.thinking.machines.socket.framework.common.*;
import com.thinking.machines.socket.framework.factory.*;
class ObjectFactoryTestCase
{
public static void main(String g[])
{
Pool<String,Class> classPool=new Pool<String,Class>(new ClassFactory());
Pool<String,Object> pool;
pool=new Pool<String,Object>(new ObjectFactory(classPool));
Class c1=classPool.get("java.lang.String");
Class c2=classPool.get("java.io.File");
System.out.println(classPool.size());
System.out.println(c1==c2);
Object o1=pool.get("java.lang.String");
Object o2=pool.get("java.lang.Integer");
System.out.println(classPool.size());
System.out.println("for Object "+(o1==o2));
}
}