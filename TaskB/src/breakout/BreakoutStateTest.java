package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.utils.Circle;
import breakout.utils.Point;
import breakout.utils.Rect;
import breakout.utils.Vector;

class BreakoutStateTest {
	private Point BR;
	private BlockState[] blocks;
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
	void testBreakoutStateNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(null,blocks,BR,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(balls,null,BR,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(balls,blocks,null,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(balls,blocks,BR,null) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(new Ball[] {null},blocks,BR,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(balls,new BlockState[] {null},BR,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(balls,blocks,new Point(-1,-1),paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(balls,blocks,BR,new NormalPaddleState(new Point(-10,-10), 
						Constants.TYPICAL_PADDLE_COLORS(),Constants.TYPICAL_PADDLE_COLORS()[0])));
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(balls,new BlockState[] {new NormalBlockState(new Rect(new Point(-10,-10), new Point(-1,-1)))},
						BR,paddle) );
		assertThrows(IllegalArgumentException.class, 
				() -> new BreakoutState(new Ball[] {new NormalBall(new Circle(new Point(60000,60000), Constants.INIT_BALL_DIAMETER), 
						Constants.INIT_BALL_VELOCITY)},blocks,BR,paddle) );
		
	}
	
	@Test
	void testBreakoutStateNormal() {
		BreakoutState state = new BreakoutState(balls,blocks,BR,paddle);
		assertTrue(Arrays.equals(balls, state.getBalls()));
		assertTrue(Arrays.equals(blocks, state.getBlocks()));
		assertEquals(BR,state.getBottomRight());
		assertEquals(paddle, state.getPaddle());
	}
	
	@Test
	void testTickNormal() {
		state.tickDuring(21);
		assertEquals(1,state.getBalls().length);
		Ball b = state.getBalls()[0];
		assertEquals(new Vector(Setups.typicalNormalBall(0).getVelocity().getX(), Setups.typicalNormalBall(0).getVelocity().getY()) , b.getVelocity());
	}

	@Test
	void testTickBounceBlock() {
		assertEquals(1,state.getBalls().length);
		assertEquals(4,state.getBlocks().length);
		state.tickDuring(500);
		assertEquals(1,state.getBalls().length);
		assertEquals(3,state.getBlocks().length);
		assertEquals(new Vector(-Setups.typicalNormalBall(0).getVelocity().getX(), Setups.typicalNormalBall(0).getVelocity().getY()) , state.getBalls()[0].getVelocity());
	}
	
	@Test
	void testHitBlock() {
		BreakoutState state = new BreakoutState(new Ball[] {Setups.typicalNormalBall(1)},blocks,BR,paddle);
		state.tickDuring(500);
		assertEquals(blocks.length, state.getBlocks().length);
	}
	
	
	@Test
	void testMovePaddleLeft() {
		state.movePaddleLeft(1);
		assertEquals(new Point(25000-Constants.PADDLE_VEL.getX(),paddle.getCenter().getY()), state.getPaddle().getCenter());
	}
	
	@Test
	void testMovePaddleRight() {
		state.movePaddleRight(2);
		assertEquals(new Point(25000+(2*Constants.PADDLE_VEL.getX()),paddle.getCenter().getY()), state.getPaddle().getCenter());
	}
	
	@Test
	void testIsWon() {
		assertFalse(state.isWon());
		assertTrue(stateWon.isWon());
		assertFalse(stateDead.isWon());
	}

	@Test
	void testIsDead() {
		assertFalse(state.isDead());
		assertFalse(stateWon.isDead());
		assertTrue(stateDead.isDead());
	}

}