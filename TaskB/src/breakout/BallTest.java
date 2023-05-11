package breakout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import breakout.utils.*;

class BallTest {
	
	Point p15;
	Point br;
	Circle c152;
	Vector v1010;
	NormalBall n1; 
	NormalBall n2;
	NormalBall n3;
	Rect wall;
	SuperChargedBall s1; 
	SuperChargedBall s2;
	SuperChargedBall s3;
	BlockState b2;

	
	@BeforeEach
	void setUp() throws Exception {
		p15 = new Point(10,5);
		c152 = new Circle(p15,Constants.INIT_BALL_DIAMETER);
		v1010 = new Vector(10,10);
		n1 = new NormalBall(c152, v1010);
		s1 = new SuperChargedBall(c152,v1010,4);
		n2 = Setups.typicalNormalBall(0);
		n3 = Setups.typicalNormalBall(4);
		s3 = Setups.typicalSuperBall(4);
		s2 = Setups.typicalSuperBall(0);
		b2 = Setups.typicalBlocks()[0];
		br = new Point(50000,30000);
		wall = new Rect( new Point(br.getX(),0), new Point(br.getX()+1000,br.getY()));
		
	}
	

	
	@Test 
	void testBall() {
		n1.setLocation(new Circle(new Point (2,2), 2));
		n1.setPosition(new Point(1,1));
		assertEquals(new Circle(new Point (1,1),2), n1.getLocation());
		assertEquals(new Point(1,1), n1.getCenter());
		n1.setVelocity(new Vector(5,5));
		assertEquals(new Vector(5,5), n1.getVelocity());
		
		assertEquals(Constants.SUPERCHARGED_BALL_LIFETIME,s3.getLifetime());
		
	}
	
	@Test
	void testHitBlock() {
		assertEquals(null,n3.bounceOn(b2.getLocation()));
		n2.setPosition(new Point(44700, n2.getCenter().getY()));
		n2.hitBlock(b2.getLocation(), false);
		
		s2.setPosition(new Point(44700, s2.getCenter().getY()));
		s2.hitBlock(b2.getLocation(), false);
		
	}
	
	@Test
	void testHitPaddle() {
		n2.setVelocity(new Vector(0,4));
		Rect paddle = new Rect (new Point (42000,28300), new Point (43000,29000));
		Ball beforeHit1 = n2.clone();
		n2.hitPaddle(paddle,new Vector (6,0));
		assertEquals(beforeHit1.bounceOn(paddle).plus(new Vector(6,0).scaledDiv(5)), n2.getVelocity());
		
		n2.setVelocity(new Vector(20,20));
		Ball beforeHit2 = n2.clone();
		n2.hitPaddle(paddle, new Vector(6,0));
		assertEquals(beforeHit2.bounceOn(paddle), n2.getVelocity());
		
	}
	
	@Test
	void testGetColor() {
		assertFalse(n2.getVelocity().getSquareLength() > Constants.BALL_SPEED_THRESH * Constants.BALL_SPEED_THRESH);
		assertEquals(Constants.BALL_COLOR, n2.getColor());
		n2.setVelocity(new Vector(10,10));
		assertTrue(n2.getVelocity().getSquareLength() > Constants.BALL_SPEED_THRESH * Constants.BALL_SPEED_THRESH);
		assertEquals(Constants.BALL_FAST_COLOR, n2.getColor());
	}
	
	@Test
	void testHitWall() {
		n3.setPosition(new Point (49700, n3.getCenter().getY()));
		Vector originalVel = n3.getVelocity();
		n3.hitWall(wall);
		assertEquals(originalVel.mirrorOver(new Vector(-1,0)), n3.getVelocity());
		
		s3.setPosition(new Point (49700, s3.getCenter().getY()));
		Vector originalVel2 = s3.getVelocity();
		s3.hitWall(wall);
		assertEquals(originalVel2.mirrorOver(new Vector(-1,0)), s3.getVelocity());
		//assertTrue(n3.getVelocity() == null);
	}
	
	@Test
	void testBackToNormal() {
		assertEquals(n2.backToNormal(), n2);
		assertEquals(s2.backToNormal(), s2);
		assertEquals(s2.getLocation(),s2.backToNormal().getLocation());
		assertEquals(s2.getVelocity(),s2.backToNormal().getVelocity());
	}

	@Test
	void testclonevelocity() {
		assertTrue(n2.getLocation() == n2.cloneWithVelocity(v1010).getLocation());
		assertTrue(s2.getLocation() == s2.cloneWithVelocity(v1010).getLocation());
	}
	
	@Test
	void testLifetime() {
		assertTrue(s1.getLifetime() == 4);
	}
}
