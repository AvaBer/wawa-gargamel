package org.hudson.plugins.model;

import hudson.model.Job;
import hudson.model.Result;
import hudson.model.Run;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class BuildDurationEstimate {
    private static final Logger LOGGER = Logger.getLogger(BuildDurationEstimate.class.getName());

    private static  <JobT extends Job<JobT, RunT>, RunT extends Run<JobT, RunT>>
    List<RunT> timeEstimateCandidates(Job<JobT, RunT> job) {
        List<RunT> candidates = new ArrayList<RunT>(6);
        List<RunT> fallbackCandidates = new ArrayList<RunT>(6);
        RunT lastSuccessful = job.getLastSuccessfulBuild();
        int lastSuccessfulNumber = -1;
        if (lastSuccessful != null) {
            candidates.add(lastSuccessful);
            lastSuccessfulNumber = lastSuccessful.getNumber();
        }
        RunT r = job.getLastBuild();
        int i = 0;
        while (r != null && candidates.size() < 6 && i < 12) {
            if (!r.isBuilding() && r.getResult() != null && r.getNumber() != lastSuccessfulNumber) {
                Result result = r.getResult();
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
            candidates.add(run);
        }
        return candidates;

    }


    public static <JobT extends Job<JobT, RunT>, RunT extends Run<JobT, RunT>>
    long getEstimatedDuration(Job<JobT, RunT> job) {
        List<RunT> builds = timeEstimateCandidates(job);
        if (builds.isEmpty()) return -1;
        long totalDuration = 0;
        for (RunT b : builds) {
            totalDuration += b.getDuration();
        }
        if (totalDuration == 0) return -1;
        return Math.round((double) totalDuration / builds.size());
    }

}
