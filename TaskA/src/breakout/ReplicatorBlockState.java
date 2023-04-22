package breakout;

import java.awt.Color;

import breakout.utils.Rect;

public class ReplicatorBlockState extends NormalBlockState {

	private static final Color COLOR = new Color(100, 149, 237);

	public ReplicatorBlockState(Rect location) {
		super(location);
	}

	@Override
	/**
	 * TODO
	 */
	public PaddleState paddleStateAfterHit(PaddleState paddleState) {
		return new ReplicatingPaddleState(paddleState.getCenter(), Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 4);
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

}
