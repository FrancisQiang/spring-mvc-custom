package com.lgq.handler;

import com.lgq.annotation.Controller;
import com.lgq.annotation.RequestMapping;
import com.lgq.model.ModelAndView;
import org.springframework.stereotype.Component;

/**
 * @author lgq
 * @date 2019/10/25
 */
@Component
@Controller
public class HelloWorldHandler {

    @RequestMapping(value = "/helloWorld")
    public ModelAndView helloWorld() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAttribute("hello!!!", "World!!!");
        return modelAndView;
    }

}
