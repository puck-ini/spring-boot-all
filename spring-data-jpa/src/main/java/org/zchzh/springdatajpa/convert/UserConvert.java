package org.zchzh.springdatajpa.convert;

import lombok.extern.slf4j.Slf4j;
import org.zchzh.springdatajpa.dto.UserDTO;
import org.zchzh.springdatajpa.entity.common.UserEntity;

/**
 * @author zengchzh
 * @date 2021/5/11
 */

@Slf4j
public class UserConvert {

    public static UserDTO toDto(UserEntity userEntity) {
        log.info(userEntity.toString());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername().getUsername());
        userDTO.setPassword(userEntity.getPassword().getPassword());
        userDTO.setUserType(userEntity.getUserType().name());
        log.info(userDTO.toString());
        return userDTO;
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        log.info(userDTO.toString());
        UserEntity userEntity = new UserEntity();
        userEntity.username(userDTO.getUsername()).password(userDTO.getPassword()).userType(userDTO.getUserType());
        userEntity.setId(userDTO.getId());
        log.info(userEntity.toString());
        return userEntity;
    }
}
