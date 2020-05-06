import java.awt.*;

public class GameObject
{
    String flag = "empty";
    Texture texture;
    Transform transform = new Transform();
    KeyInput keyInput = new KeyInput();
    Hitbox hitbox = null;
    Area area;

    public GameObject()
    {
        texture = new Texture(new TextureImage());
    }

    public GameObject(String flag)
    {
        this.flag = flag;

        //texture = new Texture(ResourceLoader.getTexture(flag));
        //texture.size = 10;
        if (texture != null)
        {
            area = new Area(this);
        }
    }

    public GameObject(Transform transform, Texture texture, String flag, Hitbox hitbox, KeyInput keyInput)
    {
        this.transform = transform;
        this.texture = texture;
        this.flag = flag;
        if (texture != null)
        {
            area = new Area(this);
        }
        this.keyInput = keyInput;
    }

    protected void keyAction(char key,boolean released)
    {
        keyInput.buttonPressed(key,released);
    }

    protected void mouseAction(int button,boolean released)
    {
        keyInput.mouseButtonPressed(button,released);
    }

    public void action()
    {
        area.sync(transform.getX(),transform.getY());
    }

    public void draw(Graphics g)
    {
        if (texture != null)
        {
            texture.draw(g,transform.convertToLocal().getX(),transform.convertToLocal().getY(),-1);
        }
    }


}
