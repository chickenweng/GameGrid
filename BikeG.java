package Grid;
import java.awt.*;
public class BikeG
{
    int X, Y, B, DX, DY, oldx=0, oldy=0, xunit, yunit;
    boolean loser;
    Color C;
    public BikeG(Color c, int xu, int yu, int b, int dx, int dy)
    {
        C=c;        
        xunit=xu;
        yunit=yu;
        X=xunit*B;
        Y=yunit*B;
        B=b;
        DX=dx;
        DY=dy;
        loser=false;
    }
    public void changeXY()
    {
        xunit+=DX; 
        X=xunit*B; 
        yunit+=DY;    
        Y=yunit*B;
        checkSide();
    }
    public void stopDXY()
    {
        DX=0;
        DY=0;
    }
    public void changeSpeed(int d)
    {
        if(d==0&&xunit-1!=oldx&&!(xunit==0&&oldx==99))
        {
            DX=-1; 
            DY=0;
        } 
        else if(d==1&&yunit-1!=oldy&&!(yunit==0&&oldy==99))
        {
            DX=0; 
            DY=-1;
        }
        else if(d==2&&xunit+1!=oldx&&!(xunit==99&&oldx==0))
        {
            DX=1; 
            DY=0;
        }
        else if(d==3&&yunit+1!=oldy&&!(yunit==99&&oldy==0))
        {
            DX=0; 
            DY=1;
        }
    }
    public void previous(int x, int y)
    {
        oldx=x;
        oldy=y;
    }
    public void checkSide()
    {
        if(xunit<0)
        {
            xunit=99;
            X=xunit*B; 
        }
        if(yunit<0)
        { 
            yunit=99;
            Y=yunit*B;
        }        
        if(xunit>99)
        {
            xunit=0;
            X=0; 
        }
        if(yunit>99)
        {
            yunit=0;
            Y=0; 
        }
        if(!GameGrid.wall[xunit][yunit])
        GameGrid.wall[xunit][yunit]=true;
        else
        {
            loser=true; 
            GameGrid.stopAll();
        } 
    }
    public void lastChange()
    {    
        xunit+=DX; 
        X=xunit*B; 
        yunit+=DY;    
        Y=yunit*B;   
        if(X<0)
        {
            X=GameGrid.S-GameGrid.B; 
            xunit=99;
        }       
        else if(X>GameGrid.S-GameGrid.B)
        {
            X=0; 
            xunit=0;
        }
        if(Y<0)
        {
            Y=GameGrid.S-GameGrid.B; 
            yunit=99;
        }         
        else if(Y>GameGrid.S-GameGrid.B)
        {
            Y=0; 
            yunit=0;
        }
        if(GameGrid.wall[xunit][yunit]) 
        loser=true;
    }
}