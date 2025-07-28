package com.ll.demo01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // HTTP 요청을 처리할 때마다 사용
public class HomeController {
    @GetMapping("a")  // action method
    @ResponseBody  // return 값을 String으로 변환하여 응답본문으로 설정
    public  String  hello() {
        return "Hello";
    }

    @GetMapping("b")  // 브라우저에서 호출 가능
    @ResponseBody
    public String hello2() {
        System.out.println("Hello2"); // 터미널 창 출력
        return "안녕하세요";
    }

}
