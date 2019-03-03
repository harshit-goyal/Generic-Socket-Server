import java.math.*;

import com.thinking.machines.chat.dl.*;
import com.thinking.machines.dmframework.*;
import com.thinking.machines.dmframework.exceptions.*;
class DeleteAccount{
public static void main(String g[])
{
Account a=new Account();
a.setUserId(BigDecimal.valueOf(Long.parseLong(g[0])));
try
{
DataManager dm=new DataManager();
dm.begin();
dm.delete(a);
dm.end();
System.out.println(a.getUserId());
}catch(Exception e)
{
System.out.println(e);

}

}


}