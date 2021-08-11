package org.zchzh.validator.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.zchzh.validator.annotation.PasswordValidator;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/8/11
 */


@Data
public class TestReq {

    @NotNull
    private String name;

    @NotBlank
    private String desc;

    @Max(9999)
    @Min(0)
    private Integer age;

    @AssertFalse
    private Boolean deleted;


    @Email
    private String email;

    @NotEmpty
    @Size(min = 1, max = 2)
    private List<String> stringList;

    @Length(min = 1, max = 3)
    private String length;

    @PasswordValidator
    private String password;



}
