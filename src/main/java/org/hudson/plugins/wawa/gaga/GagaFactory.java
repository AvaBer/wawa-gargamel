package org.hudson.plugins.wawa.gaga;

import hudson.Extension;
import hudson.model.*;
import jenkins.model.Jenkins;
import jenkins.model.TransientActionFactory;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;

@Extension
public class GagaFactory extends Actionable {




    @DataBoundConstructor
    public GagaFactory() {
        addGaga();
    }

    private void addGaga() {
        Jenkins jenkins = Jenkins.getInstance();
        List<AbstractProject> items = jenkins.getItems(AbstractProject.class);
        for (AbstractProject item : items) {
            try {
                item.addAction(new Gaga());
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getSearchUrl() {
        return null;
    }
}
