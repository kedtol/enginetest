import java.awt.*;

public class Map
{
    Plot[][] content;
    int xPadding = 10;
    int yPadding = 10;
    int plotSize = 32;
    int xSize;
    int ySize;


    Map(int xSize, int ySize)
    {
        this.xSize = xSize;
        this.ySize = ySize;

        content =  new Plot[xSize][ySize];

        for (int i = 0; i < xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                Area area = new Area(xPadding+plotSize*i,yPadding+plotSize*j,plotSize,plotSize);
                content[i][j] = new Plot(plotSize,area);
            }
        }
    }

    Map(Plot[][] plots, int xSize, int ySize)
    {
        this.xSize = xSize;
        this.ySize = ySize;
        content = plots;
    }

    void action()
    {
        for (int i = 0; i < xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                content[i][j].action();
            }
        }
    }

    void draw(Graphics g)
    {
        for (int i = 0; i < xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                content[i][j].draw(g);
            }
        }
    }

}
