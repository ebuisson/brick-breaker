package breakout;

import java.awt.Color;

import breakout.utils.Rect;
import breakout.utils.Circle;

public class PowerupBallBlockState extends NormalBlockState {

	private static final Color COLOR = new Color(215, 0, 64);

	public PowerupBallBlockState(Rect location) {
		super(location);
	}

	/**
	 * @pre | b != null
	 * @pre | b.getLocation().getCenter() != null
	 * @pre | b.getLocation().getDiameter() >= Constants.INIT_BALL_DIAMETER
	 * @pre | b.getVelocity() != null
	 * @post | result.getVelocity() == b.getVelocity()
	 * @post | result.getLocation().getDiameter() == Constants.INIT_BALL_DIAMETER + 600
	 * @post | ((SuperChargedBall)result).getLifetime() == Constants.SUPERCHARGED_BALL_LIFETIME
	 * @creates | result
	 * 
	 */
	@Override
	public Ball ballStateAfterHit(Ball b) {
		Circle superLoc = new Circle( b.getCenter(), Constants.INIT_BALL_DIAMETER + 600);
		return new SuperChargedBall(
				superLoc,
				b.getVelocity(),
				Constants.SUPERCHARGED_BALL_LIFETIME);		
	}

	/**
	 * @post | result == new Color(215, 0, 64)
	 */
	@Override
	public Color getColor() {
		return COLOR;
	}

}
