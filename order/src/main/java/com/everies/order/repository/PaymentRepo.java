package com.everies.order.repository;

import com.everies.order.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentModel, Integer> {
}
