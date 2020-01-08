package com.mechague.tacocloud.repository.jpa;

import com.mechague.tacocloud.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findByZip(String deliveryZip);
    List<Order> readOrdersByZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
