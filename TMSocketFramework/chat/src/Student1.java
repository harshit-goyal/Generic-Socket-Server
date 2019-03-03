import com.thinking.machines.dmframework.annotations.*;
import java.math.*;
@Display(value="Student 1")
@Table(name="STUDENT1")
public class Student1 implements java.io.Serializable,Comparable<Student1>
{
@Sort(priority=1)
@Display(value="roll")
@Column(name="ROLL")
private BigDecimal roll;
@Display(value="name")
@Column(name="NAME")
private Character name;
public Student1()
{
this.roll=null;
this.name=null;
}
public void setRoll(BigDecimal roll)
{
this.roll=roll;
}
public BigDecimal getRoll()
{
return this.roll;
}
public void setName(Character name)
{
this.name=name;
}
public Character getName()
{
return this.name;
}
public boolean equals(Object object)
{
if(object==null) return false;
if(!(object instanceof Student1)) return false;
Student1 anotherStudent1=(Student1)object;
if(this.roll==null && anotherStudent1.roll==null) return true;
if(this.roll==null || anotherStudent1.roll==null) return false;
return this.roll.equals(anotherStudent1.roll);
}
public int compareTo(Student1 anotherStudent1)
{
if(anotherStudent1==null) return 1;
if(this.roll==null && anotherStudent1.roll==null) return 0;
int difference;
if(this.roll==null && anotherStudent1.roll!=null) return 1;
if(this.roll!=null && anotherStudent1.roll==null) return -1;
difference=this.roll.compareTo(anotherStudent1.roll);
return difference;
}
public int hashCode()
{
if(this.roll==null) return 0;
return this.roll.hashCode();
}
}
