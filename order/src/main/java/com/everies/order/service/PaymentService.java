package com.everies.order.service;

import com.everies.order.model.PaymentModel;
import com.everies.order.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepo paymentRepo;

    public List<PaymentModel> getAllPayment(){
        return paymentRepo.findAll();
    }

    public Optional<PaymentModel> getPaymentById(Integer id){
        return paymentRepo.findById(id);
    }

    public PaymentModel createPayment(PaymentModel paymentModel){
        return paymentRepo.save(paymentModel);
    }
    public PaymentModel updatePayment(Integer id, PaymentModel paymentModel){
        paymentModel.setId(id);
        return paymentRepo.save(paymentModel);
    }

    public Boolean deletePayment(Integer id){
        Boolean isExist = paymentRepo.existsById(id);
        if(isExist){
            paymentRepo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
