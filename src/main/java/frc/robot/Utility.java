package frc.robot;

public class Utility 
{
    public static void safeSleep(int secs) 
    {
      try 
      {
        Thread.sleep(secs * 1000);
      } catch(InterruptedException e)
      {
        System.out.println("Thread sleep in Drivetrain.java/driveForwardCommand interrupted!");
      }
    }

    // This method needs overloads for different types.
    public static Boolean allEqual(double[] numbers, double target) 
    {
        for (double i : numbers) 
        {
            if (i != target) 
            {
                return false;
            }
        }

        return true;
    }


private Utility() {
    throw new UnsupportedOperationException("This is a utility class!");
    }
}