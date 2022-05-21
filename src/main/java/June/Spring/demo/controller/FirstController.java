package June.Spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// MVC, template engine 으로 Spring boot 웹 만들기
// HTML 을 변환/렌더링 해서 웹에 내린다.
/*
@Controller                //web application 첫 진입점 , annotation 해준다 (여기 controller 있어요!)
public class FirstController {

    @GetMapping("Junehome")       // web 에서 /Junehome 으로 들어가면 아래의 메소드를 호출해준다 (URL 에 매칭)
    public String gogo(Model A){           //Model(스프링부트 기능) - A 에다 key, value 값 넣어둠
    A.addAttribute("data", "June's test"); // .addAttribute 속성 추가 ( key값 , value )
    return "firstmvc"; // ViewResolver -> templates + {ViewName} + .html  ( ViewName 맵핑)
                    // 찾아서 렌더링 하도록 시키는 명령어. return.
    }

@GetMapping("June02")
    public String gogo2(@RequestParam("promise") String whatever, Model B){  //@RequestParam("key") String A, Model B)
        // Parameter (String 인 whatever) 를 key 값 "promise" 로 가져온다.
      //  default 값으로 required 가 true 인 상태 -> URL 으로 parameter 넘겨야함 => /June02?promise(key값)=whatever(String)
       // (@RequestParam(value = "promise", required = true)  << 사실 이 모습인 것
      // 이렇게 value (여기선 key 로 쓰이는 모양새) 주고, required = true (default) 일때는, 기본적으로 URL로 parameter 을 넘긴다.
      // required = false 로 하면 안넘겨도 된다.  -> June02 -> Hello null
      // required = true  --> June02 -> Error page

    B.addAttribute("promise", whatever);   // Model 에 key값 과 value 를 담는다.
      return "secondmvc";
}

    // API 방식으로 Spring Boot 웹 만들기

    // 웹에 HTML 을 주지 않는다. 단지 data 만 그대로 내린다. (페이지 소스보기)
    // (Static 정적 컨텐츠는 HTML 을 그대로 내린 것)
    // View 가 없다
@GetMapping("Juneapi")
@ResponseBody             // API 방식 필수 ResponseBody , 빼먹지말자
    public String helloapi(@RequestParam("keey") String wht){       //MVC랑 똑같은데, 1. Model 을 안만듬
        return "JustString " + wht;                                 // 2. return 에 value 값 직접 출력 (String wht)
}                                                 // 3. return 이 template 에서 View name 찾아주는 view resolver 가 아니라
                                                 //    그냥 진짜 return, 출력하는 애다. (즉 template 필요없음)
                                                  // 4. @ResponseBody - http Body부에 data를 직접 넣는다.


// API 방식(Json 방식) 으로 data 를 가져오기 ( 객체 가져오기 )
// {"key" : "value"} 구조

@GetMapping("dataAPI")
@ResponseBody                                                 // (String 이 아니라)
public apiclass HA(@RequestParam("promise") String A){        // 객체, class 를 data type 으로 하는 method 만들거다
    apiclass h1 = new apiclass();                             // String 이 아니라 객체를 web 에 내릴거다.
    h1.setNick(A);  //parameter A 를 URL에서 받아서 넣음         //객체 instance 만들어주고 그걸로 그 클래스의 method 사용
//  h1.getNick();           //set 하고 get 하면 출력됨
    return h1;                                                // setNick 한 h1 객체를 return
}                                                             // {"promise" : "parameter" }  <= Json

    //GetMapping, ResponseBody 받는 method 위에다가 @@ 해줘야 함
    // 이 객체를 메소드 datatype 으로 쓸거다. 객체 class 는 밑과 같다.

    static class apiclass {                                  // static class 로 만들어 public class 안에서도 사용가능
        private String nick;      //private 로 해서 못건들게 해놓고 public 으로 연다. ( public 으로 해도 작동됨 )
                                 // alt+insert -> generator -> getter&setter 자동!
public String getNick(){                                      // 꺼낼때
    return nick;}
public void setNick(String nick){                             // 넣을때
    this.nick = nick;}
    }






}//
*/


