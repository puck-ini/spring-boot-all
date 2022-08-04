package org.zchzh.test.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.entity.User;
import org.zchzh.test.request.CreateReq;

/**
 * @author zengchzh
 * @date 2022/8/3
 */

@Mapper
public interface UserConvert {

    UserConvert CONVERT = Mappers.getMapper(UserConvert.class);

    UserDTO toDTO(User user);

    User toEntity(CreateReq req);
}
