package com.ll.demo01;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // @Autowired 생략 -> final 추가
public class ComponentA {
    private final ComponentB componentB;
    private final ComponentC componentC;
    private final ComponentD componentD;
    private final ComponentE componentE;


    public String action() {
        return "ComponentA action / " + componentB.getAction();
    }
}
