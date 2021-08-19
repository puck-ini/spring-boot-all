package org.zchzh.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/8/18
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "demo_user")
public class DemoUser {

    @Id
    private Long id;

    @Field
    private String username;

    @Field
    private String name;

    @Field
    private String desc;

    @Field
    private Integer age;

    @Field
    private Date createTime;

    @Field
    private Double num;

    @Field
    private BigDecimal decimal;
}
