package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.utils.Point;

class PaddleStateTest {


	private NormalPaddleState normalPaddle;
	private ReplicatingPaddleState replicatingPaddle;
	private NormalPaddleState normalPaddle2;
	private ReplicatingPaddleState replicatingPaddle2;
	
	@BeforeEach
	void setUp() {
		
		normalPaddle = new NormalPaddleState(
				new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4),
				Constants.TYPICAL_PADDLE_COLORS(),
				Constants.TYPICAL_PADDLE_COLORS()[0]);
		replicatingPaddle = new ReplicatingPaddleState(
				new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4),
				Constants.TYPICAL_PADDLE_COLORS(),
				Constants.TYPICAL_PADDLE_COLORS()[0], 4);
	}
	@Test
	void createPaddleState() {
		normalPaddle2 = new NormalPaddleState(
				new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4),
				Constants.TYPICAL_PADDLE_COLORS(),
				Constants.TYPICAL_PADDLE_COLORS()[0]);
		replicatingPaddle2 = new ReplicatingPaddleState(
				new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4),
				Constants.TYPICAL_PADDLE_COLORS(),
				Constants.TYPICAL_PADDLE_COLORS()[0], 4);
		
				assertEquals(normalPaddle, new NormalPaddleState(
						new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4),
						Constants.TYPICAL_PADDLE_COLORS(),
						Constants.TYPICAL_PADDLE_COLORS()[0]));

					
	}
}

