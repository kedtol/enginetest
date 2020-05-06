import Engine.Geometry;

import java.awt.*;
import java.util.ArrayList;

public class RadialMenu
{
    ArrayList<MenuSlot> slots = new ArrayList<>();
    int size = 200;
    int maxSize = 480;
    int textSize;
    boolean start = false;
    boolean boot = false;
    Alarm myAlarm = new Alarm();
    int radius = 20;
    int maxRadius = 150;
    Color backColor = new Color(0, 0, 0, 160);;
    MenuSlot cancel = new MenuSlot("Cancel",1);

    RadialMenu()
    {
        slots.add(new MenuSlot("alpha",2));
        slots.add(new MenuSlot("bravo",3));
        slots.add(new MenuSlot("charlie",4));
        slots.add(new MenuSlot("delta",5));
        slots.add(new MenuSlot("echo",6));
        slots.add(new MenuSlot("foxtrot",7));
        slots.add(new MenuSlot("golf",8));
        slots.add(new MenuSlot("type",0));
        myAlarm.setLength(0.0000001);
        myAlarm.start();
    }

    void draw(Graphics g, Transform transform, int _angle)
    {
        if (start)
        {
            if (boot)
            {
                if (size < maxSize || radius < maxRadius)
                {
                    if (myAlarm.finished())
                    {
                        if (size < maxSize)
                        {
                            size += 10;
                        }
                        if (radius < maxRadius)
                        {
                            radius += 5;
                        }
                        myAlarm.setLength(0.0000001);
                        myAlarm.start();
                    }
                }
                else
                {
                    boot = false;
                }
            }

            int angle = 360 / slots.size();

            g.setColor(backColor);

            g.fillOval(transform.getX() - size / 2, transform.getY() - size / 2, size, size);

            cancel.draw(g, transform, 0, 0, slots.size(), size);

            for (int i = 1; i < slots.size() + 1; i++)
            {
                if (_angle < angle * i + angle / 2 && _angle > angle * i - angle / 2)
                {
                    if (Geometry.pointDistance(Program.mouse_x, Program.mouse_y, transform.getX(), transform.getY()) > 100)
                    {
                        slots.get(i - 1).selected = true;
                        drawCenteredString(g, slots.get(i - 1).label, transform.getX(), transform.getY() - size / 2 - 20, 20);
                        cancel.selected = false;
                    }
                    else
                    {
                        cancel.selected = true;
                        slots.get(i - 1).selected = false;
                    }
                }
                else
                {
                    slots.get(i - 1).selected = false;
                }
                slots.get(i - 1).draw(g, transform, 360 - angle * i, radius, slots.size(), size);
            }
        }
        else
        {
            size = 200;
            radius = 20;
        }
    }

    public void drawCenteredString(Graphics g, String text,int _x,int _y, int _size)
    {
        Font old = g.getFont();
        Font font = new Font(g.getFont().getName(), Font.PLAIN, _size);
        FontMetrics metrics = g.getFontMetrics(font);

        g.setFont(font);
        int x = _x - metrics.stringWidth(text) / 2;
        int y = _y - metrics.getHeight() / 2 + metrics.getAscent();
        g.drawString(text, x, y);
        g.setFont(old);
    }
}
