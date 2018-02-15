package Grid;
public class PaddleG
{
    int x, y, side;
    public PaddleG(int X, int Y, int S)
    {
        x=X;
        y=Y;
        side=S;        
    }
    public void move(int d)
    {
       boolean[] sides=side();
       if(d==0&&!sides[0])
       x-=2;
       if(d==1&&!sides[1])
       y-=2;
       if(d==2&&!sides[2])
       x+=2;
       if(d==3&&!sides[3])
       y+=2;
    }
    public boolean[] side()
    {
        boolean[] sides=new boolean[4];
        for(int i=0; i<sides.length; i++)
        sides[i]=false;        
        if(side==0)
        {
            if(x<=0)
            sides[0]=true;
            if(y<=-GameGrid.pheight/2)
            sides[1]=true;
            if(x>=((GameGrid.W/2)-GameGrid.pheight))
            sides[2]=true;
            if(y>=GameGrid.H-GameGrid.pheight/2)
            sides[3]=true;
        }
        if(side==1)
        {
            if(x<=(GameGrid.W/2+GameGrid.pheight-GameGrid.pwidth))
            sides[0]=true;
            if(y<=-GameGrid.pheight/2)
            sides[1]=true;
            if(x>=GameGrid.W-GameGrid.pwidth)
            sides[2]=true;
            if(y>=GameGrid.H-GameGrid.pheight/2)
            sides[3]=true;
        }
        return sides;        
    }
}