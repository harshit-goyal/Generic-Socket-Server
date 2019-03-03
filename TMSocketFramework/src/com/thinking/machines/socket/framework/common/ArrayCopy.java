package com.thinking.machines.socket.framework.common;
public class ArrayCopy
{
public static void copy(Object target,Object source)
{
if(source instanceof int[])
{
int[] sourceInt=(int[])source;
int[] targetInt=(int[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;
}
if(source instanceof long[])
{
long[] sourceInt=(long[])source;
long[] targetInt=(long[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;
}
if(source instanceof short[])
{
short[] sourceInt=(short[])source;
short[] targetInt=(short[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;
}
if(source instanceof byte[])
{
byte[] sourceInt=(byte[])source;
byte[] targetInt=(byte[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;
}
if(source instanceof float[])
{
float[] sourceInt=(float[])source;
float[] targetInt=(float[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;
}
if(source instanceof double[])
{
double[] sourceInt=(double[])source;
double[] targetInt=(double[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;
}
if(source instanceof char[])
{
char[] sourceInt=(char[])source;
char[] targetInt=(char[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;

}
if(source instanceof boolean[])
{
boolean[] sourceInt=(boolean[])source;
boolean[] targetInt=(boolean[])target;
for(int x=0;x<targetInt.length;x++)
{
targetInt[x]=sourceInt[x];
}
return;
}
else
{
arraycopy(target,source);
return;
}
}
private static void arraycopy(Object target,Object source)
{
Object targetArray[]=(Object[])target;
Object sourceArray[]=(Object[])source;
for(int x=0;x<targetArray.length;x++)
{
targetArray[x]=sourceArray[x];
}
}
}