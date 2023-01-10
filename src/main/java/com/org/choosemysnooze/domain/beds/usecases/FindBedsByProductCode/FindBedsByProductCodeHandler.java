package com.org.choosemysnooze.domain.beds.usecases.FindBedsByProductCode;

import an.awesome.pipelinr.Command;
import com.org.choosemysnooze.common.exceptions.NotFoundException;
import com.org.choosemysnooze.domain.beds.Bed;
import com.org.choosemysnooze.domain.beds.BedRepository;

import java.util.List;

public class FindBedsByProductCodeHandler implements Command.Handler<FindBedsByProductCodeRequest, List<Bed>>
{
    private final BedRepository bedRepository;

    public FindBedsByProductCodeHandler(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Override
    public List<Bed> handle(FindBedsByProductCodeRequest command) {
        var beds = bedRepository.findByProductCodeIn(command.getBedProductCodes());

        if (beds.isEmpty()) {
            throw new NotFoundException("No beds found with supplied IDs");
        }

        return beds;
    }
}
