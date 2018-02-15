package Grid;
import java.awt.*;
public class GridSky
{
    int w=GameGrid.W;
    int h=GameGrid.H;
    int centerX=w/2;
    int centerY=h/2;
    int radius=centerY*7/8;
    int sunX=centerX-radius;
    int sunY=centerY;
    int moonX=centerX+radius;
    int moonY=centerY;
    int change=1;
    int cchange=1;
    double color=70;
    static Color sky, ground;
    public GridSky()
    {
    }
    public void move()
    {
        sunX+=change;
        moonX-=change;
        int yDis=(int)(centerY+Math.sqrt(Math.pow(radius, 2)-Math.pow(sunX-centerX, 2)))-centerY;
        sunY=centerY+change*yDis;
        moonY=centerY+change*-1*yDis;
        if(Math.abs(sunX-centerX)>=radius)
        change*=-1;
        if(Math.abs(sunY-centerY)>=radius)
        cchange*=-1;        
        color+=((double)cchange/4);
        sky=new Color((int)(color)/2, 0, (int)(220-color));
        GameGrid.buffer.setColor(sky);
        GameGrid.buffer.fillRect(0, 0, w, h);        
        ground=new Color((int)color/4, 250-(int)color, 30);  
        GameGrid.buffer.setColor(Color.yellow);
        GameGrid.buffer.fillOval(sunX-50, sunY-50, w/8, w/8);
        GameGrid.buffer.setColor(Color.white);
        GameGrid.buffer.fillOval(moonX-50, moonY-50, w/8, w/8);
        GameGrid.buffer.setColor(sky);
        GameGrid.buffer.fillOval(moonX-75, moonY-75, w/8, w/8);
        GameGrid.buffer.setColor(ground);
        GameGrid.buffer.fillRect(0, h/3, w, 2*h*3);      
    }
}