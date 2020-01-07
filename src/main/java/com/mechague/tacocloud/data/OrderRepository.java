package com.mechague.tacocloud.data;

import com.mechague.tacocloud.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
