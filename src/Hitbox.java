import java.awt.*;

public class Hitbox
{
    private Transform transform;
    private int width = 40;
    private int height = 40;
    public int size = 1;

    Hitbox(GameObject gameObject)
    {
        this.transform = gameObject.transform;

        if (gameObject.texture != null)
        {
           // gameObject.texture.getFrames();
            //this.width = gameObject.texture.getRealWidth();
            //this.height = gameObject.texture.getRealHeight();
        }
        else
        {
            gameObject.hitbox = null;
        }
    }

    private Transform getPosWithoutPivot()
    {
        Transform transform_ = new Transform();

        transform_.setX(transform.getX()-width*size/2);
        transform_.setY(transform.getY()-height*size/2);

        return transform_;
    }

    boolean pointInside(int _x, int _y)
    {
        int x = getPosWithoutPivot().getX();
        int y = getPosWithoutPivot().getY();

        if ((_x >= x && _x <= x+width*size) && (_y >= y && _y <= y+height*size))
        {
            return true;
        }

        return false;
    }

    boolean lineInside(int _x, int _y, int _x1, int _y1,Graphics g)
    {
        int x = getPosWithoutPivot().getX();
        int y = getPosWithoutPivot().getY();

        Vector2D aVector = new Vector2D(_x,_y).substractGet(new Vector2D(_x1,_y1));
        Vector2D nVector = aVector.normalGet();

        for (int ix = _x; ix < _x1; ix++)
        {
            int iy = (nVector.getXComponent()*_x+nVector.getYComponent()*_y-nVector.getXComponent()*ix)/nVector.getYComponent();
            /*Transform T = new Transform(ix,iy).convertToLocal();
            g.drawRect(T.getX(),T.getY(),3,3);*/

            if (pointInside(ix,iy))
            {
                return true;
            }
        }
        return false;
    }

    void draw(Graphics g, Transform _transform)
    {
        int x = _transform.getX();
        int y = _transform.getY();

        g.drawRect(x-height*size/2,y-width*size/2,width*size,height*size);
    }

    boolean gameObjectInside(GameObject gameObject)
    {
        Graphics g = null;
        if (gameObject.hitbox != null)
        {
            int widthG = gameObject.hitbox.width*gameObject.hitbox.size;
            int heightG = gameObject.hitbox.height*gameObject.hitbox.size;

            int xG = gameObject.transform.getX()-widthG/2;
            int yG = gameObject.transform.getY()-heightG/2;

            if (lineInside(xG,yG,xG+widthG,yG,g) || lineInside(xG,yG,xG,yG-heightG,g)
            || lineInside(xG,yG+heightG,xG+widthG,yG+heightG,g)
            || lineInside(xG+widthG,yG,xG+widthG,yG-heightG,g))
            {
                return true;
            }
        }

        return false;
    }

    boolean areaInside(Area area)
    {
        Graphics g = null;

        if (lineInside(area.x,area.y,area.x+area.w,area.y+area.h,g) || lineInside(area.x+ area.w,area.y,area.x,area.y+area.h,g))
        {


        }

        if (lineInside(area.x,area.y,area.x+area.w,area.y,g) || lineInside(area.x,area.y,area.x,area.y-area.h,g)
                || lineInside(area.x,area.y+area.h,area.x+area.w,area.y+area.h,g)
                || lineInside(area.x+area.w,area.y,area.x+area.w,area.y-area.h,g))
        {
            return true;
        }

        return false;
    }



}
