package bl;
public class Item implements java.io.Serializable
{
private String name;
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
}