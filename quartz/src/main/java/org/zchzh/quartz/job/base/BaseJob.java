package org.zchzh.quartz.job.base;

import org.quartz.*;

/**
 * @author zengchzh
 * @date 2021/3/4
 */
public interface BaseJob extends Job {

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * fires that is associated with the <code>Job</code>.
     * </p>
     *
     * <p>
     * The implementation may wish to set a
     * {@link JobExecutionContext#setResult(Object) result} object on the
     * {@link JobExecutionContext} before this method exits.  The result itself
     * is meaningless to Quartz, but may be informative to
     * <code>{@link JobListener}s</code> or
     * <code>{@link TriggerListener}s</code> that are watching the job's
     * execution.
     * </p>
     *
     * @param context 上下文
     * @throws JobExecutionException if there is an exception while executing the job.
     */
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}
