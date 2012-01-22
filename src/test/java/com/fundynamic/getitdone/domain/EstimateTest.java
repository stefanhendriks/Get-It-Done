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

}
