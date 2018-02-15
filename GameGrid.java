package Grid; 

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class GameGrid extends Applet implements MouseMotionListener, MouseListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	
    //grid stuff
    static int W, H, Ascore, Bscore;
    public static Graphics buffer;
    static Image offscreen;
    static Font large, small;
    static GridLines[] line;
    static GridSky sky;
    static boolean grid;
    static boolean[] box=new boolean[15];
    static boolean[] game=new boolean[15];
    
    
    public void init()
    {          
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        gridInit();
    }
    //paint calls other methods to draw on buffer then prints buffer
    public void paint(Graphics g)
    {
        if(grid)
        grid();
        if(game[4])
        tron();
        if(game[9])
        pong();
        
        g.drawImage(offscreen, 0, 0, this);
        repaint();
    }
    public void update(Graphics g)
    {
        paint(g);
    }  
    //initializes everything for the grid HUD
    public void gridInit()
    {                  
        W=800;
        H=(int)(W*.6);
        setSize(W, H);
        offscreen=createImage(W, H);
        buffer=offscreen.getGraphics();
        large=new Font("Ariel", Font.BOLD, (int)(W/11.7)+1);
        small=new Font("Ariel", Font.BOLD, (int)(W/40)+1);
        for(int i=0; i<game.length; i++)
        game[i]=false;
        for(int i=0; i<box.length; i++)
        box[i]=false; 
        grid=true; 
        sky=new GridSky();
        line=new GridLines[9];
        for(int i=0; i<line.length; i++)
        line[i]=new GridLines(Math.pow(28.75*i, 2)*(double)W/160000, 
        28.75*i*(double)W/80000);
        repaint();
    }
    //paints the grid
    public void grid()
    {
        buffer.clearRect(0, 0, W, H);
        //sky and ground color and animation
        sky.move();       
        //moving lines
        buffer.setColor(Color.gray);
        for(int i=0; i<line.length; i++)
        line[i].moveLine();
        //non moving lines
        buffer.drawLine(0, H/3, W, H/3);        
        buffer.drawLine(W/2, H/3, W/3, H);
        buffer.drawLine(W/2, H/3, 2*W/3, H);
        double d=H-H/3;
        for(int i=0; i<15; i++)
        {
            buffer.drawLine(W/2, H/3, 0, H/3+(int)d);
            buffer.drawLine(W/2, H/3, W, H/3+(int)d);
            d/=2;
        }        
        //buttons(boxes)
        buffer.setFont(small);
        buffer.setColor(Color.black);
        if(box[0])
        {
            buffer.fillRect(0, 0, W/4, H/4);            
            buffer.setColor(Color.white);
            buffer.drawString("Records", W/16+W/120, H/5);
            buffer.setColor(Color.black);
        }
        else
        {            
            buffer.drawString("Records", W/16+W/120, H/5);
        }
        if(box[1])
        {
            buffer.fillRect(W/4, 0, W/2, H/4);
            buffer.setColor(Color.white);
            buffer.setFont(large);
            buffer.drawString("GameGrid", W/4+W/22, H/4-H/16);   
            buffer.setFont(small);
            buffer.setColor(Color.black);
        }
        else
        {
            buffer.setFont(large);
            buffer.drawString("GameGrid", W/4+W/22, H/4-H/16);
            buffer.setFont(small);
        }
        if(box[2])
        {
            buffer.fillRect(3*W/4, 0, W/4, H/4);            
            buffer.setColor(Color.white);
            buffer.drawString("Players", 13*W/16+W/120, H/5);
            buffer.setColor(Color.black);
        }
        else
        {            
            buffer.drawString("Players", 13*W/16+W/120, H/5);
        }
        if(box[3])
        {
            buffer.fillRect(0, H/4, W/4, H/4);
        }
        if(box[4])
        {
            buffer.fillRect(W/4, H/4, W/4, H/4);            
            buffer.setColor(Color.white);
            buffer.drawString("Tron", 11*W/32, 3*H/8);
            buffer.setColor(Color.black);
        }
        else
        {            
            buffer.drawString("Tron", 11*W/32, 3*H/8);
        }
        if(box[5])
        {
            buffer.fillRect(2*W/4, H/4, W/4, H/4);
        }
        if(box[6])
        {
            buffer.fillRect(3*W/4, H/4, W/4, H/4);
        }
        if(box[7])
        {
            buffer.fillRect(0, 2*H/4, W/4, H/4);
        }
        if(box[8])
        {
            buffer.fillRect(W/4, 2*H/4, W/4, H/4);
        }
        if(box[9])        
        {
            buffer.fillRect(2*W/4, 2*H/4, W/4, H/4);            
            buffer.setColor(Color.white);
            buffer.drawString("Pong", 75*W/128, 5*H/8);
            buffer.setColor(Color.black);
        }
        else
        {            
            buffer.drawString("Pong", 75*W/128, 5*H/8);
        }
        if(box[10])
        {
            buffer.fillRect(3*W/4, 2*H/4, W/4, H/4);
        }
        if(box[11])
        {
            buffer.fillRect(0, 3*H/4, W/4, H/4);
        }
        if(box[12])
        {
            buffer.fillRect(W/4, 3*H/4, W/4, H/4);
        }
        if(box[13])
        {
            buffer.fillRect(2*W/4, 3*H/4, W/4, H/4);
        }
        if(box[14])
        {
            buffer.fillRect(3*W/4, 3*H/4, W/4, H/4);
        }
        try 
        {
            Thread.sleep(10);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }      
    }       
    //hitbox for boxes
    public void checkBox(MouseEvent e)
    {
        if(e.getX()>0&&e.getX()<W/4&&e.getY()>0&&e.getY()<H/4)           
            box[0]=true;
        else box[0]=false;
        if(e.getX()>W/4&&e.getX()<3*W/4&&e.getY()>0&&e.getY()<H/4)           
            box[1]=true;
        else box[1]=false;
        if(e.getX()>3*W/4&&e.getX()<W&&e.getY()>0&&e.getY()<H/4)           
            box[2]=true; 
        else box[2]=false;           
        if(e.getX()>0&&e.getX()<W/4&&e.getY()>H/4&&e.getY()<2*H/4)           
            box[3]=true;
        else box[3]=false;
        if(e.getX()>W/4&&e.getX()<2*W/4&&e.getY()>H/4&&e.getY()<2*H/4)           
            box[4]=true;
        else box[4]=false;
        if(e.getX()>2*W/4&&e.getX()<3*W/4&&e.getY()>H/4&&e.getY()<2*H/4)           
            box[5]=true;
        else box[5]=false;
        if(e.getX()>3*W/4&&e.getX()<W&&e.getY()>H/4&&e.getY()<2*H/4)           
            box[6]=true; 
        else box[6]=false;           
        if(e.getX()>0&&e.getX()<W/4&&e.getY()>2*H/4&&e.getY()<3*H/4)           
            box[7]=true;
        else box[7]=false;
        if(e.getX()>W/4&&e.getX()<2*W/4&&e.getY()>2*H/4&&e.getY()<3*H/4)           
            box[8]=true;
        else box[8]=false;
        if(e.getX()>2*W/4&&e.getX()<3*W/4&&e.getY()>2*H/4&&e.getY()<3*H/4)           
            box[9]=true;
        else box[9]=false;
        if(e.getX()>3*W/4&&e.getX()<W&&e.getY()>2*H/4&&e.getY()<3*H/4)           
            box[10]=true; 
        else box[10]=false;           
        if(e.getX()>0&&e.getX()<W/4&&e.getY()>3*H/4&&e.getY()<H)           
            box[11]=true;
        else box[11]=false;
        if(e.getX()>W/4&&e.getX()<2*W/4&&e.getY()>3*H/4&&e.getY()<H)           
            box[12]=true; 
        else box[12]=false;       
        if(e.getX()>2*W/4&&e.getX()<3*W/4&&e.getY()>3*H/4&&e.getY()<H)           
            box[13]=true;
        else box[13]=false;
        if(e.getX()>3*W/4&&e.getX()<W&&e.getY()>3*H/4&&e.getY()<H)           
            box[14]=true;
        else box[14]=false;
        }
    
    
    
     
     
    
    
    //tron stuff
    static int S, B;
    static BikeG blue, orange;      
    static boolean[][] wall;
    static boolean stop; 
    static Double fill;
    
    
    //tron intitializations
    public void tronInit()
    {
        W=600;
        S=W; 
        B=S/100;
        setSize(S, S);
        offscreen=createImage(S, S);
        buffer=offscreen.getGraphics();
        buffer.clearRect(0, 0, S, S);
        blue=new BikeG(Color.cyan, 99, 49, B, 1, 0);
        orange=new BikeG(Color.orange, 0, 49, B, -1, 0);
        wall=new boolean[100][100];
        stop=false;
        for(int i=0; i<wall.length; i++)
        for(int f=0; f<wall[i].length; f++)
        wall[i][f]=false;
        Font font=new Font("Ariel", Font.BOLD, S/10);
        buffer.setFont(font);
        setBackground(Color.black);
        Ascore=0;
        Bscore=0;
        fill=0.0;
        repaint();
    }
    public void tronNew()
    {
        W=600;
        S=W; 
        B=S/100;
        setSize(S, S);
        offscreen=createImage(S, S);
        buffer=offscreen.getGraphics();
        buffer.clearRect(0, 0, S, S);
        blue=new BikeG(Color.cyan, 99, 49, B, 1, 0);
        orange=new BikeG(Color.orange, 0, 49, B, -1, 0);
        wall=new boolean[100][100];
        stop=false;
        for(int i=0; i<wall.length; i++)
        for(int f=0; f<wall[i].length; f++)
        wall[i][f]=false;
        Font font=new Font("Ariel", Font.BOLD, S/10);
        buffer.setFont(font);
        setBackground(Color.black);
        fill=0.0;
        repaint();        
    }
    public void tron()
    {
        if(stop)    
        {
            if(blue.loser&&orange.loser)                   
            {                       
                crashAnimation(blue.X, blue.Y);               
                crashAnimation(orange.X, orange.Y);      
                buffer.setColor(Color.white);
                buffer.drawString("Draw", S/3+S/20, S/3); 
            }                                                
            else if(blue.loser) 
            {   
                crashAnimation(blue.X, blue.Y);       
                buffer.setColor(Color.white);            
                buffer.drawString("Orange Bike Wins", S/15, S/3);
            }
            else  
            {
                crashAnimation(orange.X, orange.Y);
                buffer.setColor(Color.white);  
                buffer.drawString("Blue Bike Wins", S/7, S/3);
            }
            buffer.drawString(Ascore+"", S/8+S/16, S/8);
            buffer.drawString(Bscore+"", 3*S/4, S/8);            
        }
        else
        {
            blue.previous(blue.xunit, blue.yunit);
            orange.previous(orange.xunit, orange.yunit);    
            blue.changeXY();
            orange.changeXY();                               
            buffer.setColor(blue.C);
            buffer.fillRect(blue.X, blue.Y, B, B);
            buffer.setColor(orange.C);           
            buffer.fillRect(orange.X, orange.Y, B, B); 
            fill+=2;
            showStatus(""+fill);
        }
        try 
        {
            Thread.sleep(25);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }             
    }
    public static void stopAll()
    {   
        blue.stopDXY();
        if(!orange.loser)          
        orange.lastChange();             
        orange.stopDXY();
        if(blue.loser&&orange.loser)
        ;
        else if(orange.loser)
        Ascore++;
        else
        Bscore++;
        stop=true;
    }
    public static void crashAnimation (int x, int y)
    {
        int size=(int)(Math.random() * 35) + 5;
        buffer.setColor(color());
        buffer.fillOval((int)(Math.random() * 60) + (x - 50), (int)(Math.random() * 60) + (y - 50), size, size);
    }
    public static Color color()
    {
        Color color;
        int type = (int)(Math.random() * 10);
        if (type <= 2)
            color = new Color(255, 69, 0); //red
        else if (type <= 4)
            color = new Color(255, 140, 0); //orange
        else if (type <= 6)
            color = new Color(255, 215, 0); //yellow
        else if (type <= 7)
            color = new Color(235, 235, 235); //very light gray
        else if (type <= 8)
            color = new Color(211, 211, 211); //light gray
        else if (type <= 9)
            color = new Color(169, 169, 169); //medium grey
        else
            color = new Color(105, 105, 105); //dark grey
        return color;
    }
    
    //pong stuff  
    static int pwidth, pheight, bsize;   
    PaddleG[] paddle;
    BallG ball;
    boolean[] leftMove, rightMove;
    
    
    //pong initializations
    public void pongInit()
    {
        W=1000;
        H=W/2;
        pwidth=W/100;
        pheight=H/10;
        bsize=W/100;
        Ascore=0;
        Bscore=0;
        setSize(W, H);
        Font font=new Font("Ariel", Font.BOLD, W/50);
        setBackground(Color.yellow);
        paddle=new PaddleG[2];
        paddle[0]=new PaddleG(0, (H/2)-(pheight/2), 0);
        paddle[1]=new PaddleG(W-pwidth, (H/2)-(pheight/2), 1); 
        ball=new BallG((W/2)-(bsize/2), (H/2)-(bsize/2));
        offscreen=createImage(W, H);
        buffer=offscreen.getGraphics();
        buffer.setFont(font);
        leftMove=new boolean[4];
        rightMove=new boolean[4];
        for(int i=0; i<4; i++)
        {
            leftMove[i]=false;
            rightMove[i]=false;
        }
        repaint();
    }
    //pong animations
    public void pong()
    {        
        buffer.clearRect(0, 0, W, H);
        buffer.setColor(Color.black);
        buffer.drawLine(W/2, 0, W/2, H/3);
        buffer.drawOval((W/2)-(H/6), H/3, H/3, H/3);
        buffer.drawLine(W/2, (2*H)/3, W/2, H);
        buffer.drawString(Bscore+"", W/4, H/10);
        buffer.drawString(Ascore+"", 3*(W/4), H/10);
        buffer.fillRect(paddle[0].x, paddle[0].y, pwidth, pheight);
        buffer.fillRect(paddle[1].x, paddle[1].y, pwidth, pheight);        
        buffer.fillRect(ball.x, ball.y, bsize, bsize);  
        if(leftMove[0])
        paddle[0].move(0);
        if(leftMove[1])
        paddle[0].move(1);
        if(leftMove[2])
        paddle[0].move(2);
        if(leftMove[3])
        paddle[0].move(3);
        if(rightMove[0])
        paddle[1].move(0);
        if(rightMove[1])
        paddle[1].move(1);
        if(rightMove[2])
        paddle[1].move(2);
        if(rightMove[3])
        paddle[1].move(3);        
        ball.move(paddle[0], paddle[1]);
        try 
        {
            Thread.sleep(7);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
    }
    
    
    
    
    
    
    
    
    //Listeners
        //MouseMotion
    public void mouseMoved(MouseEvent e)
    {
        if(grid)
        {
            checkBox(e);
        }
    }
    public void mouseDragged(MouseEvent e)
    {
        if(grid)
        {
            checkBox(e);
        }
    }    
        //Mouse
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {        
        if(grid)
        for(int i=0; i<box.length; i++)
            box[i]=false; 
    }    
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {        
        if(grid)
        {
            if(box[0]);
            if(box[1]);
            if(box[2]);
            if(box[3]);
            if(box[4])
            {
                grid=false;
                game[4]=true;
                setBackground(Color.black);
                tronInit();
            }
            if(box[5]);
            if(box[6]);
            if(box[7]);
            if(box[8]);
            if(box[9])
            {
                grid=false;
                game[9]=true;
                setBackground(Color.yellow);
                pongInit();
            }
            if(box[10]);
            if(box[11]);
            if(box[12]);
            if(box[13]);
            if(box[14]);
        }
    }
    public void mouseReleased(MouseEvent e)
    {
    }
        //Keys
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyPressed(KeyEvent e)
    {
        if(game[4])
        {
            if(!stop)
            {
                if(e.getKeyCode()==KeyEvent.VK_A)
                blue.changeSpeed(0);
                if(e.getKeyCode()==KeyEvent.VK_W)
                blue.changeSpeed(1);
                if(e.getKeyCode()==KeyEvent.VK_D)
                blue.changeSpeed(2);
                if(e.getKeyCode()==KeyEvent.VK_S)
                blue.changeSpeed(3);        
                if(e.getKeyCode()==KeyEvent.VK_LEFT)  
                orange.changeSpeed(0);
                if(e.getKeyCode()==KeyEvent.VK_UP)
                orange.changeSpeed(1);
                if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                orange.changeSpeed(2);
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                orange.changeSpeed(3);   
            }
            else 
            {
                if(e.getKeyCode()==KeyEvent.VK_SPACE)
                {
                    tronNew();  
                }
                if(e.getKeyCode()==KeyEvent.VK_N)
                {
                	tronInit();
                }
            }
        }   
        if(game[9])
            {
            if(e.getKeyCode()==KeyEvent.VK_A)
            leftMove[0]=true;
            if(e.getKeyCode()==KeyEvent.VK_W)
            leftMove[1]=true;
            if(e.getKeyCode()==KeyEvent.VK_D)
            leftMove[2]=true;
            if(e.getKeyCode()==KeyEvent.VK_S)
            leftMove[3]=true;
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
            rightMove[0]=true;
            if(e.getKeyCode()==KeyEvent.VK_UP)
            rightMove[1]=true;
            if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            rightMove[2]=true;
            if(e.getKeyCode()==KeyEvent.VK_DOWN)
            rightMove[3]=true;
            if(ball.dx==0&&e.getKeyCode()==KeyEvent.VK_SPACE)
            ball.start();               
            if(e.getKeyCode()==KeyEvent.VK_N)
            {pongInit();}
        }
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE&&!grid)
        {
            grid=true;
            for(int i=0; i<game.length; i++)
            game[i]=false;
            gridInit();
        }
    }
    public void keyReleased(KeyEvent e)
    { 
        if(game[9])
            {
            if(e.getKeyCode()==KeyEvent.VK_A)
            leftMove[0]=false;
            if(e.getKeyCode()==KeyEvent.VK_W)
            leftMove[1]=false;
            if(e.getKeyCode()==KeyEvent.VK_D)
            leftMove[2]=false;
            if(e.getKeyCode()==KeyEvent.VK_S)
            leftMove[3]=false;
            if(e.getKeyCode()==KeyEvent.VK_LEFT)
            rightMove[0]=false;
            if(e.getKeyCode()==KeyEvent.VK_UP)
            rightMove[1]=false;
            if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            rightMove[2]=false;
            if(e.getKeyCode()==KeyEvent.VK_DOWN)
            rightMove[3]=false;
        }
    }
}