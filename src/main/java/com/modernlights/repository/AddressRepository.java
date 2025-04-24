package com.modernlights.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modernlights.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
