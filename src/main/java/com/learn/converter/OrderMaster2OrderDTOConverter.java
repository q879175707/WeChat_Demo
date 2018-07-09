package com.learn.converter;

import org.springframework.beans.BeanUtils;

import com.learn.dataobject.OrderMaster;
import com.learn.dto.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 2017-06-11 22:02
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
