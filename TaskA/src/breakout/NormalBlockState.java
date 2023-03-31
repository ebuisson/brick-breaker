package breakout;

import java.awt.Color;
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
	 */
	public BlockState blockStateAfterHit(int squaredSpeed) {
		return null;
	}

	@Override
	/**
	 * TODO
	 */
	public Ball ballStateAfterHit(Ball ballState) {
		return null;
	}

	@Override
	/**
	 * TODO
	 */
	public PaddleState paddleStateAfterHit(PaddleState paddleState) {
		return null;
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

}
