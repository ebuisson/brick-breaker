package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.utils.Point;
import breakout.utils.Rect;
import breakout.utils.Vector;


class PaddleStateTest {


	private NormalPaddleState normalPaddle;
	private ReplicatingPaddleState replicatingPaddle;
	private ReplicatingPaddleState reppaddle;
	Vector vector;
	Rect rect;
	
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
		reppaddle = new ReplicatingPaddleState(new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4), 
				Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 2);
		vector = new Vector(-100,0);
		rect = new Rect(Constants.ORIGIN, new Point(Constants.WIDTH, Constants.HEIGHT));
	}
	
	@Test
	void testPaddleState() {
		normalPaddle.setCenter(new Point(100,100));
		assertEquals(new Point(100,100), normalPaddle.getCenter());
	}
	
	@Test
	void testMove() {
		normalPaddle.move(vector, rect);
		assertEquals(rect.minusMargin(Constants.PADDLE_WIDTH / 2, 0).constrain(new Point (24900, 22500)), normalPaddle.getCenter());
	}

	@Test
	void testNumberOfBallsAfterHit() {
		assertEquals(1, normalPaddle.numberOfBallsAfterHit());
		assertEquals(4, replicatingPaddle.numberOfBallsAfterHit());
	}
	
	@Test
	void testStateAfterHit() {
		assertEquals(normalPaddle, normalPaddle.stateAfterHit());
		
	}
	
	@Test
	void testColors() {
		assertEquals(normalPaddle.getCurColor(), normalPaddle.getActualColors()[0]);
		assertEquals(replicatingPaddle.getPossibleColors(), replicatingPaddle.getActualColors());
		normalPaddle.setCurColor(normalPaddle.getPossibleColors()[1]);
		assertTrue(normalPaddle.getActualColors()[0] == normalPaddle.getCurColor());
		assertTrue(normalPaddle.getActualColors().length == 1);
		
	}
	
	@Test
	void testReproduce() {
		assertEquals(normalPaddle.getCenter(), normalPaddle.reproduce().getCenter());
		assertEquals(normalPaddle.getPossibleColors(), normalPaddle.reproduce().getPossibleColors());
		assertEquals(normalPaddle.getCurColor(), normalPaddle.reproduce().getCurColor());
		assertEquals(replicatingPaddle.getCenter(), replicatingPaddle.reproduce().getCenter());
		assertEquals(replicatingPaddle.getActualColors(), replicatingPaddle.reproduce().getActualColors());
		assertEquals(replicatingPaddle.getCurColor(), replicatingPaddle.reproduce().getCurColor());
		assertEquals(replicatingPaddle.getCount(), ((ReplicatingPaddleState)replicatingPaddle.reproduce()).getCount());
		
	}
	
	@Test
	void replicatingPaddleHitsTest() {
		assertInstanceOf(NormalPaddleState.class, reppaddle.stateAfterHit());
		ReplicatingPaddleState paddle = new ReplicatingPaddleState(new Point( Constants.WIDTH / 2, (3 * Constants.HEIGHT) / 4), 
				Constants.TYPICAL_PADDLE_COLORS(), Constants.TYPICAL_PADDLE_COLORS()[1], 4);
		assertEquals(paddle.getCount()-1, ((ReplicatingPaddleState)paddle.stateAfterHit()).getCount());
	}
	
}

