package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	private AnimationTimer myTimer;
	private boolean isRunning;
	public World getWorld() {
		return (World)getParent();
	}
	public abstract void act(long now);
	public void addedToWorld() {
		
	}
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}
	public <A extends Actor>java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		List<A> allActors = getWorld().getObjects(cls);
		List<A> intersectingActors = new ArrayList<A>();
		for(int i = 0; i < allActors.size(); i++) {
			if(allActors.get(i)!=this&& allActors.get(i).intersects(getBoundsInParent())) {
				intersectingActors.add(allActors.get(i));
			}
		}
		return intersectingActors;
	}
	public <A extends Actor>A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> list = getIntersectingObjects(cls);
		if(list.size()==0) {
			return null;
		}
		return list.get(0);
	}
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}
	public void move(double dx, double dy) {
		setX(getX()+dx);
		setY(getY()+dy);
	}
}
