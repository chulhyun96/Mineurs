package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.newlecmineursprj.entity.Address;

@Mapper
public interface AddressRepository {

    void reg(Address address, Long memberId);

    List<Address> findAllByMemberId(Long memberId);
}
