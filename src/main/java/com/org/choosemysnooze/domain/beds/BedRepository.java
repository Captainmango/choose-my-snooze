package com.org.choosemysnooze.domain.beds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long>
{
    Optional<Bed> findByName(String name);
    Optional<Bed> findByProductCode(String productCode);
    List<Bed> findByProductCodeIn(List<String> productCodes);
}
