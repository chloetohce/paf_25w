package paf.workshop.paf_24w.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import paf.workshop.paf_24w.model.Order;
import paf.workshop.paf_24w.service.OrderService;



@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/add")
    public String orderForm(Model m) {
        m.addAttribute("order", new Order());
        return "order-form";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order o) {
        orderService.saveOrder(o);
        return "order-form";
    }
    
    
}
