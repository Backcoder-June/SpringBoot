package June.Spring.demo.controller;

import June.Spring.demo.clientservice.memberservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import June.Spring.demo.clientdomain.clientmemeber;
import org.springframework.web.bind.annotation.PostMapping;

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

     //이제 memberController 에 Service 랑 repository 연결은 DI 로 다 해놨고,
     // Homecontroller 랑 home html 만들어서 기본 home 화면까지는 만들어졌다.

    // 다음은, home 에서 회원등록 클릭했을 때 나오는 등록 page 를 만들어보자

    @GetMapping("/members/new")           // 등록 눌렀을때 나오는 html 에서 설정해둔 Url.
    public String Joining(){              // 여기로 접속하면
        return "members/Joining";          // Joining html 을 내려서 회원등록 page 를 띄운다.
    }

    // 회원 등록 페이지 까지는 이제 뜬다. 여기까진 사실상, home controller, home html 이랑
    // 등록하는 page html 이 다한거다.
    // 실제로 등록을 가능하게 하는 logic 을 내가 만들어놓은 controller, service, repository 가 수행하는 거다.
    // 이제, name 입력하고 등록 을 눌렀을 때, 실제로 등록이 되게하는 걸 만들자.
    @PostMapping("/members/new")       //같은 Url 에서 PostMapping 을 통해 다른 작업을 수행하게 한다. (html 과 연계)
    public String creating(memberform form){               //다른 작업 : creating 이라는 메소드를 실행시키는 것
                                                           //얘는 따로 만들어 놓은 memberform 이라는 객체를 para 로 취한다.
                                                           // memeberform 에는 "name" 이 getter setter 되있다.
                                  // 이 "name" 을 갖는 새로 생성된 method 에 미리 만들어놓은 domain 에서 받은 name 을 넣어주면
                                  // 내가 만들어 놓은 거랑, 이 새로운 method 의 name 이 연결된다.

        clientmemeber member = new clientmemeber();        // domain 객체 소환
        member.setClientname(form.getName());              // domain 객체에 setClientname 의 인자값에 ("June" 이 아니라)
                                                           // 새로 생성한 form 메소드의  getName 을 넣어줌으로서
                                                        // 새 메소드의 name => domain 의 member 정보 name 으로 들어가게된다.
                                                     //이 새 메소드에서 "name" 은 postmapping 을 통해 html과 연결되어있다.
                                                     // html 에서 "name" 값이 지정된 부분이 이 새 메소드의 name 과 연결
                                                   // 즉 html name - 새 메소드 name - 도메인 name 이 연결되는 것

        System.out.println("member = " +member.getClientid()+""+ member.getClientname());  // 잘 들어갔는지 확인용
                                      // id 값 null 로 나온다. 이것도 뽑아보자

        // 이제 web 에서 name 을 치면 그게 domain name 이 되게는 만들었고,
        // 실제로 등록을 누르면, map 에 저장이 되게 만들자

        memberservice.join(member);            // memberservice 에서 구현해둔 join 메소드만 따오면 된다.
                                               // 이 인자값으로 domain 에 member data 를 넣으면 끝
        return "redirect:/";                   // "등록" 누르면 위의 메소드들 실행되서 join 까지 완료되고
    }                                         //마지막에 return 으로 redirect 해서 "/" - home 으로 보낸다.


}
