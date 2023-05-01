package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.utils.Point;

class BlockStateTest {
	
	private Point BR;
	private BlockState[] blocks;
	private SturdyBlockState oneblock;
	private SturdyBlockState twoblock;
	private Ball nball;
	private Ball sball;
	private Ball[] balls;
	private PaddleState paddle;
	private BreakoutState state;
	private BreakoutState stateWon;
	private BreakoutState stateDead;
	
	@BeforeEach
	void setUp() throws Exception {
		BR = new Point(Constants.WIDTH, Constants.HEIGHT);
		blocks = Setups.typicalBlocks();
		oneblock = new SturdyBlockState (blocks[1].getLocation(),1);
		twoblock = new SturdyBlockState (blocks[1].getLocation(),2);
		paddle = new NormalPaddleState(
				new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4),
				Constants.TYPICAL_PADDLE_COLORS(),Constants.TYPICAL_PADDLE_COLORS()[0]);
		nball = Setups.typicalNormalBall(0);
		sball = Setups.typicalSuperBall(4);
		balls = new Ball[] { nball};
		state = new BreakoutState(balls, blocks, BR, paddle);
		stateWon = new BreakoutState(balls,new BlockState[] {},BR,paddle);
		stateDead = new BreakoutState(new Ball[] {}, blocks, BR, paddle);
		
	}

	@Test
	void testBlockStateAfterHit() {
		assertEquals(new SturdyBlockState(blocks[1].getLocation(),3-1).getLocation(), blocks[1].blockStateAfterHit(5).getLocation());
		assertNotEquals(new SturdyBlockState(blocks[1].getLocation(),3-1).getLivesLeft(), ((SturdyBlockState)blocks[1]).getLivesLeft());
		assertEquals(oneblock, oneblock.blockStateAfterHit(0));
		assertEquals(null, oneblock.blockStateAfterHit(100));
		assertEquals(null,blocks[0].blockStateAfterHit(0));
		
	}
	
	@Test
	void testBallStateAfterHit() {
		assertEquals(nball,blocks[0].ballStateAfterHit(nball));
		assertEquals(nball,blocks[1].ballStateAfterHit(nball));
		
	}
	
	@Test
	void testPaddleStateAfterHit() {
		assertEquals(paddle,blocks[0].paddleStateAfterHit(paddle));
		assertEquals(paddle,blocks[1].paddleStateAfterHit(paddle));
		
	}
	
	@Test
	void testColor() {
		assertEquals(new Color(128, 128, 128), blocks[0].getColor());
		assertEquals(new Color(160, 82, 45), oneblock.getColor());
		assertEquals(new Color(123, 63, 0), twoblock.getColor());
		assertEquals(new Color(92, 64, 51), blocks[1].getColor());
	}

}
