package com.fundynamic.getitdone.domain;

public class PERTEstimate {

	protected int optimistic = 0;
	protected int pessimistic = 0;
	protected int mostLikely = 0;

	public PERTEstimate(int optimistic, int pessimistic, int mostLikely) {
		if (optimistic > pessimistic) {
			throw new IllegalArgumentException("Pessimistic value ["
					+ pessimistic
					+ "] is not allowed to be lower than optimistic value of ["
					+ optimistic + "]");
		}
		setOptimistic(optimistic);
		setPessimistic(pessimistic);
		setMostLikely(mostLikely);
	}

	public PERTEstimate() {
		setOptimistic(0);
		setPessimistic(0);
		setMostLikely(0);
	}

	public static PERTEstimate createEmpty() {
		PERTEstimate PERTEstimate = new PERTEstimate();
		PERTEstimate.pessimistic = 0;
		PERTEstimate.optimistic = 0;
		PERTEstimate.mostLikely = 0;
		return PERTEstimate;
	}

	public static PERTEstimate createWithAverageMostLikely(int optimistic,
			int pessimistic) {
		PERTEstimate PERTEstimate = new PERTEstimate();
		PERTEstimate.setPessimistic(pessimistic);
		PERTEstimate.setOptimistic(optimistic);
		PERTEstimate.setMostLikely((pessimistic + optimistic) / 2);
		return PERTEstimate;
	}

	public PERTEstimate add(PERTEstimate anotherPERTEstimate) {
		int newOptimistic = this.optimistic + anotherPERTEstimate.getOptimistic();
		int newPessimistic = this.pessimistic
				+ anotherPERTEstimate.getPessimistic();
		int newMostLikely = this.mostLikely + anotherPERTEstimate.getMostLikely();
		return new PERTEstimate(newOptimistic, newPessimistic, newMostLikely);
	}

	public int getPERTEstimate() {
		int result = (optimistic) + (4 * mostLikely) + pessimistic;
		return result / 6;
	}

	public void setOptimistic(int optimistic) {
		this.optimistic = optimistic;
		if (this.optimistic < 0)
			setOptimistic(0);
		if (optimistic > pessimistic) {
			setPessimistic((optimistic + 1));
		}
	}

	public void setPessimistic(int pessimistic) {
		this.pessimistic = pessimistic;
		if (this.pessimistic < 1)
			setPessimistic(1);
		if (pessimistic < optimistic) {
			setOptimistic((pessimistic - 1));
		}
	}

	public void setMostLikely(int mostLikely) {
		if (mostLikely < optimistic)
			mostLikely = optimistic;
		if (mostLikely > pessimistic)
			mostLikely = pessimistic;
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

	public String toString() {
		final String TAB = "    ";

		String retValue = "";

		retValue = "PERTEstimate ( optimistic = "
				+ this.optimistic + TAB + "pessimistic = " + this.pessimistic
				+ TAB + "mostLikely = " + this.mostLikely + TAB + " )";

		return retValue;
	}

}
