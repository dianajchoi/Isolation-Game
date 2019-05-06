import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MyStopwatch extends JFrame{

	private Timer keepTime;
	public int counter;
	
	public MyStopwatch() {
		counter = 0;
		keepTime = new Timer(1000, new StopwatchListener());
		keepTime.start();
	}
	
	private class StopwatchListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			++counter;
		}
	}
	
	public static void main(String[] args) {
		new MyStopwatch();
		while(true);
	}
}
