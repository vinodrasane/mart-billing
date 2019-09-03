package com.mart.billing.repo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.mart.billing.model.Product;
import com.mart.billing.model.ProductCategory;
import com.mart.billing.repo.model.ProductCategoryDo;
import com.mart.billing.repo.model.ProductDo;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, unmappedSourcePolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface ProductMapper {
	ProductDo map (Product product);
	Product map(ProductDo productDo);
	
	ProductCategoryDo map (ProductCategory productCat);
	ProductCategory map (ProductCategoryDo productCatDo);
}
