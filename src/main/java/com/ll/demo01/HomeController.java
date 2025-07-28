package com.ll.demo01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            @RequestParam("a") int num1, // 요청 파라미터 이름 a를 num1Str에 매핑
            @RequestParam("b") int num2,
            // Jackson은 컨트롤러 메서드가 Java 객체를 반환할 때, 해당 객체를 JSON 문자열로 변환해 응답 본문으로 보냄 -> String이 아닌 int 사용 가능
            // 단, @ResponseBody 또는 @RestController가 있어야 작동
            @RequestParam(name = "c", defaultValue = "0") int num3
    ) {
        System.out.println("num1 = " + num1); // 터미널 창 출력
        System.out.println("num2 = " + num2);
        System.out.println("num3 = " + num3);
        return "a + b + c= %d".formatted(num1 + num2 + num3);
    }

    @GetMapping("c")
    @ResponseBody
    public String c(
            boolean married
    ) {
        return married ? "결혼" : "미혼";
    }

    @GetMapping("d")
    @ResponseBody
    public String d(
            Boolean married
    ){
        if ( married == null )  return "정보를 입력해주세요.";
        return married ? "결혼" : "미혼";
    }

    public  static  class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @GetMapping("person1")
    @ResponseBody
    public String person(String name, int age){
        Person person = new Person(name, age);

        return  person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person2(Person person){
        return  person.toString();
    }

    @GetMapping("e")
    @ResponseBody
    public int e(){
        int age = 10;

        return age;
    }

    @GetMapping("f")
    @ResponseBody
    public boolean f(){
        boolean married = true;

        return married;
    }

    @GetMapping("g")
    @ResponseBody
    public Person g(){
        Person person = new Person("gaeun", 22);

        return  person;
    }

    @GetMapping("h")
    @ResponseBody
    public int[] h(){
        int[] arr = new int[] {10, 20, 30};

        return arr;
    }

    @GetMapping("i")
    @ResponseBody
    public List<Integer> i(){
        List<Integer> arr = List.of(10,20,30);

        return arr;
    }

    @GetMapping("j")
    @ResponseBody
    public Map<String, Object> j(){
        Map<String, Object> person = new HashMap<>();
        person.put("age", 22);
        person.put("name", "gaeun");

        return person;
    }
}
