package com.mart.billing.repo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, unmappedSourcePolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface BillMapper {

/*	BillDo map(Bill bill);

	Bill map(BillDo billDo);*/
}
