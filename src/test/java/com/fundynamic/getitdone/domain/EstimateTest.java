package com.fundynamic.getitdone.domain;

import junit.framework.Assert;

import org.junit.Test;


public class EstimateTest {

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
	
	public class Estimate {

		private int optimistic = 0;
		private int pessimistic = 0;
		private int mostLikely = 0;
		
		// Formula: (O + 4M + P)/6
		public int getPERTEstimate() {
			int result = (optimistic) + (4 * mostLikely) + pessimistic;
			return result / 6;
		}

		public void setOptimistic(int optimistic) {
			this.optimistic = optimistic;
		}

		public void setPessimistic(int pessimistic) {
			this.pessimistic = pessimistic;
		}

		public void setMostLikely(int mostLikely) {
			this.mostLikely = mostLikely;
		}

	}
}
