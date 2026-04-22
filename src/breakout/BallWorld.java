package breakout;
import engine.World;
public class BallWorld extends World{
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
	}
}
