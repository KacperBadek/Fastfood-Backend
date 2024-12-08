package com.example.tbkproject.service;

import com.example.tbkproject.data.repositories.AddOnRepository;
import com.example.tbkproject.exceptions.exception.addon.AddOnNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddOnService {

    private final AddOnRepository addOnRepository;

    public Double getAddOnPriceByName(String name){
        var addOn = addOnRepository.findByName(name).orElseThrow(() -> new AddOnNotFoundException(name));
        return addOn.getAdditionalPrice();
    }

}
