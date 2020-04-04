package vn.com.fobelife.component.training.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/training")
@Slf4j
public class TrainingController {

    @GetMapping
    public String getTraining(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** training *****");
        return "training";
    }
}
