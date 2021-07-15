package org.zchzh.springdatajpa.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/7/15
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchReq implements Serializable {


    private Integer pageNum;

    private Integer pageSize;

    private String name;

    private Date start;

    private Date end;
}
