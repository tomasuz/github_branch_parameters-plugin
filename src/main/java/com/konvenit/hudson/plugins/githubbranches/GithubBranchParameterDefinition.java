package com.konvenit.hudson.plugins.githubbranches;

import hudson.Extension;
import hudson.model.ParameterDefinition;
import hudson.model.ParameterValue;
import java.io.IOException;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;

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
    private String password;

    @DataBoundConstructor
    public GithubBranchParameterDefinition(String name, String project, String username, String password, String description) {
        super(name, description);
        this.project = project;
        this.username = username;
        this.password = password;
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

        public String getPassword() {
		return password;
	}

        public void setPassword(String password) {
		this.password = password;
	}

    public List<String> getBranches() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            GitHubClient client = new GitHubClient();
            if(!StringUtils.isBlank(getUsername())) {
                client.setCredentials(getUsername(), getPassword());
            }
            RepositoryService repo_service = new RepositoryService(client);
            
            String[] parts = getProject().split("/");
            Repository repo = repo_service.getRepository(parts[0], parts[1]);

            List<RepositoryBranch> branches = repo_service.getBranches(repo);
            result.add("master");
            for(RepositoryBranch b: branches) {
                if(b.getName() != "master") {
                    result.add(b.getName());
                }
            }
        } catch(IOException e) {
            result.add(e.getMessage());
        }
        return result;
    }
}
