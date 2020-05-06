import java.awt.*;

public class Collision
{
    Map map;

    Collision(Map map)
    {
        this.map = map;
    }

    public GameObject collideTransformWFlag(String flag, Transform transform, Hitbox hitbox)
    {
        GameObject gameObject = new GameObject();
        gameObject.transform = transform;
        gameObject.hitbox = hitbox;

        for (GameObject g : Program.gameObjectList)
        {
            if (g.hitbox != null)
            {
                if (g.flag == flag || flag == null)
                {
                    if (g.hitbox.gameObjectInside(gameObject))
                    {
                        return g;
                    }
                }
            }
        }
        return null;
    }

    public Plot collidePlot(Area area)
    {
        for (int i = 0; i < map.xSize; i++)
        {
            for (int j = 0; j < map.ySize; j++)
            {
                if (area.areaInside(map.content[i][j].getArea()))
                {
                    return map.content[i][j];
                }

            }
        }

        return null;
    }

    public GameObject collideAreaFlag(String flag, Area area)
    {
        for (GameObject g : Program.gameObjectList)
        {
            if (g.area != null)
            {
                if (g.flag == flag || flag == null)
                {
                    if (area.areaInside(g.area))
                    {
                        return g;
                    }
                }
            }
        }

        return null;
    }

}
