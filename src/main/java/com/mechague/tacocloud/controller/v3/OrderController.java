package com.mechague.tacocloud.controller.v3;

import com.mechague.tacocloud.domain.Order;
import com.mechague.tacocloud.domain.User;
import com.mechague.tacocloud.repository.jpa.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@Controller("OrderControllerV3")
@RequestMapping("/v3/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }


    @GetMapping("/current")
    public String orderForm(Model model) {
        //Not necessary because of the SessionAttribute order
        //model.addAttribute("order", new OrderEntity());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("OrderEntity submitted: " + order);
        //To get a user that is in any class, not just a controller
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();*/

        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }



}
