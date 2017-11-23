package org.hudson.plugins.column;

import hudson.Extension;
import hudson.Util;
import hudson.model.Job;
import hudson.model.Run;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import org.hudson.plugins.model.BuildDurationEstimate;
import org.hudson.plugins.model.Job2;
import org.hudson.plugins.model.JobWrapper;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;
import java.io.IOException;

public class AverageBuildDurationEstimateViewColumn extends ListViewColumn {
    @DataBoundConstructor
    public AverageBuildDurationEstimateViewColumn() {
    }

    public String getAverageBuildDurationString(Job<?, ?> job) {
        return Util.getTimeSpanString(new JobWrapper(job).getEstimatedDuration());
    }

    @Extension(ordinal=DEFAULT_COLUMNS_ORDINAL_PROPERTIES_START-5)
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public boolean shownByDefault() {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Average Duration";
        }
    }
}
