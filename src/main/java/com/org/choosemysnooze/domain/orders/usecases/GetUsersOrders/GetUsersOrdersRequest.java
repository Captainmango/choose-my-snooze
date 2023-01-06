package com.org.choosemysnooze.domain.orders.usecases.GetUsersOrders;

import an.awesome.pipelinr.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersOrdersRequest implements Command<GetUsersOrdersResponse>
{
    private String userIdentity;
}
