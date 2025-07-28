package com.ll.demo01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home2")
public class Home2Controller {
    @Autowired // 필요한 의존 객체의 타입에 해당하는 빈을 찾아 주입 (Controller, Component에만 사용 가능)
    private ComponentA componentA;

    // http://localhost:8080/home/action/
    @GetMapping("/action")
    @ResponseBody
    public String action() {
        return componentA.action();
    }
}
