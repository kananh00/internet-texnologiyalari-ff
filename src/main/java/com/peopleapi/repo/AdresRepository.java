package com.peopleapi.repo;

import com.peopleapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepository extends JpaRepository<Address, Long> {
}
