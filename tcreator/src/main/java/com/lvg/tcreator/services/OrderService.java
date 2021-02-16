package com.lvg.tcreator.services;

import com.lvg.tcreator.models.OrderDTO;

public interface OrderService {
    OrderDTO loadFromFile(byte[] file);
}
