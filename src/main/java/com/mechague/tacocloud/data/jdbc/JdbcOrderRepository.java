package com.mechague.tacocloud.data.jdbc;

import com.mechague.tacocloud.domain.Order;

public interface JdbcOrderRepository {
    Order save(Order order);
}
