import java.awt.*;

public class Game extends Program
{
    private Camera camera = new Camera(null);
    Player player = new Player();

    public Game()
    {
        camera.target = player;
        map = ResourceLoader.findMap("oomap");
        gameObjectList.add(player);

    }

    public void mainLoop()
    {
        time.tickMove();
        Camera.sync();

        for (int i = 0; i < gameObjectList.size(); i++)
        {
            gameObjectList.get(i).action();
        }
    }

    public void drawCycle(Graphics g)
    {
        g.drawString(String.valueOf(Time.fullTick),10,10);
        g.drawString(String.valueOf(Time.tick),10,20);

        map.draw(g);

        for (GameObject i : gameObjectList)
        {
            i.draw(g);
        }

        hud.draw(g);
    }



}
