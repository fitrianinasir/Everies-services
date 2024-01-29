package com.everies.order.controller;

import com.everies.order.data.io.ResMsg;
import com.everies.order.model.PaymentModel;
import com.everies.order.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<ResMsg> getAllPayments(){
        ResMsg res = new ResMsg(200, "Payment options retrieved successfully", paymentService.getAllPayment());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<ResMsg> getPaymentById(@PathVariable("id") Integer id){
        ResMsg res = new ResMsg(200, "Payment retrieved successfully", paymentService.getPaymentById(id));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<ResMsg> createPayment(@RequestBody PaymentModel paymentModel){
        ResMsg res = new ResMsg(201, "Payment created successfully", paymentService.createPayment(paymentModel));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<ResMsg> updatePayment(@PathVariable("id") Integer id, @RequestBody PaymentModel paymentModel){
        ResMsg res = new ResMsg(200, "Payment updated successfully", paymentService.updatePayment(id, paymentModel));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @DeleteMapping("/payment/{id}")
    public ResponseEntity<ResMsg> deleteMapping(@PathVariable("id") Integer id){
        Boolean isDeleted = paymentService.deletePayment(id);
        ResMsg res = new ResMsg();
        if(isDeleted){
            res.setStatus(200);
            res.setMessage("Payment deleted successfully");
            res.setData(true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }else{
            res.setStatus(400);
            res.setMessage("Delete payment failed, payment id not found");
            res.setData(false);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }


    }
}
