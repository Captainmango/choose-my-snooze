package com.org.choosemysnooze.fixtures;

import com.org.choosemysnooze.domain.beds.Bed;
import com.org.choosemysnooze.domain.beds.BedRepository;

import java.util.List;

public class BedsFixture implements BaseFixture
{
    private final BedRepository bedRepository;

    public BedsFixture(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Override
    public void run()
    {
        if (bedRepository.findAll().size() == 0) {
            var bedList = List.of(
                    Bed.builder().name("Queen Divan").price(23.99f).build(),
                    Bed.builder().name("King Divan").price(30.99f).build(),
                    Bed.builder().name("Bunk Bed").price(35.15f).build(),
                    Bed.builder().name("Single").price(21.35f).build()
            );

            bedRepository.saveAll(bedList);
        }
    }
}
