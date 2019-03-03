import java.math.*;

import com.thinking.machines.chat.dl.*;
import com.thinking.machines.dmframework.*;
import com.thinking.machines.dmframework.exceptions.*;
class UpdateAccount{
public static void main(String g[])
{
Account a=new Account();
a.setUserName(g[0]);
a.setEmailId(g[1]);
a.setPassword(g[2]);
a.setPasswordKey(g[3]);
a.setFirstName(g[4]);
a.setLastName(g[5]);
a.setGender(g[6]);
a.setStatus(g[7]);
a.setUserId(BigDecimal.valueOf(Long.parseLong(g[8])));
try
{
DataManager dm=new DataManager();
dm.begin();
dm.update(a);
dm.end();
}catch(Exception e)
{
System.out.println(e);

}

}


}