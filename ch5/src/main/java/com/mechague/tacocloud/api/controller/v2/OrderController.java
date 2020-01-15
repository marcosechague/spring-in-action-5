package com.mechague.tacocloud.api.controller.v2;

import com.mechague.tacocloud.api.repository.jdbc.JdbcOrderRepository;
import com.mechague.tacocloud.api.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller("OrderControllerV2")
@RequestMapping("/v2/orders")
@SessionAttributes("order")
public class OrderController {

    private final JdbcOrderRepository orderRepo;

    public OrderController(JdbcOrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }


    @GetMapping("/current")
    public String orderForm(Model model) {
        //Not necessary because of the SessionAttribute order
        //model.addAttribute("order", new OrderEntity());
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
