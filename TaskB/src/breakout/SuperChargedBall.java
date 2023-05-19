package breakout;

//import java.util.Arrays;

import breakout.utils.*;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @invar | getLifetime() >= 0
 * @invar | getLifetime() <= Constants.SUPERCHARGED_BALL_LIFETIME
 */
public class SuperChargedBall extends NormalBall {

	/**
	 * @invar | lifetime >= 0
	 * @invar | lifetime <= Constants.SUPERCHARGED_BALL_LIFETIME
	 */
	private int lifetime;

	/**
	 * Construct a new ball at a given `location`, with a given `velocity`.
 	 * @throws IllegalArgumentException | location == null
 	 * @throws IllegalArgumentException | velocity == null
 	 * @throws IllegalArgumentException | !Constants.ORIGIN.isUpAndLeftFrom(location.getCenter())
	 * @throws IllegalArgumentException | !location.getCenter().isUpAndLeftFrom(new Point(Constants.WIDTH, Constants.HEIGHT))
	 * @throws IllegalArgumentException | location.getDiameter() < Constants.INIT_BALL_DIAMETER
	 * @throws IllegalArgumentException | lifetime < 0
	 * @throws IllegalArgumentException | lifetime > Constants.SUPERCHARGED_BALL_LIFETIME
 	 * @post | getLocation().equals(location)
 	 * @post | getVelocity().equals(velocity)

 	 * 
	 */
	public SuperChargedBall(Circle location, Vector velocity, int lifetime) {
		super(location, velocity);
		if (location == null) throw new IllegalArgumentException();
		if (velocity == null) throw new IllegalArgumentException();
		if (!Constants.ORIGIN.isUpAndLeftFrom(location.getCenter())) throw new IllegalArgumentException();
		if (!location.getCenter().isUpAndLeftFrom(new Point(Constants.WIDTH, Constants.HEIGHT))) throw new IllegalArgumentException();
		if (location.getDiameter() < Constants.INIT_BALL_DIAMETER) throw new IllegalArgumentException();
		if (lifetime < 0) throw new IllegalArgumentException();
		if (lifetime > Constants.SUPERCHARGED_BALL_LIFETIME) throw new IllegalArgumentException();
		this.lifetime = lifetime;
	}

	/**
	 * Update the BallState after hitting a block at a given location.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @post | getCenter().equals( old(getCenter() ))
	 * @post | (getLifetime() < 0 || !destroyed) || getVelocity().equals(old(getVelocity()))
	 * @post | getLocation().getDiameter() >= Constants.INIT_BALL_DIAMETER 
	 * @mutates | this
	 */
	@Override
	public void hitBlock(Rect rect, boolean destroyed) {
		if(lifetime < 0 || !destroyed) { //bounces if normal ball again, or sturdy block
			super.hitBlock(rect, destroyed);
		}
		if (getLocation().getDiameter() >= Constants.INIT_BALL_DIAMETER + 100) {
			setLocation( new Circle ( getCenter() , getLocation().getDiameter() - 100));
		}
	}


	/**
	 * @pre | loc != null
	 * @pre | paddleVel != null
	 * @post | getLocation().getDiameter() == old ( getLocation().getDiameter()) + 100 
	 * @post | getLocation().getCenter() == old(getLocation().getCenter())
	 * @mutates | this
	 */
	@Override
	public void hitPaddle(Rect loc, Vector paddleVel) {
		super.hitPaddle(loc, paddleVel);
		setLocation( new Circle ( getCenter() , getLocation().getDiameter() + 100));
	}

	@Override
	public void hitWall(Rect rect) {
		super.hitWall(rect);
	}

	/**
	 * @post | result >= 0
	 * @post | result <= Constants.SUPERCHARGED_BALL_LIFETIME
	 * @inspects | this
	 */
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
	 /**
	 * @inspects | this
	 * @creates | result
	 * @pre | v != null
	 * @post | ((SuperChargedBall)result).getLifetime() >= 0
	 * @post | ((SuperChargedBall)result).getLifetime() <= Constants.SUPERCHARGED_BALL_LIFETIME
	 * @post | result.getLocation().equals(getLocation())
	 * @post | result.getVelocity().equals(v)
	 * @post | result.getClass() == this.getClass()
	 */
	@Override
	public Ball cloneWithVelocity(Vector v) {
		return new SuperChargedBall(getLocation(), v, getLifetime());
	}
	
	@Override
	/**
	 * TODO
	 * @post | result.getLocation().getDiameter() == Constants.INIT_BALL_DIAMETER || 
	 * 		| result.getLocation().getDiameter() == getLocation().getDiameter()
	 * @post | result.getVelocity() == getVelocity()
	 * @post | getLifetime() <= 0 ? result.getClass() != this.getClass() : result == this
	 */
	public Ball backToNormal() {
		if (lifetime <= 0) {
			return new NormalBall(new Circle(getCenter(), Constants.INIT_BALL_DIAMETER), getVelocity());
		}
		else {
			return this.clone();
		}
	}

}
