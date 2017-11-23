package org.hudson.plugins;

import hudson.Extension;
import hudson.model.RootAction;

import javax.annotation.CheckForNull;
//@Extension
public class AverageBuildTimeRootAction implements RootAction {
    @CheckForNull
    @Override
    public String getIconFileName() {
        return "monitor.png";
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "AverageBuildTime";
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "average";
    }
}
