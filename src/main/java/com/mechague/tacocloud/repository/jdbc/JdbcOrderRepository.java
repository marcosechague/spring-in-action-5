package com.mechague.tacocloud.repository.jdbc;

import com.mechague.tacocloud.domain.Order;

public interface JdbcOrderRepository {
    Order save(Order order);
}
