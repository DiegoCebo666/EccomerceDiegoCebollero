package com.eccomerce.diegocebollero.eccomerce.Model.Support;

import com.eccomerce.diegocebollero.eccomerce.Model.OrderProduct;

public class EditDeleteOrders {
    private OrderProduct edit;
    private OrderProduct delete;

    public EditDeleteOrders(){}

    public EditDeleteOrders(OrderProduct edit, OrderProduct delete){
        this.edit = edit;
        this.delete = delete;
    }

    public OrderProduct getEdit() {
        return edit;
    }
    public void setEdit(OrderProduct edit) {
        this.edit = edit;
    }
    public OrderProduct getDelete() {
        return delete;
    }
    public void setDelete(OrderProduct delete) {
        this.delete = delete;
    }
}
