package com.somnus.bdd.jbehave;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.somnus.bdd.jbehave.core.CoreStories;
import com.somnus.bdd.jbehave.core.steps.BeforeAfterSteps;
import com.somnus.bdd.jbehave.steps.BankAccountSteps;

public class BankAccountStories extends CoreStories {

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(),
				new BankAccountSteps(), new BeforeAfterSteps());

	}

}
