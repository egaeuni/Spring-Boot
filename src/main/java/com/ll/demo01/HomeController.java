package com.ll.demo01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // HTTP 요청을 처리할 때마다 사용
public class HomeController {
    @GetMapping("a")  // action method
    @ResponseBody  // return 값을 String으로 변환하여 응답본문으로 설정
    public  String  hello(
            String age, // 통신에 사용되는 텍스트는 모두 String
            String id
    ) {
        return "%s번 사람의 나이는 %s살 입니다.".formatted(id, age);
    }

    @GetMapping("b")  // 브라우저에서 호출 가능
    @ResponseBody
    public String plus(
            @RequestParam("a") String num1Str, // 요청 파라미터 이름 a를 num1Str에 매핑
            @RequestParam("b") String num2Str
    ) {
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        System.out.println("num1 = " + num1); // 터미널 창 출력
        System.out.println("num2 = " + num2);
        return "a + b = %d".formatted(num1 + num2);
    }

}
