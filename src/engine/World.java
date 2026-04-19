package engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	private AnimationTimer myTimer;
	private boolean isRunning;
	private Set<KeyCode> keysPressed;
	private boolean isWidthSet;
	private boolean isHeightSet;
	private int countDimensionCalls;
	
	public World() {
		isWidthSet = false;
		isHeightSet = false;
		keysPressed = new HashSet<KeyCode>();
		isRunning=false;
		MyWidthChangeListener w = new MyWidthChangeListener();
		widthProperty().addListener(w);
		MyHeightChangeListener h = new MyHeightChangeListener();
		heightProperty().addListener(h);
		MySceneChangeListener s = new MySceneChangeListener();
		sceneProperty().addListener(s);
		MyKeyPressedEventHandler ke = new MyKeyPressedEventHandler();
		setOnKeyPressed(ke);
		MyKeyReleasedEventHandler ker = new MyKeyReleasedEventHandler();
		setOnKeyReleased(ker);
		myTimer = new MyAnimationTimer();
		countDimensionCalls=0;;
	}
	
	public abstract void act(long now);
	public void add(Actor actor) {
		getChildren().add(actor);
		actor.addedToWorld();
	}
	public <A extends Actor>java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
		List<A> actors = getObjects(cls);
		List<A> addedActors = new ArrayList<A>();
		for(int i = 0; i < actors.size(); i++) {
			A actor = actors.get(i);
			if (actor.getBoundsInParent().contains(x, y)) {
		        addedActors.add(actor);
		    }
		}
		return addedActors;
	}
	public <A extends Actor>java.util.List<A> getObjects(java.lang.Class<A> cls) {
		List<A> list = new ArrayList<A>();
		for(int i = 0; i < getChildren().size(); i++) {
			if(cls.isInstance(getChildren().get(i))) {
				list.add((A)getChildren().get(i));
			}
		}
		return list;
	}
	public boolean isKeyPressed(javafx.scene.input.KeyCode code) {
		return keysPressed.contains(code);
		
	}
	public boolean isStopped() {
		return !isRunning;
	}
	public abstract void onDimensionsInitialized();
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}
	public void start() {
		myTimer.start();
		isRunning = true;
	}
	public void stop() {
		myTimer.stop();
		isRunning = false;
	}
	private class MyWidthChangeListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if(newValue.intValue()>0) {
				isWidthSet=true;
			}
			if(isWidthSet&&isHeightSet&&countDimensionCalls==0) {
				countDimensionCalls++;
				onDimensionsInitialized();
			}
		}
	}
	private class MyHeightChangeListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if(newValue.intValue()>0) {
				isHeightSet=true;
			}
			if(isWidthSet&&isHeightSet&&countDimensionCalls==0) {
				countDimensionCalls++;
				onDimensionsInitialized();
			}
		}
	}
	private class MySceneChangeListener implements ChangeListener<Scene> {
		@Override
		public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
			if(newValue!=null) {
				requestFocus();
			}
		}
	}
	private class MyKeyPressedEventHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent e) {
			keysPressed.add(e.getCode());
		}
	}
	private class MyKeyReleasedEventHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent e) {
			keysPressed.remove(e.getCode());
		}
	}
	private class MyAnimationTimer extends AnimationTimer {
		@Override
		public void handle(long now) {
			act(now);
			for (Actor n : getObjects(Actor.class)) {
			    if (n.getWorld() != null) {
			        n.act(now);
			    }
			}
		}
	}
}
