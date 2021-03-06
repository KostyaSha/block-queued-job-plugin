package org.jenkinsci.plugins.blockqueuedjob;

import hudson.Extension;
import hudson.Util;
import hudson.model.AbstractProject;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.blockqueuedjob.condition.BlockQueueCondition;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import javax.annotation.CheckForNull;
import java.util.List;

/**
 * Project property for {@link BlockItemQueueTaskDispatcher}. Contains configured conditions.
 *
 * @author Kanstantsin Shautsou
 */
public class BlockItemJobProperty extends JobProperty<AbstractProject<?, ?>> {

    @CheckForNull
    private List<BlockQueueCondition> conditions;

    @DataBoundConstructor
    public BlockItemJobProperty(List<BlockQueueCondition> conditions) {
        this.conditions = Util.fixNull(conditions);
    }

    public List<BlockQueueCondition> getConditions() {
        return conditions;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    public static class DescriptorImpl extends JobPropertyDescriptor {
        @Override
        public JobProperty<?> newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            if (req.hasParameter("hasBlockedJobProperty")) {
                return super.newInstance(req, formData);
            } else {
                return null;
            }
        }

        @Override
        public String getDisplayName() {
            return "Block Job";
        }
    }
}
