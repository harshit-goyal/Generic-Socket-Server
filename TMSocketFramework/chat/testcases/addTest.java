import com.thinking.machines.dmframework.*;
import com.thinking.machines.dmframework.exceptions.*;
import java.math.*;
class AddTest{
public static void main(String g[])
{
Student1 a=new Student1();
a.setName(g[0].charAt(0));
a.setRoll(new BigDecimal(g[1]));
try
{
DataManager dm=new DataManager();
dm.begin();
dm.insert(a);
dm.end();
}catch(Exception e)
{
e.printStackTrace();

}

}


}