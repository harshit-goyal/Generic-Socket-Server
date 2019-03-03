package com.thinking.machines.chat.dl;
import com.thinking.machines.dmframework.annotations.*;
import java.math.*;
@Display(value="Account")
@Table(name="ACCOUNT")
public class Account implements java.io.Serializable,Comparable<Account>
{
@Sort(priority=1)
@Display(value="user id")
@Column(name="USER_ID")
private BigDecimal userId;
@Display(value="user name")
@Column(name="USER_NAME")
private Character userName;
@Display(value="email id")
@Column(name="EMAIL_ID")
private Character emailId;
@Display(value="password")
@Column(name="PASSWORD")
private Character password;
@Display(value="possword key")
@Column(name="POSSWORD_KEY")
private Character posswordKey;
@Display(value="first name")
@Column(name="FIRST_NAME")
private Character firstName;
@Display(value="last name")
@Column(name="LAST_NAME")
private Character lastName;
@Display(value="gender")
@Column(name="GENDER")
private Character gender;
@Display(value="status")
@Column(name="STATUS")
private Character status;
public Account()
{
this.userId=null;
this.userName=null;
this.emailId=null;
this.password=null;
this.posswordKey=null;
this.firstName=null;
this.lastName=null;
this.gender=null;
this.status=null;
}
public void setUserId(BigDecimal userId)
{
this.userId=userId;
}
public BigDecimal getUserId()
{
return this.userId;
}
public void setUserName(Character userName)
{
this.userName=userName;
}
public Character getUserName()
{
return this.userName;
}
public void setEmailId(Character emailId)
{
this.emailId=emailId;
}
public Character getEmailId()
{
return this.emailId;
}
public void setPassword(Character password)
{
this.password=password;
}
public Character getPassword()
{
return this.password;
}
public void setPosswordKey(Character posswordKey)
{
this.posswordKey=posswordKey;
}
public Character getPosswordKey()
{
return this.posswordKey;
}
public void setFirstName(Character firstName)
{
this.firstName=firstName;
}
public Character getFirstName()
{
return this.firstName;
}
public void setLastName(Character lastName)
{
this.lastName=lastName;
}
public Character getLastName()
{
return this.lastName;
}
public void setGender(Character gender)
{
this.gender=gender;
}
public Character getGender()
{
return this.gender;
}
public void setStatus(Character status)
{
this.status=status;
}
public Character getStatus()
{
return this.status;
}
public boolean equals(Object object)
{
if(object==null) return false;
if(!(object instanceof Account)) return false;
Account anotherAccount=(Account)object;
if(this.userId==null && anotherAccount.userId==null) return true;
if(this.userId==null || anotherAccount.userId==null) return false;
return this.userId.equals(anotherAccount.userId);
}
public int compareTo(Account anotherAccount)
{
if(anotherAccount==null) return 1;
if(this.userId==null && anotherAccount.userId==null) return 0;
int difference;
if(this.userId==null && anotherAccount.userId!=null) return 1;
if(this.userId!=null && anotherAccount.userId==null) return -1;
difference=this.userId.compareTo(anotherAccount.userId);
return difference;
}
public int hashCode()
{
if(this.userId==null) return 0;
return this.userId.hashCode();
}
}
