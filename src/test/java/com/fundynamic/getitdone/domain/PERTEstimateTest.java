package com.fundynamic.getitdone.domain;

import junit.framework.Assert;

import org.junit.Test;


public class PERTEstimateTest {

	private static final int PESSIMISTIC_FIFTEEN = 15;
	private static final int OPTIMISTIC_FIVE = 5;

	
	@Test
	// Example taken from: http://www.techrepublic.com/blog/project-management/use-pert-technique-for-more-accurate-estimates/120
	public void mustReturnPERTEstimate() {
		PERTEstimate PERTEstimate = new PERTEstimate();
		
		PERTEstimate.setOptimistic(6);
		PERTEstimate.setPessimistic(26);
		PERTEstimate.setMostLikely(10);
		
		// Act
		int pert = PERTEstimate.getPERTEstimate();
		
		Assert.assertEquals(12, pert);
	}
	
	@Test
	public void mustCreateEstimateWithAverageValueForMostLikely() {
		PERTEstimate pertEstimate = PERTEstimate.createWithAverageMostLikely(4, 10);
		Assert.assertEquals(7, pertEstimate.mostLikely);
	}
	
	@Test
	public void mustLowerOptimisticByOneForPessimisticWhenPessimisticIsLowerThanOptimistic() {
		PERTEstimate PERTEstimate = new PERTEstimate();
		PERTEstimate.setOptimistic(4);
		PERTEstimate.setPessimistic(3);
		Assert.assertEquals(2, PERTEstimate.optimistic);
	}

	@Test
	public void mustUpPessimisticByOneForOptimisticWhenOptimisticIsHigherThanPessimistic() {
		PERTEstimate PERTEstimate = new PERTEstimate();
		PERTEstimate.setPessimistic(4);
		PERTEstimate.setOptimistic(6);
		Assert.assertEquals(7, PERTEstimate.pessimistic);
	}
	
	@Test
	public void mustNotAllowPessimisticBeLowerThanOne() {
		PERTEstimate PERTEstimate = new PERTEstimate();
		PERTEstimate.setPessimistic(0);
		Assert.assertEquals(1, PERTEstimate.pessimistic);
	}

	@Test
	public void mustNotAllowOptimisticBeLowerThanZero() {
		PERTEstimate PERTEstimate = new PERTEstimate();
		PERTEstimate.setOptimistic(-1);
		Assert.assertEquals(0, PERTEstimate.optimistic);
	}
	
	@Test 
	public void mustReturnEstimateWithSumOfEstimates() {
		PERTEstimate PERTEstimate = new PERTEstimate(5, 15, 10);
		PERTEstimate anotherPERTEstimate = new PERTEstimate(10, 30, 20);
		
		// Act
		PERTEstimate combinedPERTEstimate = PERTEstimate.add(anotherPERTEstimate);
		
		// Assert
		Assert.assertTrue(combinedPERTEstimate != PERTEstimate);
		Assert.assertTrue(combinedPERTEstimate != anotherPERTEstimate);
		Assert.assertEquals(15, combinedPERTEstimate.optimistic);
		Assert.assertEquals(45, combinedPERTEstimate.pessimistic);
		Assert.assertEquals(30, combinedPERTEstimate.mostLikely);
	}
	
	@Test
	public void mustForceMostLikelyToBeInBetween() {
		PERTEstimate pertEstimate = PERTEstimate.createWithAverageMostLikely(OPTIMISTIC_FIVE, PESSIMISTIC_FIFTEEN);
		pertEstimate.setMostLikely(4);
		Assert.assertEquals(OPTIMISTIC_FIVE, pertEstimate.mostLikely);
		pertEstimate.setMostLikely(16);
		Assert.assertEquals(PESSIMISTIC_FIFTEEN, pertEstimate.mostLikely);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void mustThrowIllegalArgumentExceptionWhenPessimisticIsLowerThanOptimistic() {
		new PERTEstimate(15, 10, 5);
	}

}
