package com.ll.demo01;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor // 모든 필드를 초기화하는 생성자
    public  static  class Person {
        private String name;
        private int age;
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

    @AllArgsConstructor
    @Getter
    @Builder  // new 대신 빌더 패턴으로 객체를 생성할 수 있게 해줌 (Lombok 제공)
    @ToString  // 객체의 내부 상태를 보기 쉽게 문자열로 출력
    @EqualsAndHashCode(onlyExplicitlyIncluded = true) // equals, hashCode 자동 생성 (어떤 객체가 같은지 다른지 확인)
    // include가 붙은 필드만 계산에 포함
    public static class Post {
        @ToString.Exclude
        @JsonIgnore // JSON 변환 시 특정 필드 제외(무시)
        @EqualsAndHashCode.Include
        private Long id;
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;
        @Builder.Default
        private String subject = "제목 입니다.";
        private String body;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getPost() {
        List<Post> posts = new ArrayList<>() {{
            add(new Post(1L, LocalDateTime.now(), LocalDateTime.now(), "제목1", "내용1"));
            add(new Post(2L, LocalDateTime.now(), LocalDateTime.now(), "제목2", "내용2"));
            add(new Post(3L, LocalDateTime.now(), LocalDateTime.now(), "제목3", "내용3"));
            add(new Post(4L, LocalDateTime.now(), LocalDateTime.now(), "제목4", "내용4"));
            add(new Post(5L, LocalDateTime.now(), LocalDateTime.now(), "제목5", "내용5"));
        }};

        return posts;
    }

    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPost2() {
        List<Post> posts = new ArrayList<>() {{
            add(
                    Post
                            .builder()
                            .id(1L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .subject("제목 1")
                            .body("내용 1")
                            .build()
            );
            add(
                    Post
                            .builder()
                            .id(2L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .subject("제목 2")
                            .body("내용 2")
                            .build()
            );
            add(
                    Post
                            .builder()
                            .id(3L)
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())

                            .body("내용 3")
                            .build()
            );
        }};

        return posts;
    }

    @GetMapping("/posts/1")
    @ResponseBody
    public Post getPost3() {
        Post post = Post
                .builder()
                .id(1L)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .subject("제목 1")
                .body("내용 1")
                .build();

        System.out.println(post);

        return post;
    }

    @SneakyThrows
    @GetMapping("/posts/2")
    @ResponseBody
    public Post getPost4() {
        Post post = Post
                .builder()
                .id(2L)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .subject("제목 2")
                .body("내용 2")
                .build();

        Thread.sleep(5000); // 5초 있다가 응답
        System.out.println(post);

        return post;
    }
}
