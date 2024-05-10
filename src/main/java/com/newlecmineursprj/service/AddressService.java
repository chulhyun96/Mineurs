package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Address;

public interface AddressService {
    void regBymemberId(Address address, Long memberId);

    List<Address> findAll(Long memberId);
}
