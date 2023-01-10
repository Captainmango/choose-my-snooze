package com.org.choosemysnooze.domain.beds.usecases.FindBedsByProductCode;

import an.awesome.pipelinr.Command;
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
public class FindBedsByProductCodeRequest implements Command<List<Bed>>
{
    List<String> bedProductCodes;
}
