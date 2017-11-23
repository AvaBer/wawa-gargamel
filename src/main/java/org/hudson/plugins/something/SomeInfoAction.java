package org.hudson.plugins.something;

import hudson.model.Action;
import hudson.model.Job;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;

public class SomeInfoAction implements Action {
    private Job job;

    @DataBoundConstructor
    public SomeInfoAction(Job job) {
        setJob(job);
    }

    @CheckForNull
    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "SomeInfoAction";
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "SomeInfo";
    }

    private Jenkins jenkins = Jenkins.getInstance();

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    private void dump() {

    }
}
