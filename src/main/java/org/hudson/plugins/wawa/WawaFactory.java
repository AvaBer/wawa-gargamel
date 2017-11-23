package org.hudson.plugins.wawa;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

@Extension
public class WawaFactory extends TransientProjectActionFactory {
    private static final Logger LOGGER = Logger.getLogger(WawaFactory.class.getName());
    @Override
    public Collection<? extends Action> createFor(AbstractProject target) {
//        LOGGER.info(target.getActions().toString() + " <---- All them actions");
        return Arrays.asList(new Wawa(target));
    }
}
