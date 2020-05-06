package Engine;

import static java.lang.Math.*;

public class Geometry
{
    public static int pointDistance(int x, int y, int x1, int y1)
    {
        double a = abs(x - x1);
        double b = abs(y - y1);

        double c = sqrt(pow(a, 2) + pow(b, 2));

        return (int) c;
    }

    public static int closedAngle(int x1, int y1, int x2, int y2)
    {
        return (int) toDegrees(atan2(y1 - y2, x1 - x2)) + 90;
        //return (int) toDegrees(atan2(y1 - y2, x2 - x1));
    }

}
