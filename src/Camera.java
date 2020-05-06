public class Camera //implements Drawable
{
    public static GameObject target = new GameObject();
    private static Transform transform = new Transform();
    static Transform center = new Transform();

    Camera(GameObject target)
    {
        this.target = target;
    }

    static void sync()
    {
        if (target != null)
        {
            transform.setX(target.transform.getX() - Program.screen_width / 2);
            transform.setY(target.transform.getY() - Program.screen_height / 2);

            center.setX(Program.screen_width / 2);
            center.setY(Program.screen_height / 2);
        }
    }

    static Vector2D getVector()
    {
        return new Vector2D(-transform.getX(),-transform.getY());
    }

    static void move(Vector2D vector)
    {
        transform.setX(transform.getX()+vector.getXComponent()- Program.screen_width / 2);
        transform.setY(transform.getY()+vector.getYComponent()- Program.screen_height / 2);
    }

}
