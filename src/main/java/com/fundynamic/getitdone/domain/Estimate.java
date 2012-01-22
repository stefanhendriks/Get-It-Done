package com.fundynamic.getitdone.domain;


public class Estimate {

	protected int optimistic = 0;
	protected int pessimistic = 0;
	protected int mostLikely = 0;

	public Estimate() {
		setOptimistic(0);
		setPessimistic(1);
		setMostLikely(1);
	}
	
	public Estimate add(Estimate anotherEstimate) {
		int newOptimistic = this.optimistic + anotherEstimate.getOptimistic();
		int newPessimistic = this.pessimistic + anotherEstimate.getPessimistic();
		int newMostLikely = this.mostLikely + anotherEstimate.getMostLikely();
		return new Estimate(newOptimistic, newPessimistic, newMostLikely);
	}

	public Estimate(int optimistic, int pessimistic, int mostLikely) {
		if (optimistic > pessimistic) {
			throw new IllegalArgumentException("Pessimistic value [" + pessimistic + "] is not allowed to be lower than optimistic value of [" + optimistic + "]");
		}
		setOptimistic(optimistic);
		setPessimistic(pessimistic);
		setMostLikely(mostLikely);
	}
	
	public static Estimate createWithAverageMostLikely(int optimistic, int pessimistic) {
		Estimate estimate = new Estimate();
		estimate.setPessimistic(pessimistic);
		estimate.setOptimistic(optimistic);
		estimate.setMostLikely((pessimistic + optimistic) / 2);
		return estimate;
	}
	
	public int getPERTEstimate() {
		int result = (optimistic) + (4 * mostLikely) + pessimistic;
		return result / 6;
	}

	public void setOptimistic(int optimistic) {
		this.optimistic = optimistic;
		if (this.optimistic < 0) setOptimistic(0);
		if (optimistic > pessimistic) {
			setPessimistic((optimistic + 1));
		}
	}

	public void setPessimistic(int pessimistic) {
		this.pessimistic = pessimistic;
		if (this.pessimistic < 1) setPessimistic(1);
		if (pessimistic < optimistic) {
			setOptimistic((pessimistic - 1)); 
		}
	}

	public void setMostLikely(int mostLikely) {
		if (mostLikely < optimistic) mostLikely = optimistic;
		if (mostLikely > pessimistic) mostLikely = pessimistic;
		this.mostLikely = mostLikely;
	}

	public int getOptimistic() {
		return optimistic;
	}

	public int getPessimistic() {
		return pessimistic;
	}

	public int getMostLikely() {
		return mostLikely;
	}

}
