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
	//private Ball[] balls;
	private PaddleState paddle;
	private ReplicatingPaddleState reppaddle;
	//private BreakoutState state;

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
				Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 1);
		n0 = Setups.typicalNormalBall(0);
		n4 = Setups.typicalNormalBall(4);
		s4 = Setups.typicalSuperBall(4);
		s0 = Setups.typicalSuperBall(0);
		//balls = new Ball[] {};
		//state = new BreakoutState(balls, blocks, BR, paddle);
		
	}
	
	@Test
	//A replicating paddle's colors are determined by the array of possibleColors
	void replicatingPaddleColorTest() {
		assertArrayEquals(reppaddle.getActualColors(),Constants.TYPICAL_PADDLE_COLORS());
	}
	
	@Test
	//Replicating paddle loses a count after a hit 
	void replicatingPaddleHitsTest() {
		ReplicatingPaddleState paddle = new ReplicatingPaddleState(new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4), 
				Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 4);
		assertEquals(paddle.getCount()-1, ((ReplicatingPaddleState)paddle.stateAfterHit()).getCount());
		
	}
	
	@Test
	//A replicating paddle with 1 life left becomes a normal paddle after next hit
	void replicatingPaddleLifetimeTest() {
		assertInstanceOf(NormalPaddleState.class, reppaddle.stateAfterHit());
	}
	
	@Test
	//A superball becomes a normal ball after 10s
	void superChargedBallLifetimeTest() {
		Ball[] superball = new Ball[] {s4};
		BreakoutState state1 = new BreakoutState(superball, blocks, BR, paddle);
		state1.tickDuring(10000);
		assertNotEquals(SuperChargedBall.class, state1.getBalls()[0].getClass());
	}
	
	@Test
	//A supercharged ball can has diameter reduced (min=INIT_BALL_DIAMETER) after hitting a block
	void superChargedBallDiameterMinTest() {
		s0.setLocation(new Circle(s0.getCenter(), Constants.INIT_BALL_DIAMETER+100));
		Ball[] superball = new Ball[] {s0};
		BreakoutState state1 = new BreakoutState(superball, blocks, BR, paddle);
		state1.tickDuring(500);
		assertEquals(s0.getLocation().getDiameter()-100, state1.getBalls()[0].getLocation().getDiameter());
	}
	
	@Test
	//A supercharged ball increases diameter by 100 after a paddle hit
	void superChargedBallDiameterPaddleTest() {
		s0.setPosition(new Point(25000,21000));
		s0.setVelocity(new Vector(0,50));
		Ball[] ball = new Ball[] {s0};
		BreakoutState state1 = new BreakoutState(ball, blocks, BR, paddle);
		state1.tickDuring(100);
		assertEquals(s0.getLocation().getDiameter()+100, state1.getBalls()[0].getLocation().getDiameter());
	}
	
	@Test
	//Balls spawn from spot locations
	void ballsSpawnTest() {}
	

	
	
//	@Test
//	void dummyTest() {
//		assertTrue(false);
//	}
	


	
}

