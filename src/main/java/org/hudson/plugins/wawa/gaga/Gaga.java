package org.hudson.plugins.wawa.gaga;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.InvisibleAction;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.CheckForNull;

@Extension
public class Gaga implements Action {

    @DataBoundConstructor
    public Gaga() {
    }

    @CheckForNull
    @Override
    public String getIconFileName() {
        return "plugin.png";
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "Gaga";
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "Gaga";
    }
}
