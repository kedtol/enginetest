import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Texture
{
    public TextureImage textureImage;

    public double frameSpeed = 1000f; //in ms
    public int frameIndex = 0;
    public int angle = 0;
    private boolean frameEnd = true;
    private Alarm frameAlarm = new Alarm();

    public void setSize(int size)
    {
        textureImage.setxSize(size);
        textureImage.setySize(size);
    }

    public Texture(TextureImage textureImage)
    {
        this.textureImage = textureImage;
        this.textureImage.setPivotCenter();
    }

    public Texture(ArrayList<BufferedImage> frames)
    {
        textureImage.setFrames(frames);
    }

    Area getArea()
    {
        return textureImage.area;
    }

    public void setTextureImage(TextureImage textureImage)
    {
        this.textureImage = textureImage;
    }

    public void animate(Graphics g, int x, int y)
    {
        if (frameAlarm.finished())
        {
            if (frameIndex < textureImage.getFrames().size())
            {
                frameIndex++;
            }
            else
            {
                frameIndex = 0;
            }
            frameAlarm.start();
        }

        textureImage.drawFrame(x,y,frameIndex,40,g);
    }

    public void draw(Graphics g, int x, int y, int frame)
    {
        textureImage.drawFrame(x,y,frame,angle,g);
    }
}
