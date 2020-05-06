import java.awt.*;
import java.util.Random;

public class Plot
{
    private int zoneId = 0;      //landmark texture
    private int claimId = 0;     //claim type

    private int plotSize;
    private Area area;

    Plot(int plotSize, Area area)
    {
        Random random = new Random();
        this.plotSize = plotSize;
        this.area = area;
    }

    Area getArea()
    {
        return area;
    }

    int getClaimId()
    {
        return claimId;
    }

    void setClaimId(int id)
    {
        this.claimId = id;
    }

    void action()
    {

    }


    public int getZoneId()
    {
        return zoneId;
    }

    public void setZoneId(int id)
    {
        this.zoneId = id;
    }

    void draw(Graphics g)
    {
        Transform transform = new Transform(area.x,area.y).convertToLocal();

        //g.drawImage(ResourceLoader.getTexture("zone", zoneId).getFrames().get(0),transform.getX(),transform.getY(),null);

        if (claimId != 0)
        {
            //g.drawImage(ResourceLoader.getTexture("claim", claimId).getFrames().get(0), transform.getX(), transform.getY(), null);
        }

        area.draw(g);
    }
}
