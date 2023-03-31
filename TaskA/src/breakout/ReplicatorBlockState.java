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
		return null;
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

}
