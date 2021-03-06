package com.mechague.tacocloud.api.repository.jpa;

import com.mechague.tacocloud.api.domain.Order;
import com.mechague.tacocloud.api.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findByZip(String deliveryZip);
    List<Order> readOrdersByZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
