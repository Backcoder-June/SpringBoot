package June.Spring.demo.clientrepository;

import June.Spring.demo.clientdomain.clientmemeber;  //domain class 를 import 해주고

import java.util.List;
import java.util.Optional;             //Optional util import (자동)

public interface memberrepository {               // interface 로 형식 짜주기
    clientmemeber save(clientmemeber member);   // 1. save  //clientmember class인 save 는
                                                            // clientmember datatype인 member 를 parameter 로 취함
                                                // 메소드 save 를 저장기능으로 나중에 구현함

    Optional<clientmemeber> findByID(Long id);  // 2. id로 찾기
                                                // clientmemeber class 를 데이터타입 으로하는 메소드 findByID 는 Optional 이다
                                                // Long id 를 parameter 로 취함

    Optional<clientmemeber> findByName(String name);  // 3. name 으로 찾기
    //Optional 은 null 이 나올 가능성이 있을 경우 써준다.

    List<clientmemeber> findAll();                   // 4. 찾은 모든 list 출력
                                           //findAll 이라는 메소드는 clientmember datatype 을 취하고, List 이다.




}
