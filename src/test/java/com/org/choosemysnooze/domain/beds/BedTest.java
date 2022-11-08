package com.org.choosemysnooze.domain.beds;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BedTest
{
    @Autowired
    private BedRepository bedRepository;

    @Test
    public void bedsHavePricesAndNames()
    {
        var myBed = Bed.builder()
                .price(2.34f)
                .name("my bed")
                .build();

        assertEquals("my bed", myBed.getName());
        assertEquals(2.34f, myBed.getPrice());
    }

    @Test
    public void bedsHaveProductCodesWhenSaved()
    {
        var myBed = Bed.builder()
                .price(2.34f)
                .name("my bed")
                .build();

        var newBed = bedRepository.save(myBed);

        assertNotNull(newBed.getProductCode());
    }
}
