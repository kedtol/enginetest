import Engine.Geometry;

public class Vector2D
{
    private int xComponent;
    private int yComponent;

    Vector2D(int _xC, int _yC)
    {
        xComponent = _xC;
        yComponent = _yC;

    }

    Vector2D(int length, int angle,boolean a)
    {
        xComponent = (int)(Math.cos(Math.toRadians(angle))*length);
        yComponent = (int)(Math.sin(Math.toRadians(angle))*length);
    }

    int getXComponent()
    {
        return xComponent;
    }

    int getYComponent()
    {
        return yComponent;
    }

    double getLength()
    {
        return Math.sqrt(xComponent * xComponent + yComponent * yComponent);
    }

    void nullVector()
    {
        xComponent = 0;
        yComponent = 0;
    }

    boolean isNullVector()
    {
        if (xComponent == 0 && yComponent == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    void adjust(double length)
    {
        double n = xComponent*xComponent+yComponent*yComponent;
        double d = Math.sqrt(length*length/n);
        xComponent = (int) (d*xComponent);
        yComponent = (int) (d*yComponent);
    }


    void add(Vector2D vector)
    {
        xComponent = xComponent+vector.getXComponent();
        yComponent = yComponent+vector.getYComponent();
    }

    Vector2D addGet(Vector2D vector)
    {
        Vector2D vector2D_ = new Vector2D(xComponent+vector.getXComponent(),yComponent+vector.getYComponent());

        return  vector2D_;
    }

    void substract(Vector2D vector)
    {
        xComponent = xComponent-vector.getXComponent();
        yComponent = yComponent-vector.getYComponent();
    }

    Vector2D substractGet(Vector2D vector)
    {
        Vector2D vector2D_ = new Vector2D(xComponent-vector.getXComponent(),yComponent-vector.getYComponent());

        return  vector2D_;
    }

    Vector2D normalGet()
    {
        Vector2D vector2D_ = new Vector2D(-yComponent,xComponent);

        return  vector2D_;
    }

    int getAngle()
    {
        return Geometry.closedAngle(0,0,xComponent,yComponent);
    }

}
