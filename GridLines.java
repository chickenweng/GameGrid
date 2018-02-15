package Grid;

public class GridLines
{
    int o=GameGrid.H/3;
    double d2y=(double)(GameGrid.W)/80000, dy, y;
    public GridLines(double y, double dy)
    {
        this.y=y;
        this.dy=dy;
    }
    public void moveLine()
    {
        GameGrid.buffer.drawLine(0, (int)(o+y), GameGrid.W, (int)(o+y));
        dy+=d2y;
        y+=dy;
        if(y+o>=GameGrid.H)
        {
            y=0;
            dy=0;
        } 
        
    }
}