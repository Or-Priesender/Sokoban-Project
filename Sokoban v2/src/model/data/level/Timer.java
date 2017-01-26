package model.data.level;

public class Timer {
	
	private int seconds;
	private boolean stop=false;
	
	public void start()
	{
		while(true){
		seconds++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
	public int getTime()
	{
		return seconds;
	}
	
	public void reset()
	{
		seconds = 0 ;
	}

}
