package com.konvenit.hudson.plugins.githubbranches;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;



import hudson.EnvVars;
import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.ParameterValue;
import hudson.model.ParameterDefinition;
import hudson.model.ParametersDefinitionProperty;
import hudson.model.Hudson;
import hudson.model.TaskListener;
import hudson.scm.SCM;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;


public class GithubBranchParameterDefinition extends ParameterDefinition {

    private static final long serialVersionUID = 9157832967140868122L;
        
    private final UUID uuid;

    @Extension
    public static class DescriptorImpl extends ParameterDescriptor {
        @Override
        public String getDisplayName() {
            return "Github Project";
        }
    }

    private String project;
    private String username;
    private String token;

    @DataBoundConstructor
    public GithubBranchParameterDefinition(String name, String project, String username, String token, String description) {
        super(name, description);
        this.project = project;
        this.username = username;
        this.token = token;
        this.uuid = UUID.randomUUID();               
    }
        

	@Override
	public ParameterValue createValue(StaplerRequest request) {
		String value[] = request.getParameterValues(getName());
		if (value == null) {
			return getDefaultParameterValue();
		}
		return null;
	}

	@Override
	public ParameterValue createValue(StaplerRequest request, JSONObject jO) {
		Object value = jO.get("value");
		String strValue = "";
		if (value instanceof String) {
			strValue = (String)value;
		} else if (value instanceof JSONArray) {
			JSONArray jsonValues = (JSONArray)value;
			for(int i = 0; i < jsonValues.size(); i++) {
				strValue += jsonValues.getString(i);
				if (i < jsonValues.size() - 1) {
					strValue += ",";
				}
			}
		}

        GithubBranchParameterValue gitParameterValue = new GithubBranchParameterValue(jO.getString("name"), strValue);
        return gitParameterValue;
    }

	@Override
	public ParameterValue getDefaultParameterValue() {
		String defValue = "";
		if (!StringUtils.isBlank(defValue)) {                    
			return new GithubBranchParameterValue(getName(), defValue);
		}
		return super.getDefaultParameterValue();
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

        public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

        public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
