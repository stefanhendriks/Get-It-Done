package com.fundynamic.getitdone.domain;

import junit.framework.Assert;

import org.junit.Test;


public class EstimateTest {

	private static final int PESSIMISTIC_FIFTEEN = 15;
	private static final int OPTIMISTIC_FIVE = 5;

	@Test
	// Example taken from: http://www.techrepublic.com/blog/project-management/use-pert-technique-for-more-accurate-estimates/120
	public void mustReturnPERTEstimate() {
		Estimate estimate = new Estimate();
		
		estimate.setOptimistic(6);
		estimate.setPessimistic(26);
		estimate.setMostLikely(10);
		
		// Act
		int pert = estimate.getPERTEstimate();
		
		Assert.assertEquals(12, pert);
	}
	
	@Test
	public void mustCreateEstimateWithAverageValueForMostLikely() {
		Estimate estimate = Estimate.createWithAverageMostLikely(4, 10);
		Assert.assertEquals(7, estimate.mostLikely);
	}
	
	@Test
	public void mustLowerOptimisticByOneForPessimisticWhenPessimisticIsLowerThanOptimistic() {
		Estimate estimate = new Estimate();
		estimate.setOptimistic(4);
		estimate.setPessimistic(3);
		Assert.assertEquals(2, estimate.optimistic);
	}

	@Test
	public void mustUpPessimisticByOneForOptimisticWhenOptimisticIsHigherThanPessimistic() {
		Estimate estimate = new Estimate();
		estimate.setPessimistic(4);
		estimate.setOptimistic(6);
		Assert.assertEquals(7, estimate.pessimistic);
	}
	
	@Test
	public void mustNotAllowPessimisticBeLowerThanOne() {
		Estimate estimate = new Estimate();
		estimate.setPessimistic(0);
		Assert.assertEquals(1, estimate.pessimistic);
	}

	@Test
	public void mustNotAllowOptimisticBeLowerThanZero() {
		Estimate estimate = new Estimate();
		estimate.setOptimistic(-1);
		Assert.assertEquals(0, estimate.optimistic);
	}
	
	@Test 
	public void mustReturnEstimateWithSumOfEstimates() {
		Estimate estimate = new Estimate(5, 15, 10);
		Estimate anotherEstimate = new Estimate(10, 30, 20);
		
		// Act
		Estimate combinedEstimate = estimate.add(anotherEstimate);
		
		// Assert
		Assert.assertTrue(combinedEstimate != estimate);
		Assert.assertTrue(combinedEstimate != anotherEstimate);
		Assert.assertEquals(15, combinedEstimate.optimistic);
		Assert.assertEquals(45, combinedEstimate.pessimistic);
		Assert.assertEquals(30, combinedEstimate.mostLikely);
	}
	
	@Test
	public void mustForceMostLikelyToBeInBetween() {
		Estimate estimate = Estimate.createWithAverageMostLikely(OPTIMISTIC_FIVE, PESSIMISTIC_FIFTEEN);
		estimate.setMostLikely(4);
		Assert.assertEquals(OPTIMISTIC_FIVE, estimate.mostLikely);
		estimate.setMostLikely(16);
		Assert.assertEquals(PESSIMISTIC_FIFTEEN, estimate.mostLikely);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void mustThrowIllegalArgumentExceptionWhenPessimisticIsLowerThanOptimistic() {
		new Estimate(15, 10, 5);
	}
	
	public static class Estimate {

		private int optimistic = 0;
		private int pessimistic = 0;
		private int mostLikely = 0;

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
}
