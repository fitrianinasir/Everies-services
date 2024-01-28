package com.everies.order.repository;

import com.everies.order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface OrderRepo extends JpaRepository<OrderModel, Integer> {
    @Transactional
    @Modifying()
    @Query(value = "UPDATE TBL_ORDERS SET payment_status= :paymentStatus, payment_time= :paymentTime WHERE id = :orderId", nativeQuery = true)
    void updatePayment(@Param("orderId") Integer orderId, @Param("paymentStatus") String paymentStatus, @Param("paymentTime")LocalDateTime paymentTime);
}
