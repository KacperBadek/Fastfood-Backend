package com.example.tbkproject.mapper;

import com.example.tbkproject.data.documents.AddOnDocument;
import com.example.tbkproject.dto.AddOnDto;

public class AddOnMapper {
    public static AddOnDto toDto(AddOnDocument addon) {
        return new AddOnDto(addon.getName(), addon.getAdditionalPrice(), addon.getQuantity());
    }

    public static AddOnDocument toDocument(AddOnDto dto) {
        return new AddOnDocument(dto.getName(), dto.getAdditionalPrice(), dto.getQuantity());
    }
}
