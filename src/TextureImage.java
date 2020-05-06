import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;

public class TextureImage
{
    private ArrayList<BufferedImage> frames = null;
    private int pivotX = 0;
    private int pivotY = 0;
    private int xSize = 3;
    private int ySize = 3;
    Area area = new Area(0,0,0,0);

    TextureImage()
    {}

    TextureImage(ArrayList<BufferedImage> frames)
    {
        this.frames = frames;
        area.w = frames.get(0).getWidth();
        area.h = frames.get(0).getHeight();
        resize();
        setPivotCenter();
    }

    void setFrames(ArrayList<BufferedImage> frames)
    {
        this.frames = frames;
    }

    ArrayList<BufferedImage> getFrames()
    {
        return frames;
    }

    void setPivotCenter()
    {
        pivotX = area.w*xSize/2;
        pivotY = area.h*ySize/2;
    }

    public void setxSize(int xSize)
    {
        this.xSize = xSize;
    }

    public void setySize(int ySize)
    {
        this.ySize = ySize;
    }

    public void resize()
    {
        for (int i = 0; i < frames.size(); i++)
        {
            Image image = frames.get(i).getScaledInstance(area.w * xSize, area.h * ySize, Image.SCALE_DEFAULT);
            BufferedImage buffered = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            buffered.getGraphics().drawImage(image, 0, 0 , null);
            frames.set(i,buffered);
        }
    }

    boolean drawFrame(int x, int y, int index, int angle, Graphics g)
    {
        if (frames != null)
        {
            BufferedImage frame = frames.get(index);
            area.w = frame.getWidth();
            area.h = frame.getHeight();
            AffineTransform at = AffineTransform.getTranslateInstance(x-pivotX,y-pivotY);
            at.rotate(Math.toRadians(angle),pivotX,pivotY);
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(frame,at,null);

            return true;
        }
        else
        {
            return false;
        }
    }

}
