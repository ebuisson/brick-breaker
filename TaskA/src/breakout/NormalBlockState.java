package breakout;

import java.awt.Color;
import java.util.Arrays;

import breakout.utils.Rect;

public class NormalBlockState extends BlockState {

	private static final Color COLOR = new Color(128, 128, 128);

	/**
	 * Construct a block occupying a given rectangle in the field.
	 * | @pre | location != null
	 * | @post | getLocation().equals(location)
	 */
	public NormalBlockState(Rect location) {
		super(location);
	}

	@Override
	/**
	 * TODO
	 * @post | result == null
	 */
	public BlockState blockStateAfterHit(int squaredSpeed) {
		return null;
	}

	@Override
	/**
	 * TODO
	 * @post | result == ballState
	 */
	public Ball ballStateAfterHit(Ball ballState) {
		return ballState;
	}

	@Override
	/**
	 * TODO
	 * @post | result == paddleState
	 */
	public PaddleState paddleStateAfterHit(PaddleState paddleState) {
		return paddleState;
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

}
