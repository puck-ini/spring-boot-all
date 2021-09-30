package org.zchzh.querydsl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/9/30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDTO implements Serializable {

    private Long id;

    private String msg;

    private String info;

    private Long otherId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
