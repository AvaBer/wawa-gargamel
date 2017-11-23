package org.hudson.plugins.wawa;

import hudson.Extension;
import hudson.Util;
import hudson.model.*;
import net.sf.json.JSONObject;
import org.hudson.plugins.model.JobWrapper;
import org.kohsuke.stapler.*;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;

import javax.annotation.CheckForNull;
import java.util.logging.Logger;

@ExportedBean(defaultVisibility=2)
@Extension
public class Wawa<J extends Project<J, R>, R extends Build<J, R>> implements ProminentProjectAction {
    private static final Logger LOGGER = Logger.getLogger(Wawa.class.getName());
    private AbstractProject target;
    private JobWrapper wrapper;
    @DataBoundSetter
    private Stapler stapler;


    public Api getApi() {
        return new Api(this);
    }


    public String doStuff() {
        return this.getBuildTimeEstimate();
    }

    @Exported
    public String getInformation() {
        return this.getBuildTimeEstimate();
    }

    public Wawa() {
    }

    @DataBoundConstructor
    public Wawa(AbstractProject target) {
        this.target = target;
        this.wrapper = new JobWrapper(target);
    }

    @CheckForNull
    @Override
    public String getIconFileName() {
        return "package.png";
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "Average Build Time";
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "wawa";
    }

    @Exported
    public String getBuildTimeEstimate() {
        return Util.getTimeSpanString(wrapper.getEstimatedDuration());
    }

    public String getEstimateDebug() {
        return Util.getTimeSpanString(wrapper.getEstimatedDurationDebug());
    }

    public AbstractProject getTarget() {
        return target;
    }

    @WebMethod(name = "time")
    public String doEstimate() {
        return Util.getTimeSpanString(wrapper.getEstimatedDuration());
    }

    @WebMethod(name = "info")
    public String doSomething(@QueryParameter String query) {
        switch (query.toLowerCase()) {
            case "time":
                return Util.getTimeSpanString(wrapper.getEstimatedDuration());
            case "marco":
                return "polo";
            case "project":
                return target.getUrl();
            default:
                return "bad request";
        }
    }

    public String getDump(StaplerResponse resp, StaplerRequest req) {
        return Stapler.getCurrent().toString();
    }

    public Stapler getStapler() {
        return stapler;
    }

    public void setStapler(Stapler stapler) {
        this.stapler = stapler;
    }

    @WebMethod(name = "resp")
    public HttpResponse doRespond() {

        return HttpResponses.plainText(Util.getTimeSpanString(wrapper.getEstimatedDuration()));
    }

    @WebMethod(name = "jsn")
    public JSONObject doResp() {
        return JSONObject.fromObject(wrapper);
    }

    private void stuff() {
    }
}
