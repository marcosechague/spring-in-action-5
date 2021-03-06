package com.mechague.tacocloud.api.controller.v3;

import com.mechague.tacocloud.api.repository.jpa.OrderRepository;
import com.mechague.tacocloud.api.domain.Order;
import com.mechague.tacocloud.api.domain.User;
import com.mechague.tacocloud.api.properties.OrderProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private OrderProps orderProps;

    public OrderController(OrderRepository orderRepo, OrderProps orderProps){
        this.orderRepo = orderRepo;
        this.orderProps = orderProps;
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

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }



}
