package org.hudson.plugins.something;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.Job;
import jenkins.model.TransientActionFactory;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
//@Extension
public class SomeInfoFactory extends TransientActionFactory<Job> {
    @Override
    public Class<Job> type() {
        return Job.class;
    }

    @Nonnull
    @Override
    public Collection<? extends Action> createFor(@Nonnull Job target) {
        return Arrays.asList(new SomeInfoAction(target), new SomeProjectAction());
    }

}
