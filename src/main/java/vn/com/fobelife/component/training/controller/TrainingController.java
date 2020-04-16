package vn.com.fobelife.component.training.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.training.service.TrainingService;

@Controller
@RequestMapping("/training")
@Slf4j
public class TrainingController {

    @Autowired
    private TrainingService service;

    @GetMapping
    public String getCourse(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** course *****");
        return "course";
    }

    @GetMapping("/{code}")
    public String getTraining(@PathVariable String code, HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** training *****");
        req.setAttribute("courseCode", code);
        return "training";
    }

    @PostMapping("/import")
    public String importProduct(@RequestParam MultipartFile file) {
        log.info("***** IMPORT PRODUCT *****");
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            service.importTraining(content);
        } catch (Exception e) {
            log.error("***** Import products", e);
        }
        return "redirect:/training";
    }

    @PostMapping
    public String submitTraining(HttpServletRequest req, HttpServletResponse rep) {
        log.info("***** Submit training *****");
        try {
            List<String> qCodes = service.getQuestionCode();
            List<String> aCodes = qCodes.stream().filter(q -> StringUtils.isNotBlank(req.getParameter(q)))
                                                 .map(q -> req.getParameter(q))
                                                 .collect(Collectors.toList());
            service.submitTraining(aCodes);
        } catch(Exception e) {
            log.error("***** Submit training *****", e);
        }
        return "redirect:/training";
    }
}
