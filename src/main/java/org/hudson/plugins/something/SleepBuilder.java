package org.hudson.plugins.something;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Build;
import hudson.model.BuildListener;
import hudson.model.FreeStyleProject;
import hudson.model.User;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;

public class SleepBuilder extends Builder {
    private long time;

    @DataBoundConstructor
    public SleepBuilder(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean perform(Build<?, ?> build, Launcher launcher, BuildListener listener)
            throws InterruptedException, IOException {
        listener.getLogger().println("Sleeping for: " + time + " seconds. Started by: " + User.current().toString());
        Thread.sleep(time * 1000);
        return true;
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return aClass == FreeStyleProject.class;
        }

        @Override
        public String getDisplayName() {
            return "SleepBuilder";
        }

        public FormValidation doCheckTime(@QueryParameter String time) {
            try {
                if (Long.valueOf(time) < 0) {
                    return FormValidation.error("Please enter a positive number.");
                } else {
                    return FormValidation.ok();
                }

            } catch (NumberFormatException e) {
                return FormValidation.error("Please enter a number.");
            }

        }
    }
}
