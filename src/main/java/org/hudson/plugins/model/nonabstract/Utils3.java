package org.hudson.plugins.model.nonabstract;

import hudson.model.Build;
import hudson.model.ItemGroup;
import hudson.model.Job;
import hudson.model.Project;
import org.hudson.plugins.model.Utils2;

public abstract class Utils3<JobT extends Project<JobT,RunT>,RunT extends Build<JobT,RunT>>  extends Utils2 <JobT, RunT>{
    private Job<JobT, RunT> self;
    protected Utils3(ItemGroup parent, String name) {
        super(parent, name);
    }

    public void setSelf(Job<JobT, RunT> self) {
        this.self = self;
    }

    public Job<JobT, RunT> getSelf() {
        return self;
    }
}
