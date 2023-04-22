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

	
	@BeforeEach
	void setUp() throws Exception {
		p15 = new Point(10,5);
		c152 = new Circle(p15,Constants.INIT_BALL_DIAMETER);
		v1010 = new Vector(1,1);
		n1 = new NormalBall(c152, v1010);
		s1 = new SuperChargedBall(c152, v1010,4);
		n2 = Setups.typicalNormalBall(0);
		s2 = Setups.typicalSuperBall(0);
		
	}
	
//	@Test
//	void exampleTest1() {
//		assertThrows( IllegalArgumentException.class,
//				() -> insertcodehere );
//      assertTrue ( 1 == 1 );
//      assertEquals( expected, actual );
//      assertNotEquals( unexpected, actual );
//      assertNotSame( unexpected, actual );
//      abreakoutState.tickDuring( 200 );
//	}
	
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
	void testBounceOn() {
		assertEquals(null,n1.bounceOn(new Rect(new Point(100,100),new Point(110,110))));
		assertEquals(n1.getVelocity().mirrorOver(Vector.DOWN),n1.bounceOn(new Rect(new Point(0,0), new Point(20,20))));
	}
	
	@Test
	void testGetColor() {
		assertEquals(Constants.BALL_COLOR, n2.getColor());
		assertEquals(Constants.BALL_FAST_COLOR, s2.getColor());
	}

}
