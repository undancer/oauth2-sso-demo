package com.undancer.id.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by undancer on 2017/5/24.
 */
@Controller
@RequestMapping(path = "/oauth/confirm_access")
public class AccessConfirmController {
    @GetMapping
    public ModelAndView getAccessConfirmation() {
        return new ModelAndView("access_confirmation");
    }
}
