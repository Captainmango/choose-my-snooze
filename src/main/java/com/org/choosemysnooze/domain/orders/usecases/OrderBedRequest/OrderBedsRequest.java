package com.org.choosemysnooze.domain.orders.usecases.OrderBedRequest;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import com.org.choosemysnooze.domain.beds.Bed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBedsRequest implements Command<Voidy>
{
    private List<Bed> beds;
}
