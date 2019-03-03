package com.thinking.machines.socket.framework.client;
class Pair<T1,T2>
{
private T1 frist;
private T2 two;
public Pair(T1 frist,T2 two)
{
this.frist=frist;
this.two=two;
}
public void setFrist(T1 frist)
{
this.frist=frist;
}
public void setTwo(T2 two)
{
this.two=two;
}
public T1 getFrist()
{
return this.frist;
}
public T2 getTwo()
{
return this.two;
}
}