public class Alarm
{
    private double startTick;
    private double length;

    public Alarm()
    {
        length = 0f;
        startTick = 0f;
    }

    public void start()
    {
        startTick = Time.tick;
    }

    public void setLength(double _length)
    {
        length = _length;
    }

    public double getState()
    {
        return Time.tick-startTick;
    }

    public boolean finished()
    {
        if (startTick+length < Time.tick)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
