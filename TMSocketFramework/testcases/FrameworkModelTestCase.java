import com.thinking.machines.socket.framework.model.*;
import com.thinking.machines.socket.framework.util.*;
class FrameworkModelTestCase
{
public static void main(String g[])
{
try
{
FrameworkModel frameworkModel=new FrameworkModel();
System.out.println("Size of path : "+frameworkModel.getPaths().size());
}catch(ConfigurationException configurationException)
{
System.out.println(configurationException);
}
}


}