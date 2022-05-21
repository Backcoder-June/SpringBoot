package June.Spring.demo.clientservice;


import June.Spring.demo.clientdomain.clientmemeber;           // domain 은 매번 import 가 안된다. alt enter 도 안된다.
import June.Spring.demo.clientrepository.implementrepository;
import June.Spring.demo.clientservice.memberservice;         //이번엔 또 자동 import 가 안됬다.
import org.assertj.core.api.Assertions;                     //객체 생성할때, alt enter 확인해보자
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class memberserviceTest {

    memberservice All;

    public memberserviceTest(memberservice all) {
        this.All = all;
    }

    implementrepository implementrepository = new implementrepository();
    //clearman 가져오려면 store, clearman 만들어놓은 repository 를 import 해야겠지
    //그런데 여기서 new implementrepository 는 본체의 new implementrepository 랑은 다른 새로운 객체이다.
    // repository 에서 static 으로 해둬서 버티는 거지, 이것만 지워도 바로 문제가 생길 수 있다.
    //그래서 memberservice 에서부터 new 로 직접 안만들고, 생성자로 외부에서 정의하게하고
    //test 에서도 위에 두개 둘다 new 직접 안하고, @BeforeEach  로 거기서 new를 만들고 그걸 before 미리 넣어주는 방식을 택한다.
    // => 이게 Dependency Injection 이다. 직접 new 로 객체를 생성하지 않고, 외부에서 주입해서 가져오는 것.


    @AfterEach
    public void AfterEach(){
        implementrepository.clearman();         // 이거 clearman 하는 대상이 repositoy 를 해야 DB에서 지우면서 되는것
    }                                           // memberservice 를 대상으로 하면, 거긴 지울 store 가 없잖아


    @Test
    void 회원가입() {

        /** given  **/
        clientmemeber m1 = new clientmemeber();
        m1.setClientname("Kane");

        /**  when **/
        Long Result_join = All.join(m1);   // join 의 주기능은 save 다.
        // 추가적으로 임의로 id 를 return 하게 설정해뒀으니, 그걸 이용해 test 하고자함
        // id 를 받아야하니 (ctrl alt V 형식) Long A 로 받는다.
        /**  then **/
        clientmemeber c1 = All.findone(Result_join).get();   // memberservice 에서 fBID 로 찾은 value(id,name)을 get 한걸 c1 에넣음
        //Optional<clientmember> 로 했을때는 c1.getClientname 실행 X
        // get() 으로 Optional 벗기고, get()으로 받으니까 실행 O
        // Optional<clientmemeber> c2 = All.findone(A);       // 이거 차이점 보충!!!!!!!!!

        //근데 findone 도 test 해야할 method 인데 이걸로 test 를 해도 되는건가

        assertThat(m1.getClientname()).isEqualTo(c1.getClientname());
    }            //직접 뽑은 m1 name               // join 으로 save 한 m1 id로 뽑은 name
    // 즉 join 이 제대로 작동 했는지 test 가능

    //회원가입 - 중복거부 에 Exception 적용 되는지 test
    @Test
    public void 중복회원거르기() {

        //Given 
        clientmemeber m2 = new clientmemeber();
        m2.setClientname("Kane");

        clientmemeber m3 = new clientmemeber();
        m3.setClientname("Kane");
        
        //When
        // Exception => 1. try catch          2. throws
        /*All.join(m2);
        try {                                     //Exception 예상 ->1. try catch 로 잡아도 된다.
        All.join(m3);}
        catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }                                                                      //message 로 확인
                                           //Exception 종류나 message 가져올때, ctrl B 로 메소드로 이동
*/
        //2. throws => assertThrows
        All.join(m2);

        IllegalStateException E = assertThrows(IllegalStateException.class, () -> All.join(m3));
                                  // parameter 형식이 class 요구하므로 .class Rambda
        // Illegal Exception 반환 -> m3 이 join 할 때
        assertThat(E.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
    @Test
    void findmembers() {
    }

    @Test
    void findone() {
    }
}