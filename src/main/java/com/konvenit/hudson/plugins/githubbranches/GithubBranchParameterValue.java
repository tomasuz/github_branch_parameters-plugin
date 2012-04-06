package com.konvenit.hudson.plugins.githubbranches;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.model.StringParameterValue;

public class GithubBranchParameterValue extends StringParameterValue {
	
	@DataBoundConstructor
	public GithubBranchParameterValue(String name, String value) {
		super(name, value);
	}
        
}
