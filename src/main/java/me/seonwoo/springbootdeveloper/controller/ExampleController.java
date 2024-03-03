package me.seonwoo.springbootdeveloper.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller // 컨트롤러라는 것을 명시적으로 표시
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) { // 뷰, 즉 HTML 쪽으로 데이터를 넘겨주는 모델 객체
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson); // Person라는 키에 정보 저장
        model.addAttribute("today", LocalDate.now());

        return "example"; // 클래스에 붙은 애너테이션이 @Controller이므로 뷰 이름을 반환, example.html라는 뷰 조회
    } // 스프링 부트가 @Controller를 보고 반환 값의 이름을 가진 뷰 파일을 찾아라고 인식,
      // response/templates 디렉터리에서 example.html을 찾아 웹 브라우저에 해당 파일 보여줌
      //
    @Setter
    @Getter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
