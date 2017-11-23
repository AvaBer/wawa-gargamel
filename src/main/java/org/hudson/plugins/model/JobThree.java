package org.hudson.plugins.model;

import hudson.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("Duplicates")
public abstract class JobThree<JobT extends Job<JobT, RunT>, RunT extends Run<JobT, RunT>> extends Job<JobT, RunT> {
    private static final Logger LOGGER = Logger.getLogger(JobThree.class.getName());
    protected JobThree(ItemGroup parent, String name) {
        super(parent, name);
    }

    @Override
    protected List<RunT> getEstimatedDurationCandidates() {
        LOGGER.info("I GOT IN YO! JobThree represent!");
        List<RunT> candidates = new ArrayList<RunT>(6);
        List<RunT> fallbackCandidates = new ArrayList<RunT>(6);

        RunT lastSuccessful = getLastSuccessfulBuild();
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
