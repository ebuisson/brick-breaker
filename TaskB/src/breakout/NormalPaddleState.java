package breakout;

import java.awt.Color;
import java.util.Arrays;

import breakout.utils.*;

public class NormalPaddleState extends PaddleState {

	public NormalPaddleState(Point center, Color[] possColors, Color curColor) {
		super(center, possColors, curColor);
	}

	@Override
	/**
	 * TODO
	 * @post | result == 1
	 */
	public int numberOfBallsAfterHit() {
		return 1;
	}

	@Override
	/**
	 * TODO
	 * @post | result == this
	 */
	public PaddleState stateAfterHit() {
		return this;
	}
	
	@Override
	/**
	 * TODO
	 * @post | result[0] == getCurColor()
	 */
	public Color[] getActualColors() {
		return new Color[] {getCurColor()};
		
	}
	
	@Override
	/**
	 * TODO
	 * @post | result.getCenter() == this.getCenter() 
	 * @post | result.getPossibleColors() == this.getPossibleColors()
	 * @post | result.getCurColor() == this.getCurColor()
	 * @post | result != null
	 */
	public PaddleState reproduce(){
		return new NormalPaddleState(getCenter(), getPossibleColors(), getCurColor());	
	}
	
	/**
	 * LEGIT
	 */
	public boolean equalContent(PaddleState other) {
		if (getClass() != other.getClass()) { return false; }
		if (getCenter() != other.getCenter()) { return false; }
		if ( ! Arrays.equals(getPossibleColors(), other.getPossibleColors())) { return false; }
		if ( ! getCurColor() .equals( other.getCurColor() )) { return false; }
		return true;
		
	}

}
