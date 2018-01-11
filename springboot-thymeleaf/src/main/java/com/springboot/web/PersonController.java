package com.springboot.web;

import com.springboot.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 13:18
 * @see:
 * @since:
 */
@Controller
public class PersonController extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("title", "Hello Thymeleaf!");
        return "index";
    }

    @GetMapping("form")
    public String form(Model model) {
        model.addAttribute("person", new Person("bo", 16));
        return "form";
    }


    @PostMapping("save")
    public String save(@Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }
}
