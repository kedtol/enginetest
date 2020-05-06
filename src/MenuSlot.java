
import java.awt.*;

public class MenuSlot
{
    String label;
    int size = 100;
    boolean selected = false;
    float selValue = 1.1f;
    float selStored = 1.20f;
    int pos;
    Texture texture;
    boolean cancel = false;
    Color slotColor = Color.orange;

    MenuSlot(String label,int pos)
    {
        this.label = label;
        this.pos = pos;
        if (pos == 1)
        {
            cancel = true;
            slotColor = Color.RED;
        }
        texture = new Texture(ResourceLoader.getTextureImage("radialSpell",0));
    }


    void draw(Graphics g, Transform transform, double angle, int radius, int number, int _size)
    {
        size = (int) (_size*1.1/number);
        int x;
        int y;
        //texture.size = size/30;
        g.setColor(slotColor);

        if (selected)
        {
            selValue = selStored;
        }
        else
        {
            selValue = 1;
        }

        x = (int)((transform.getX()-size*selValue/2)+Math.cos(Math.toRadians(angle))*radius*selValue);
        y = (int)((transform.getY()-size*selValue/2)+Math.sin(Math.toRadians(angle))*radius*selValue);
        g.fillOval(x, y, (int)(size*selValue), (int)(size*selValue));
        g.setColor(Color.black);
        g.drawOval(x, y, (int)(size*selValue), (int)(size*selValue));

        g.setColor(Color.white);
        texture.draw(g,(int)(x+size*selValue/2),(int)(y+size*selValue/2),pos);
        //drawCenteredString(g,label,(int)(x+size*selValue/2),(int)(y+size*selValue/2));
    }



}

