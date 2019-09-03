package com.mart.billing.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mart.billing.repo.model.BillDo;

@Repository
public interface BillRepository extends PagingAndSortingRepository<BillDo, Long> {

}
