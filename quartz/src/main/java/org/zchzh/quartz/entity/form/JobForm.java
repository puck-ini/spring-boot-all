package org.zchzh.quartz.entity.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author zengchzh
 * @date 2021/3/4
 */

@Data
@Accessors(chain = true)
public class JobForm {

    /**
     * 定时任务全类名
     */
    @NotBlank(message = "类名不能为空")
    private String jobClassName;

    /**
     * 任务组名
     */
    @NotBlank(message = "任务组名不能为空")
    private String jobGroupName;

    /**
     * 定时任务cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
}
