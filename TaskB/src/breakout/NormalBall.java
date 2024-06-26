package breakout;

import breakout.utils.*;

public class NormalBall extends Ball {

	public NormalBall(Circle location, Vector velocity) {
		super(location, velocity);
	}

	/**
	 * Update the BallState after hitting a block at a given location.
	 * 
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @post | getCenter().equals( old(getCenter() )) //prev. location
	 * @post | getVelocity().equals(old(getVelocity()).mirrorOver(rect.collideWith(old(getLocation())))) || getVelocity().equals( old( getVelocity() )) //prev no ||
	 * @mutates | this
	 */
	@Override
	public void hitBlock(Rect rect, boolean destroyed) {
		setVelocity( bounceOn (rect) );
	}

	@Override
	/**
	 * LEGIT
	 */
	public void move(Vector v, int elapsedTime) {
		setLocation ( new Circle(getLocation().getCenter().plus(v), getLocation().getDiameter()) );
	}

	@Override
	/**
	 * LEGIT
	 */
	public void hitPaddle(Rect rect, Vector paddleVel) {
		Vector nspeed = bounceOn( rect );
		Vector mbMore = nspeed .plus(paddleVel.scaledDiv(5));
		if (mbMore.getSquareLength() <= Constants.MBS2) { setVelocity( mbMore ); }
		else { setVelocity( nspeed ); };
	}
	
	/**
	 * @pre | rect != null
	 * @pre | collidesWith(rect)
	 * @post | getLocation().equals(old(getLocation()))
	 * @post | getVelocity().equals(old(getVelocity()).mirrorOver(rect.collideWith(old(getLocation()))))
	 * @mutates | this
	 */
	@Override
	public void hitWall(Rect rect) {
		setVelocity( bounceOn( rect ) );
	}

	/**
	 * @pre | v != null
	 * @post | this.getLocation() == result.getLocation()
	 * @post | result.getVelocity() == v
	 * @creates | result
	 */
	@Override
	public Ball cloneWithVelocity(Vector v) {
		return new NormalBall(getLocation(), v);
	}
	
	@Override
	/**
	 * TODO
	 * @post | result == this
	 */
	public Ball backToNormal() {
		return this;
	}

}
