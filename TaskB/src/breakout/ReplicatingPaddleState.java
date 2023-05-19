package breakout;

import java.awt.Color;
import java.util.Arrays;

import breakout.utils.*;

public class ReplicatingPaddleState extends PaddleState {
	
	/**
	 * count = the number of balls that will be generated upon hitting this paddle + 1.
	 * @invar | count >= 1 && count <= 4
	 */
	private int count;

	@Override
	/**
	 * @post | result == getCount()
	 * @post | result >= 1 && result <= 4
	 */
	public int numberOfBallsAfterHit() {
		return count;
	}
	
	/**
	 * Returns the remaining amount of ball replications this paddle will perform + 1
	 * @post | result >= 1 && result <= 4
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * @pre | center != null
	 * @pre | possibleColors != null
	 * @pre | Arrays.stream(possibleColors).allMatch(c -> c != null)
	 * @pre | possibleColors.length == 1 || possibleColors.length == 3
	 * @pre | curColor != null
	 * @pre | Arrays.stream(possibleColors).anyMatch(c -> c.equals(curColor))
	 * @pre | count >= 1 && count <=4
	 * 
	 */
	public ReplicatingPaddleState(Point center, Color[] possibleColors, Color curColor, int count) {
		super(center,
				possibleColors,
				curColor);
		this.count = count;
	}

	/**
	 * @pre | this.getCount() >= 1
	 * @pre | this.getCenter() != null
	 * @pre | this.getPossibleColors() != null
	 * @pre | this.getCurColor() != null
	 * @post | result.getCenter() == this.getCenter()
	 * @post | this.getPossibleColors() == this.getPossibleColors()
	 * @post | this.getCurColor() == this.getCurColor()
	 * 
	 * @creates | result
	 */
	@Override
	public PaddleState stateAfterHit() {
		if (count > 2) {
			return new ReplicatingPaddleState(getCenter(), getPossibleColors(), getCurColor(), getCount()-1);
		} else {
			NormalPaddleState res = new NormalPaddleState(getCenter(), getPossibleColors(), getCurColor());
			res.tossCurColor();
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
	 * @pre | this.getCount() >= 1
	 * @pre | this.getCenter() != null
	 * @pre | this.getPossibleColors() != null
	 * @pre | this.getCurColor() != null
	 * @post | result.getCenter() == this.getCenter() 
	 * @post | ((ReplicatingPaddleState)result).getActualColors() == this.getActualColors()
	 * @post | result.getCurColor() == this.getCurColor()
	 * @post | ((ReplicatingPaddleState)result).getCount() == this.getCount()
	 * @post | result != null
	 * 
	 * @creates | result
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
