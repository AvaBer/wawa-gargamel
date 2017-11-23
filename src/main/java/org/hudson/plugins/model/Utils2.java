package org.hudson.plugins.model;

import hudson.model.*;
import jenkins.model.ParameterizedJobMixIn;
import jenkins.model.lazy.LazyBuildMixIn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@SuppressWarnings("Duplicates")
public abstract class Utils2<JobT extends Project<JobT,RunT>,RunT extends Build<JobT,RunT>> extends Job<JobT, RunT>
        implements BuildableItem, LazyBuildMixIn.LazyLoadingJob<JobT, RunT>, ParameterizedJobMixIn.ParameterizedJob{
// <JobT extends AbstractProject<JobT,RunT>,RunT extends AbstractBuild<JobT,RunT>>
    // public abstract class Utils2<JobT extends Job<JobT, RunT>, RunT extends Run<JobT, RunT>> extends Job<JobT, RunT> {
    private static final Logger LOGGER = Logger.getLogger(Utils2.class.getName());

    protected Utils2(ItemGroup parent, String name) {
        super(parent, name);
    }


    protected List<RunT> getEstimatedDurationCandidates() {
        LOGGER.info("I GOT IN YO! JobThree represent!");
        List<RunT> candidates = new ArrayList<RunT>(6);
        List<RunT> fallbackCandidates = new ArrayList<RunT>(6);

        RunT lastSuccessful = super.getLastSuccessfulBuild();
        LOGGER.info("Run lastSuccessful works");
        int lastSuccessfulNumber = -1;
        if (lastSuccessful != null) {
            candidates.add(lastSuccessful);
            lastSuccessfulNumber = lastSuccessful.getNumber();
        }

        int i = 0;
        RunT r = getLastBuild();
        LOGGER.info("r = getLastBuild works");
        while (r != null && candidates.size() < 6 && i < 12) {
            if (!r.isBuilding() && r.getResult() != null && r.getNumber() != lastSuccessfulNumber) {
                Result result = r.getResult();
                LOGGER.info("result = r.getResult works");
                if (result.isBetterOrEqualTo(Result.UNSTABLE)) {
                    candidates.add(r);
                } else if (result.isCompleteBuild()) {
                    fallbackCandidates.add(r);
                }
            }
            i++;
            r = r.getPreviousBuild();
        }

        while (candidates.size() < 6) {
            if (fallbackCandidates.isEmpty())
                break;
            RunT run = fallbackCandidates.remove(0);
            LOGGER.info("run = fallbackCandidates.remove works");
            candidates.add(run);
        }

        return candidates;
    }
}