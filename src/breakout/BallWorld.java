package breakout;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
public class BallWorld extends World{
	private Score score;
	public BallWorld() {
		setPrefWidth(500);
		setPrefHeight(500);
	}
	@Override
	public void act(long now) {
		
	}
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		ball.setX(getWidth()/2-ball.getWidth()/2);
		ball.setY(getHeight()/2-ball.getHeight()/2);
		add(ball);
		Paddle p = new Paddle();
		p.setX(getWidth()/2);
		p.setY(300);
		add(p);
		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				p.setX(event.getX()-p.getWidth()/2);
			}});
		score = new Score();
		score.setX(getWidth()/8);
		score.setY(7*getHeight()/8);
		getChildren().add(score);
	}
	public Score getScore() {
		return score;
	}
		
}
