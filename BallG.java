package Grid;
import java.util.*;
public class BallG
{
    int x, y, dx, dy;
    boolean Moving;
    Random gen=new Random();
    public BallG(int X, int Y)
    {
        x=X;
        y=Y;
        dx=0;
        dy=0;
    }
    public void start()
    {
        int i=gen.nextInt(2);
        if(i==1)
        dx=2;
        else
        dx=2;
        dy=gen.nextInt(2)+1;
    }
    public void stop()
    {
        dx=0;
        dy=0;
        x=(GameGrid.W/2)-(GameGrid.bsize/2);
        y=(GameGrid.H/2)-(GameGrid.bsize/2);
    }
    public void move(PaddleG A, PaddleG B)
    {
        x+=dx;
        y+=dy;
        int segment=GameGrid.pheight/5;
        if(x>=A.x&&x<=A.x+GameGrid.pwidth)
        {
            if((y>=A.y&&y<(A.y+segment))||(y+GameGrid.bsize>=A.y&&y+GameGrid.bsize<(A.y+segment)))
            {
                dx=2;
                dy=-2;
            }
            if(y>=(A.y+segment)&&y<(A.y+2*segment))
            {
                dx=3;
                dy=-1;
            }
            if(y>=(A.y+2*segment)&&y<(A.y+3*segment))
            {
                dx=4;
                dy=0;
            }
            if(y>=(A.y+3*segment)&&y<(A.y+4*segment))
            {
                dx=3;
                dy=1;
            }
            if(y>=(A.y+4*segment)&&y<=(A.y+5*segment))
            {
                dx=2;
                dy=2;
            }
        } 
        if(x+GameGrid.bsize>=B.x&&x+GameGrid.bsize<=B.x+GameGrid.pwidth)
        {
            if((y>=B.y&&y<(B.y+segment))||(y+GameGrid.bsize>=B.y&&y+GameGrid.bsize<(B.y+segment)))
            {
                dx=-2;
                dy=-2;
            }
            if(y>=(B.y+segment)&&y<(B.y+2*segment))
            {
                dx=-3;
                dy=-1;
            }
            if(y>=(B.y+2*segment)&&y<(B.y+3*segment))
            {
                dx=-4;
                dy=0;
            }
            if(y>=(B.y+3*segment)&&y<(B.y+4*segment))
            {
                dx=-3;
                dy=1;
            }
            if(y>=(B.y+4*segment)&&y<(B.y+5*segment))
            {
                dx=-2;
                dy=2;
            }
        }
        if(y<=0||y>=GameGrid.H-GameGrid.bsize)
        dy*=-1;
        if(x<-10)
        {stop(); GameGrid.Ascore++;}
        if(x>GameGrid.W+10)
        {stop(); GameGrid.Bscore++;}
    }
}
