import java.awt.*;
import java.util.ArrayList;

public abstract class Program
{
    static Map map = ResourceLoader.findMap("oomap");
    static ArrayList<GameObject> gameObjectList = new ArrayList<>();
    static Collision collision = new Collision(map);

    static int mouse_x;
    static int mouse_y;
    static int mouse_wheel;
    static boolean mWheel;

    static int screen_width;
    static int screen_height;



    // engine components

    HUD hud = new HUD();
    Time time = new Time();

    // --       --    --        --     --

    public Program()
    {

    }

    public void inputReceive(char key,boolean released)
    {
        for (int i = 0; i < gameObjectList.size(); i++)
        {
            gameObjectList.get(i).keyAction(key,released);
        }
    }

    public void mouseInputReceive(int button,boolean released)
    {
        for (int i = 0; i < gameObjectList.size(); i++)
        {
            gameObjectList.get(i).mouseAction(button,released);
        }
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

        hud.draw(g);
    }

    public static void instantiate(Transform _transform, Texture _texture,String _flag,Hitbox _hitbox, KeyInput _keyinput)
    {
        gameObjectList.add(0,new GameObject(_transform,_texture,_flag,null,_keyinput));
    }

    public static void destroy(GameObject gameObject)
    {
        gameObjectList.remove(gameObject);
    }

}
