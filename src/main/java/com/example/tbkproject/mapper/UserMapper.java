package com.example.tbkproject.mapper;

import com.example.tbkproject.data.documents.UserDocument;
import com.example.tbkproject.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(UserDocument userDocument) {
        return new UserDto(userDocument.getEmail(),
                userDocument.getPassword()
        );
    }

    public static UserDocument toDocument(UserDto userDto) {
        return new UserDocument(userDto.getEmail(),
                userDto.getPassword()
        );
    }

}
