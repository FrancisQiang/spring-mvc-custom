package com.lgq.handler;

import com.lgq.annotation.Controller;
import com.lgq.annotation.RequestMapping;
import com.lgq.model.ModelAndView;

/**
 * @author lgq
 * @date 2019/10/25
 */
@org.springframework.stereotype.Controller
@Controller
public class HelloWorldHandler {

    @RequestMapping(value = "/helloWorld")
    public ModelAndView helloWorld() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAttribute("hello!!!", "....");
        return modelAndView;
    }

}
