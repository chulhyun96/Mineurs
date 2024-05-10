package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Address;
import com.newlecmineursprj.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Override
    public void regBymemberId(Address address, Long memberId) {
        repository.reg(address, memberId);
    }

    @Override
    public List<Address> findAll(Long memberId) {
        return repository.findAllByMemberId(memberId);
    }

}
