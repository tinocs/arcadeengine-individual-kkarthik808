package breakout;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class Score extends Text{
	private int value;
	public void updateDisplay() {
		setText(""+value);
	}
	public Score() {
		value=0;
		setFont(new Font(20));
		updateDisplay();
	}
	public int getScore() {
		return value;
	}
	public void setScore(int newScore) {
		value=newScore;
		updateDisplay();
	}
}
