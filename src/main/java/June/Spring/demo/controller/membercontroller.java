package June.Spring.demo.controller;

import June.Spring.demo.clientservice.memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller                      // annotation -> Spring container 가 관리중
public class membercontroller {             // membercontroller 라는 객체로 생성 => Spring Bean  관리

    // memberservice 를 가져와 쓰고싶다

//   private final memberservice memberservice = new memberservice();

    // 이렇게 new 로 생성해서 받아 쓸수도 있지만,
    // membercontroller 말고도 다른 많은 controller 들이 memberservice 를 가져다 쓸 수 있으므로
    // 각 controller 마다 new 로 생성하면 memberservice 는 각 controller 마다 다 다른 memberservice 인 것이 된다.
    // 그러므로, Spring container 에 정식으로 등록을 하고 쓰일 수 있게 만들자 -> 딱 하나의 memberservice 로만 등록이 됨. (싱글톤)
    // + 부가적인 효과들까지

    private final memberservice memberservice;                // 일단 선언만 해주고, (private final)
//(alt insert -> generator -> constructor) 생성자를 만들어준다.
    @Autowired                                                // 생성자에 Autowired 를 준다.
    public membercontroller(June.Spring.demo.clientservice.memberservice memberservice) {
         this.memberservice = memberservice;
    }   //Autowired -> Spring이 memberservice 를 Container 에있는 memberservice 를 가져다 연결을 시켜준다.
    // 근데 현재로서는 memberservice 는 그냥 순수한 class 일 뿐이라, container 가 얘를 찾아서 갖고있을 방법이 없다.
    // => 1. memberservice 로 가서  class에 @Service 를 해준다. +@Autowired -> Spring 이 인식하고 container에 등록해준다.
    // => 2. 구현체 repository class 로 가서 @Repository 를 해준다. -> Spring 이 인식할 수 있도록



}
