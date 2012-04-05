package com.konvenit.hudson.plugins.githubbranches;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.model.StringParameterValue;

public class GithubBranchParameterValue extends StringParameterValue {
        private static final long serialVersionUID = -8244244942726975701L;
	
	@DataBoundConstructor
	public GithubBranchParameterValue(String name, String value) {
		super(name, value);
	}
        

}
