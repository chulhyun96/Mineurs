package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Address;

@Mapper
public interface AddressRepository {

    void reg(Address address, Long memberId);

    List<Address> findAllByMemberId(Long memberId);

    Address findById(long id, Long memberId);
}
