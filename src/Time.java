public class Time
{
	public static double tick = 0;
	public static int fullTick = 0;
	public static double speed = 1;

	public Time()
	{
		tick = System.currentTimeMillis();
		fullTick = (int) Math.floor(tick%1000);
	}

	public static void tickMove()
	{

		if ((tick+1)%1000 == 0)
		{
			// A full second passed
			fullTick += 1;
			tick += speed;
		}
		else
		{
			tick += speed;
		}
		
	}
	
	
}
