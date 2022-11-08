package com.org.choosemysnooze.domain.beds;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BedRepositoryTest
{
    @Autowired
    private BedRepository bedRepository;

    @Test
    public void generatesProductCodeOnSave()
    {
        var myBed = Bed.builder()
                .name("Simple Bed")
                .price(2.34f)
                .build();

        var savedBed = bedRepository.save(myBed);

        assertEquals(myBed.getName(), savedBed.getName());
        assertNotNull(savedBed.getProductCode());
    }

    @Test
    public void canGetABedByName()
    {
        var myBed = Bed.builder()
                .name("Simple Bed")
                .price(2.34f)
                .build();

        var savedBed = bedRepository.save(myBed);

        var foundBed = bedRepository.findByName("Simple Bed").orElseThrow();

        assertEquals(savedBed.getName(), foundBed.getName());
        assertEquals(savedBed.getId(), foundBed.getId());
    }

    @Test
    public void canGetABedByProductCode()
    {
        var myBed = Bed.builder()
                .name("Simple Bed")
                .price(2.34f)
                .build();

        var savedBed = bedRepository.save(myBed);
        String theProductCode = savedBed.getProductCode();

        System.out.println(theProductCode);

        var foundBed = bedRepository.findByProductCode(theProductCode).orElseThrow();

        assertEquals(savedBed.getName(), foundBed.getName());
        assertEquals(savedBed.getId(), foundBed.getId());
    }
}
