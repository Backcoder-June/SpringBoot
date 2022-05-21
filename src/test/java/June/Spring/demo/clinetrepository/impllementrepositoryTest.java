package June.Spring.demo.clinetrepository;

import June.Spring.demo.clientrepository.implementrepository;
import June.Spring.demo.clientrepository.memberrepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import June.Spring.demo.clientdomain.clientmemeber;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class impllementrepositoryTest {

    implementrepository repository = new implementrepository();         // 원본class A = new 원본class();
                                                                         //원본class 를 불러오기!

    @AfterEach                                            //AfterEach - method 가 하나하나 실행이 끝날때마다
    public void afterEach(){                              // 이 메소드를 실행하겠다!
        repository.clearman();}                                   //원본에 clearman 만들어놓고 가져옴

@Test                     // @Test 해주고
//Test 할거 main 에서 처럼 하는것
    public void save() {                       // void 로 해주네. 보충필요
clientmemeber m0 = new clientmemeber();    // 원본 => public clientmember save(clientmember member)
                                           //save 용 clientmember 객체 생성  (그냥 domain 객체 생성하는거라고 봐도 될듯)
m0.setClientname("June");                // clientmember 객체에 name 넣어주고

repository.save(m0);                     //repository = 원본 객체의 메소드 save를 이용 -> m0 저장 => id 생성, June 저장(Map)

    //이제 저장은 했고 뽑아서 맞나 보겠다. (Boolean)

    clientmemeber c1 = repository.findByID(m0.getClientid()).get();  //clt + alt V -> 형식 완성시켜줌
                                                      //원래는 Optional 형식 -  .get() 으로 한번 까서 뽑을 수 있다.
    System.out.println("(c1==m0) = " + (c1==m0));  //soutv 로 value 값 boolean // 뽑은 c1 값이 save 된 m0과 같은지
    //sout 보다는 Assertion 많이 쓴다
    //1.Assertions - junit
    Assertions.assertEquals(m0, c1);   // ( Expected , Actual )

   //2. Assertions - assertj (이거 많이 쓰는 추세)
    org.assertj.core.api.Assertions.assertThat(m0).isEqualTo(c1);   // alt+Ent 로 static import 하면
    assertThat(c1).isEqualTo(m0);                                //이렇게 assertthat 으로 바로 사용가능
}
 //이게 save 가 잘 작동하는지 확인하는 test.

@Test
    public void findByName() {
    clientmemeber m1 = new clientmemeber();
    m1.setClientname("Tom");
    repository.save(m1);                     //마찬가지로 해서 save 까지 해서 Map 에 넣어주고
                                            //2까지 만들어 test 해보자

        clientmemeber m2= new clientmemeber();
        m2.setClientname("Luna");
        repository.save(m2);

    clientmemeber c2 = repository.findByName("Luna").get(); // c2 에 Name "Luna" 로 찾은 member 의 결과값을 넣음

    //넣어놓고 c2 에 담긴 값이 m2에서 set된 값이랑 맞는지 확인
    // save 하면서 sequence 도 주어진거라서 sequence 랑 Luna 가 합쳐진 c2 즉 clientmember 를 비교하는것.

    assertThat(c2).isEqualTo(m2);
    //assertThat(c2).isEqualTo(m1);      // m1 넣으면 Test failed

}

@Test
    public void findAll(){
    clientmemeber m3 = new clientmemeber();
    m3.setClientname("Black");
    repository.save(m3);

    clientmemeber m4 = new clientmemeber();
    m4.setClientname("White");
    repository.save(m4);

    List<clientmemeber> c3 = repository.findAll();

    assertThat(c3.size()).isEqualTo(2);            //Expected : 1 / Actual : 2
                                                             //size 로 검증
    }
}
