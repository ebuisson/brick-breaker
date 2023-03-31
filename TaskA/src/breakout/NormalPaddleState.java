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
	 */
	public int numberOfBallsAfterHit() {
		return -1;
	}

	@Override
	/**
	 * TODO
	 */
	public PaddleState stateAfterHit() {
		return null;
	}
	
	@Override
	/**
	 * TODO
	 */
	public Color[] getActualColors() {
		return null;
		
	}
	
	@Override
	/**
	 * TODO
	 */
	public PaddleState reproduce() {
		return null;	
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
