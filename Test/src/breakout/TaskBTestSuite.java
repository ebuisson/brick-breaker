package breakout;


import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import breakout.BlockState;
import breakout.BreakoutState;
import breakout.GameMap;
import breakout.PaddleState;
import breakout.utils.*;

/**
 * Those tests should fail on the provided bad implementation, succeed on the model solution.
 */
class TaskBTestSuite {
	
	private Point BR;
	private BlockState[] blocks;
	private Ball n0;
	private Ball s0;
	private Ball n4;
	private Ball s4;
	private Ball[] balls;
	private PaddleState paddle;
	private ReplicatingPaddleState reppaddle;
	private BreakoutState state;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() {
		BR = new Point(Constants.WIDTH, Constants.HEIGHT);
		blocks = Setups.typicalBlocks();
		paddle = new NormalPaddleState(
				new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4),
				Constants.TYPICAL_PADDLE_COLORS(),Constants.TYPICAL_PADDLE_COLORS()[0]);
		reppaddle = new ReplicatingPaddleState(new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4), 
				Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 4);
		n0 = Setups.typicalNormalBall(0);
		n4 = Setups.typicalNormalBall(4);
		s4 = Setups.typicalSuperBall(4);
		s0 = Setups.typicalSuperBall(0);
		balls = new Ball[] {};
		state = new BreakoutState(balls, blocks, BR, paddle);
		
	}
	
	@Test
	void replicatingPaddleColorTest() {
		assertArrayEquals(reppaddle.getActualColors(),Constants.TYPICAL_PADDLE_COLORS());
	}
	
	@Test
	void replicatingPaddleLifetimeTest() {
		
	}
	
	
	
	// @Test
	// Supercharged ball must have a large diameter
	
	@Test
	void dummyTest() {
		assertTrue(false);
	}
	


	
}

