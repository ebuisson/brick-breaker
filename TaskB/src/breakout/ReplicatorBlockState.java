package breakout;

import java.awt.Color;
import java.util.Arrays;

import breakout.utils.Rect;

public class ReplicatorBlockState extends NormalBlockState {

	private static final Color COLOR = new Color(100, 149, 237);

	public ReplicatorBlockState(Rect location) {
		super(location);
	}

	@Override
	/**
	 * TODO
	 * @pre | paddleState != null
	 * @pre | paddleState.getCenter() != null
	 * @pre | paddleState.getActualColors() != null
	 * @pre | Arrays.stream(paddleState.getActualColors()).allMatch(c -> c != null)
	 * @pre | paddleState.getActualColors().length == 1 || paddleState.getActualColors().length == 3
	 * @pre | paddleState.getCurColor() != null
	 * @post | result.getCenter() == paddleState.getCenter()
	 * @post | result.getCurColor() == Constants.TYPICAL_PADDLE_COLORS()[1]
	 * @post | ((ReplicatingPaddleState)result).getCount() == 4
	 * @creates | result
	 */
	public PaddleState paddleStateAfterHit(PaddleState paddleState) {
		return new ReplicatingPaddleState(paddleState.getCenter(), Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 4);
	}

	/**
	 * @post | result.equals(new Color(100, 149, 237))
	 */
	@Override
	public Color getColor() {
		return COLOR;
	}

}
