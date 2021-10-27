package com.geniessoft.backend.service.impl;

import com.geniessoft.backend.model.Address;
import com.geniessoft.backend.repository.AddressRepository;
import com.geniessoft.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address findAddressById(Integer addressId) {
        return addressRepository.findById(addressId).get();
    }
}
