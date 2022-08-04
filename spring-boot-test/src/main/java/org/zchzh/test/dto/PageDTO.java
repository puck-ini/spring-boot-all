package org.zchzh.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zengchzh
 * @date 2022/8/3
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDTO<T> {

    private Integer currentPage;
    private Integer currentSize;
    private Long total;
    private Integer totalPages;
    private List<T> content;
}
