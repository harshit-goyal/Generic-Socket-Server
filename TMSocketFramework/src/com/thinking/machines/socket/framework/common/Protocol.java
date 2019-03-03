package com.thinking.machines.socket.framework.common;
public class Protocol
{
private Protocol()
{
}
public static byte acknowledgement[]={1,1,1,1,1};
static public class Request
{
public static final int HEADER_LENGTH=10;
public static final int CONTENT_SEGMENT_SIZE=1024;
public static int getContentLength(byte [] header)
{
int index=HEADER_LENGTH-1;
int contentLength=0;
int placeValue=1;
while(index>=0)
{
contentLength=contentLength+(header[index]*placeValue);
placeValue*=10;
index--;
}
return contentLength;
}
public static byte [] createHeader(int contentLength)
{
byte header[]=new byte[HEADER_LENGTH];
int number=contentLength;
int index=HEADER_LENGTH-1;
while(index>=0)
{
header[index]=(byte)(number%10);
number/=10;
index--;
}
return header;
}
}
static public class Response
{
public static final int HEADER_LENGTH=10;
public static final int CONTENT_SEGMENT_SIZE=1024;
public static int getContentLength(byte [] header)
{
int index=HEADER_LENGTH-1;
int contentLength=0;
int placeValue=1;
while(index>=0)
{
contentLength=contentLength+(header[index]*placeValue);
placeValue*=10;
index--;
}
return contentLength;
}
public static byte [] createHeader(int contentLength)
{
byte header[]=new byte[HEADER_LENGTH];
int number=contentLength;
int index=HEADER_LENGTH-1;
while(index>=0)
{
header[index]=(byte)(number%10);
number/=10;
index--;
}
return header;
}
}
}