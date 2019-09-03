package com.mart.billing.repo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.mart.billing.model.Item;
import com.mart.billing.repo.model.ItemDo;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, unmappedSourcePolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface ItemMapper {
		@Mapping(target="bill.id", source = "billId")
		@Mapping(target="product.id", source = "productId")
	    ItemDo map(Item source);
		
	    @Mapping(target="billId", source = "bill.id")
	    @Mapping(target="productId", source = "product.id")
	    Item map(ItemDo source);
}
