package org.hudson.plugins.something;

import hudson.model.Project;
import hudson.model.ProminentProjectAction;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;

public class SomeProjectAction implements ProminentProjectAction {
    @DataBoundConstructor
    public SomeProjectAction() {
    }

    @CheckForNull
    @Override
    public String getIconFileName() {
        return "star.png";
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "SomeProjectAction";
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "someProjectAction";
    }
}
