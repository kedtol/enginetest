import java.awt.*;

public class Area
{
    int x;
    int y;
    int w;
    int h;

    Area(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    Area(GameObject gameObject)
    {
        x = gameObject.transform.getX();
        y = gameObject.transform.getY();

        if (gameObject.texture != null)
        {
            this.w = gameObject.texture.getArea().w;
            this.h = gameObject.texture.getArea().h;
        }
        else
        {
            gameObject.hitbox = null;
        }
    }

    boolean areaInside(Area area)
    {
        Graphics g = null;

        if (lineInside(area.x,area.y,area.x+area.w,area.y+area.h,g) || lineInside(area.x+ area.w,area.y,area.x,area.y+area.h,g) || area.lineInside(x,y,x+w,y+h,g) || area.lineInside(x+ w,y,x,y+h,g))
        {
            return true;
        }

        return false;
    }

    boolean lineInside(int _x, int _y, int _x1, int _y1,Graphics g)
    {

        Vector2D aVector = new Vector2D(_x,_y).substractGet(new Vector2D(_x1,_y1));
        Vector2D nVector = aVector.normalGet();

        for (int ix = _x; ix < _x1; ix++)
        {
            int iy = (nVector.getXComponent()*_x+nVector.getYComponent()*_y-nVector.getXComponent()*ix)/nVector.getYComponent();

            if (pointInside(ix,iy))
            {
                return true;
            }
        }
        return false;
    }

    boolean pointInside(int _x, int _y)
    {
        if ((_x >= x && _x <= x+w) && (_y >= y && _y <= y+h))
        {
            return true;
        }

        return false;
    }

    void draw(Graphics g)
    {
        Transform _transform = new Transform(x, y);
        _transform.move(Camera.getVector());
        g.drawRect(_transform.getX(),_transform.getY(),w,h);
    }


    void sync(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
