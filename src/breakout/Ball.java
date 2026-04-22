package breakout;
import engine.Actor;
import javafx.scene.image.Image;

public class Ball extends Actor{
	private int dx;
	private int dy;
	public Ball() {
		dx=5;
		dy=5;
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
	}
	@Override
	public void act(long now) {
		

		move(dx,dy);
		if(getX()<0 ) {
			dx=-dx;
		}
		if(getY()<0) {
			dy=-dy;
		}
		if(getX()+getWidth()>getWorld().getWidth()) {
			dx=-dx;
		}
		if(getY()+getHeight()>getWorld().getHeight()) {
			dy=-dy;
		}
		if(getOneIntersectingObject(Paddle.class)!=null) {
			dy=-dy;
		}
	}
}
