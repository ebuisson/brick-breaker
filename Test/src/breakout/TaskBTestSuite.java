package breakout;


import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
	private Ball s3;
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
				Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 2);
		n0 = Setups.typicalNormalBall(0);
		n4 = Setups.typicalNormalBall(4);
		s4 = Setups.typicalSuperBall(4);
		s3 = Setups.typicalSuperBall(3);
		s0 = Setups.typicalSuperBall(0);
		//balls = new Ball[] {n0};
		//state = new BreakoutState(balls, blocks, BR, reppaddle);
		
	}
	
	@Test
	//A replicating paddle's colors are determined by the array of possibleColors
	void replicatingPaddleColorTest() {
		assertArrayEquals(reppaddle.getActualColors(),Constants.TYPICAL_PADDLE_COLORS());
	}
	
	@Test
	//CurColor is tossed when paddle goes from Replicating to Normal state
	void tossCurColorAfterStateChangeTest() {
		List<Color> result = new ArrayList<Color>();
		for (int i=0; i<25; i++) {
			Color col = reppaddle.stateAfterHit().getCurColor();
			result.add(col);
		assertFalse(result.stream().allMatch(c -> c.equals(reppaddle.getCurColor())));
		}
	}
	
	@Test
	//Replicating paddle loses a count after a hit 
	void replicatingPaddleCountAfterHitTest() {
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
		BreakoutState state = new BreakoutState(superball, blocks, BR, paddle);
		state.tickDuring(10000);
		assertNotEquals(SuperChargedBall.class, state.getBalls()[0].getClass());
	}
	
	@Test
	//A supercharged ball can has diameter reduced (min=INIT_BALL_DIAMETER) after hitting a block
	void superChargedBallDiameterMinTest() {
		s0.setLocation(new Circle(s0.getLocation().getCenter(), Constants.INIT_BALL_DIAMETER+100));
		Ball[] superball = new Ball[] {s0};
		BreakoutState state = new BreakoutState(superball, blocks, BR, paddle);
		state.tickDuring(500);
		assertEquals(s0.getLocation().getDiameter()-100, state.getBalls()[0].getLocation().getDiameter());
	}
	
	@Test
	//A supercharged ball increases diameter by 100 after a paddle hit
	void superBallDiameterAfterPaddleHitTest() {
		s0.setPosition(new Point(25000,21000));
		s0.setVelocity(new Vector(0,50));
		Ball[] ball = new Ball[] {s0};
		BreakoutState state = new BreakoutState(ball, blocks, BR, paddle);
		state.tickDuring(100);
		assertEquals(s0.getLocation().getDiameter()+100, state.getBalls()[0].getLocation().getDiameter());
	}
	
	@Test
	//Any ball that hits a powerup block gets diameter reset to init_ball_diameter+600
	void superBallDiameterAfterPowerupBlockHitTest() {
		s3.setLocation(new Circle ( s3.getLocation().getCenter() , s3.getLocation().getDiameter() - 200));
		Ball[] ball = new Ball[] {s3};
		BreakoutState state = new BreakoutState(ball, blocks, BR, paddle);
		state.tickDuring(500);
		assertTrue(state.getBalls()[0].getLocation().getDiameter() == Constants.INIT_BALL_DIAMETER + 600);
	}
	
	@Test
	//Balls spawn from replication sources
	void ballsSpawnTest() {
		n0.setPosition(new Point(25000,21000));
		n0.setVelocity(new Vector(0,50));
		Ball[] ball = new Ball[] {n0};
		BreakoutState state = new BreakoutState(ball, blocks, BR, reppaddle);
		state.tickDuring(100);
		assertTrue(state.getBalls()[0].getLocation().getCenter().getY() != state.getBalls()[1].getLocation().getCenter().getY());
		
	}
	
	
	

	
}

