import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProgramPanel extends JPanel implements MouseMotionListener, MouseWheelListener, MouseListener
{
    private int sizeX;
    private int sizeY;
    private Program program;
    private ResourceLoader resourceLoader = new ResourceLoader();
    private InputHandler inputHandler = new InputHandler();
    private int frameCounter = 0;
    private double fps = 0;
    public ProgramPanel(int _sizeX, int _sizeY,Boolean game)
    {
        sizeX = _sizeX;
        sizeY = _sizeY;
        resourceLoader.loadTextures();
        resourceLoader.loadSounds();
        resourceLoader.loadMaps();

        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addMouseListener(this);

        this.add(inputHandler.bind);
        if (!game)
        {
            program = new Editor();
            /*program = new Game();
            program.screen_width = 100000;*/
        }
        else
        {
            program = new Game();
            Program.screen_height = sizeY;
            Program.screen_width = sizeX;
        }
        inputHandler.bindSetup(program);
    }

    void Loop() throws InterruptedException
    {
        while(true)
        {
            if (System.currentTimeMillis()%16 == 0)
            {
                repaint();
            }

            // engine loop output

            program.mainLoop();
            //Thread.sleep(1);
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {

        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g2d);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, sizeX, sizeY);
        g2d.setColor(Color.RED);
        g2d.drawString(String.valueOf(frameCounter),60,60);
        g2d.drawString(String.valueOf(fps),60,80);
        // engine draw output
        program.drawCycle(g2d);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        Program.mouse_x = e.getX();
        Program.mouse_y = e.getY();
    }

    public void mouseMoved(MouseEvent e)
    {
        Program.mouse_x = e.getX();
        Program.mouse_y = e.getY();
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        Program.mouse_wheel = e.getWheelRotation();
        Program.mWheel = true;
    }


    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        program.mouseInputReceive(e.getButton(), false);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        program.mouseInputReceive(e.getButton(), true);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
