package com.mechague.tacocloud.api.controller.v1;

import com.mechague.tacocloud.api.repository.jdbc.JdbcOrderRepository;
import com.mechague.tacocloud.api.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller("OrderControllerV1")
@RequestMapping("/v1/orders")
public class OrderController {

    private final JdbcOrderRepository orderRepo;

    public OrderController(JdbcOrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }


    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("OrderEntity submitted: " + order);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }



}
