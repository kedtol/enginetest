import java.util.Random;

public class Transform
{
    private int x;
    private int y;
    private int angle;
    GameObject parent = null;

    public Transform()
    {
        Random random = new Random();
        x = random.nextInt(500);
        y = random.nextInt(500);
    }

    public Transform(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setAngle(int angle)
    {
        this.angle = angle;
    }

    public int getAngle()
    {
        return angle;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void move(Vector2D _vector)
    {
        x = x + _vector.getXComponent();
        y = y + _vector.getYComponent();
    }

    public Transform convertToLocal()
    {
        Transform _transform = new Transform(x,y);

        if (Camera.target == parent)
        {
            _transform.setX(Camera.center.getX());
            _transform.setY(Camera.center.getY());
        }
        else
        {
            _transform.move(Camera.getVector());
        }

        return _transform;
    }
}
