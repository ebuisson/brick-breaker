package breakout;

//import java.util.Arrays;

import breakout.utils.*;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @invar | getLifetime() >= 0
 */

public class SuperChargedBall extends NormalBall {


	private int lifetime;

	/**
	 * Construct a new ball at a given `location`, with a given `velocity`.
 	 * @throws IllegalArgumentException | lifetime < 0
	 */
	public SuperChargedBall(Circle location, Vector velocity, int lifetime) {
		super(location, velocity);
		if (lifetime < 0) throw new IllegalArgumentException();
		this.lifetime = lifetime;
	}

	/**
	 * Update the BallState after hitting a block at a given location.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @post | getCenter().equals( old(getCenter() ))
	 * @post | (getLifetime() < 0 || !destroyed) || getVelocity().equals(old(getVelocity()))
	 * @mutates this
	 */
	@Override
	public void hitBlock(Rect rect, boolean destroyed) {
		if(lifetime < 0 || !destroyed) { //bounces if normal ball again, or sturdy block
			super.hitBlock(rect, destroyed);
		}
		if (getLocation().getDiameter() >= Constants.INIT_BALL_DIAMETER + 300) {
			setLocation( new Circle ( getCenter() , getLocation().getDiameter() - 100));
		}
	}

	@Override
	/**
	 * @post | getLocation().getDiameter() > old ( getLocation().getDiameter() )
	 */
	public void hitPaddle(Rect loc, Vector paddleVel) {
		super.hitPaddle(loc, paddleVel);
	}

	@Override
	public void hitWall(Rect rect) {
		super.hitWall(rect);
	}

	public int getLifetime() {
		return lifetime;
	}
	
	@Override
	/**
	 * LEGIT
	 */
	public void move(Vector v, int elapsedTime) {
		if(lifetime >= 0) {
			lifetime -= elapsedTime;
		}
		setLocation( new Circle(getLocation().getCenter().plus(v), getLocation().getDiameter()) );
	}

	@Override
	public Ball cloneWithVelocity(Vector v) {
		return new SuperChargedBall(getLocation(), v, lifetime);
	}
	
	@Override
	/**
	 * TODO
	 * @post | result.getLocation() == getLocation()
	 * @post | result.getVelocity() == getVelocity()
	 */
	public Ball backToNormal() {
		return new NormalBall(getLocation(), getVelocity());
	}

}
