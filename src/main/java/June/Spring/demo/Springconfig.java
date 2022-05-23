package June.Spring.demo;


import June.Spring.demo.clientrepository.implementrepository;
import June.Spring.demo.clientrepository.memberrepository;
import June.Spring.demo.clientservice.memberservice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration                                  // Config class 에 @Configuration 주고
public class Springconfig {               //Service 의 @service @autowired / Repository의 @repository  지워준다

    // memberservice 등록하기
    @Bean                         // 여기 Spring Bean 있어요. 이거 Container 에 등록할게요!
    public memberservice memberService(){
        return new memberservice(memberRepository());   //**여기서 memberservice 에 인자값으로 memberrepository 의 객체를
    }                                                  // 넣어줌으로써, service 와 repository 를 연결(Wired) 시키는 것

    // memberrepository (interface) 등록하기
    @Bean
    public memberrepository memberRepository(){
        return new implementrepository();              // interface 는 new 가 안되므로, return 은 구현체 class repository
    }
}
// service, repository(interface) 를 Bean 으로 올리고,
// service 인자값으로 repository 를 줘서 연결 시킨다.

// 일반적이지 않은 controller, service, repository 상황이거나
// ** 상황에 따라 "구현 class" 를 변경해야 할 여지가 있는 경우, 이 @Bean(Spring config) 방법으로 DI 를 해야
// 다른 코드의 변경사항 없이 변경 가능 ( Ex. Memory memberrepository => Db memberrepository 로 바꿀때
// @Bean public M m() {return new (memory=>Db) memberrepository }  로 바로 바꿔줄 수 있다.


