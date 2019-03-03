import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

class DrawingBoard extends Canvas
{
BufferedImage bf;
ArrayList<Integer> xList,yList,x2List,y2List;
private int lastClickedXLocation,lastClickedYLocation;
DrawingBoard()
{
bf=new BufferedImage(1600,900,BufferedImage.TYPE_INT_ARGB);
xList=new ArrayList<Integer>();
yList=new ArrayList<Integer>();
x2List=new ArrayList<Integer>();
y2List=new ArrayList<Integer>();
lastClickedXLocation=0;
lastClickedYLocation=0;
this.setBackground(Color.gray);
this.setForeground(Color.red);
}
public boolean mouseDown(Event e,int currentXLocation,int currentYLocation)
{
lastClickedXLocation = currentXLocation;
lastClickedYLocation = currentYLocation;
return true;
}
public void paint(Graphics g)
{
g.drawImage(bf,0,0,this);
}
public boolean mouseDrag(Event e,int currentXLocation,int currentYLocation)
{
Graphics gg=bf.getGraphics();
Graphics g=getGraphics();
//bf.setBackground(Color.gray);
gg.setColor(Color.red);
xList.add(lastClickedXLocation);
yList.add(lastClickedYLocation);
x2List.add(currentXLocation);
y2List.add(currentYLocation);
g.drawLine(lastClickedXLocation,lastClickedYLocation,currentXLocation,currentYLocation);
gg.drawLine(lastClickedXLocation,lastClickedYLocation,currentXLocation,currentYLocation);
lastClickedXLocation = currentXLocation;
lastClickedYLocation = currentYLocation;
return true;
}
}
class DrawingBoardFrame extends Frame
{
private DrawingBoard db;
DrawingBoardFrame()
{
db=new DrawingBoard();
setLayout(new BorderLayout());
add(db,BorderLayout.CENTER);
setLocation(10,10);
setSize(500,600);
setVisible(true);
}
} class example34psp
{
public static void main(String gg[])
{
DrawingBoardFrame dbf=new DrawingBoardFrame();
}
}