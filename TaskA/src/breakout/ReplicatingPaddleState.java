package breakout;

import java.awt.Color;
import java.util.Arrays;

import breakout.utils.*;

public class ReplicatingPaddleState extends PaddleState {
	
	/**
	 * count = the number of balls that will be generated upon hitting this paddle + 1.
	 * @invar | count >= 0
	 */
	private int count;

	@Override
	/**
	 * @post | result == getCount()
	 */
	public int numberOfBallsAfterHit() {
		return count;
	}
	
	/**
	 * Returns the remaining amount of ball replications this paddle will perform + 1
	 * @post | result == numberOfBallsAfterHit()
	 * @post | result >= 0
	 */
	public int getCount() {
		return count;
	}

	public ReplicatingPaddleState(Point center, Color[] possCols, Color curColor, int count) {
		super(center,
				new Color[] { possCols[2], possCols[1], possCols[1] },
				curColor);
		this.count = count;
	}

	/**
	 * @pre | this.getCount() >= 0
	 */
	@Override
	public PaddleState stateAfterHit() {
		if (count > 2) {
			return this;
		} else {
			PaddleState res =
					new ReplicatingPaddleState(getCenter(), getPossibleColors(), getCurColor(), 1);
			return res;
		}
	}
	
	@Override
	/**
	 * TODO
	 * @post | result == getPossibleColors()
	 * 
	 */
	public Color[] getActualColors() {
		return getPossibleColors();
	}
	
	@Override
	/**
	 * TODO
	 * @post | result.getCenter() == this.getCenter() 
	 * @post | ((ReplicatingPaddleState)result).getActualColors() == this.getActualColors()
	 * @post | result.getCurColor() == this.getCurColor()
	 * @post | ((ReplicatingPaddleState)result).getCount() == this.getCount()
	 * @post | result != null
	 */
	public PaddleState reproduce() {
		return new ReplicatingPaddleState(getCenter(), getActualColors(), getCurColor(), getCount());
	}
	
	@Override
	/**
	 * LEGIT
	 */
	public boolean equalContent(PaddleState other) {
		if (getClass() != other.getClass()) { return false; }
		ReplicatingPaddleState oth = (ReplicatingPaddleState) other;
		if (getCenter() != oth.getCenter()) { return false; }
		if ( ! Arrays.equals(getPossibleColors(), oth.getPossibleColors())) { return false; }
		if ( ! getCurColor() .equals( oth.getCurColor() )) { return false; }
		if ( count != oth.getCount() ) { return false; }
		return true;
		
	}

}
