package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.utils.*;

class BallTest {
	
	Point p15;
	Circle c152;
	Vector v1010;
	NormalBall n1; 
	NormalBall n2;
	SuperChargedBall s1; 
	SuperChargedBall s2;
	BlockState b2;

	
	@BeforeEach
	void setUp() throws Exception {
		p15 = new Point(10,5);
		c152 = new Circle(p15,Constants.INIT_BALL_DIAMETER);
		v1010 = new Vector(1,1);
		n1 = new NormalBall(c152, v1010);
		s1 = new SuperChargedBall(c152,v1010,4);
		n2 = Setups.typicalNormalBall(0);
		s2 = Setups.typicalSuperBall(0);
		b2 = Setups.typicalBlocks()[0];
		
	}
	

	
	@Test 
	void testBall() {
		n1.setLocation(new Circle(new Point (2,2), 2));
		n1.setPosition(new Point(1,1));
		assertEquals(new Circle(new Point (1,1),2), n1.getLocation());
		assertEquals(new Point(1,1), n1.getCenter());
		n1.setVelocity(new Vector(5,5));
		assertEquals(new Vector(5,5), n1.getVelocity());
		
	}
	
	@Test
	void testHitRect() {
		assertEquals(null,n1.bounceOn(new Rect(new Point(100,100),new Point(110,110))));
		assertEquals(n2.getLocation().getCenter(),new Point(42500,28125));
		n2.setVelocity(new Vector(7,1));
		n2.move(n2.getVelocity().scaled(450), 100);
		assertEquals(b2.getLocation().getBottomRight(),new Point(50000,30000));
		assertEquals(b2.getLocation().getTopLeft(),new Point(45000,26250));
		assertEquals(b2.getLocation().collideWith(n2.getLocation()),new Vector(0,-1));
		assertFalse(n2.collidesWith(b2.getLocation()));
		assertFalse(n2.hitRect(b2.getLocation()));
		assertNotEquals(n2.getVelocity().mirrorOver(Vector.LEFT),n2.bounceOn(b2.getLocation()));
	}
	
	@Test
	void testGetColor() {
		assertFalse(n2.getVelocity().getSquareLength() > Constants.BALL_SPEED_THRESH * Constants.BALL_SPEED_THRESH);
		assertEquals(Constants.BALL_COLOR, n2.getColor());
		n2.setVelocity(new Vector(10,10));
		assertTrue(n2.getVelocity().getSquareLength() > Constants.BALL_SPEED_THRESH * Constants.BALL_SPEED_THRESH);
		assertEquals(Constants.BALL_FAST_COLOR, n2.getColor());
		
	}

}
