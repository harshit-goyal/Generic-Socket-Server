import com.thinking.machines.chat.dl.*;
import com.thinking.machines.dmframework.*;
import com.thinking.machines.dmframework.exceptions.*;
class AddAccount{
public static void main(String g[])
{
Account a=new Account();
a.setUserName(g[0].charAt(0));
a.setEmailId(g[1].charAt(0));
a.setPassword(g[2].charAt(0));
a.setPosswordKey(g[3].charAt(0));
a.setFirstName(g[4].charAt(0));
a.setLastName(g[5].charAt(0));
a.setGender(g[6].charAt(0));
a.setStatus(g[7].charAt(0));
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