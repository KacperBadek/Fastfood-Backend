package com.example.tbkproject.mappers;

import com.example.tbkproject.data.documents.AddOn;
import com.example.tbkproject.dto.AddOnDto;

public class AddOnMapper {
    public static AddOnDto toDto(AddOn addon) {
        return new AddOnDto(addon.getName(), addon.getAdditionalPrice());
    }

    public static AddOn toDocument(AddOnDto dto) {
        return new AddOn(dto.getName(), dto.getAdditionalPrice());
    }
}
