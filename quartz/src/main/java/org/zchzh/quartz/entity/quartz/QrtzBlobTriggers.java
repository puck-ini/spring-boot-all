package org.zchzh.quartz.entity.quartz;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/3/5
 */

@Entity
public class QrtzBlobTriggers implements Serializable {

    /**
     * 计划名
     */
    @Id
    private String schedName;

    /**
     * 触发器名称
     */
    private String triggerName;

    /**
     * 触发器组
     */
    private String triggerGroup;

    private Byte[] blobData;
}
