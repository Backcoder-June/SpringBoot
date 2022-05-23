package June.Spring.demo.clientrepository;

import June.Spring.demo.clientdomain.clientmemeber;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository                        // Spring container 에게 넣어달라는 신호
public class implementrepository implements memberrepository{       // interface implements 하고 method 도 다 import 한다
                                                                       // alt enter 활용

    private static Map<Long, clientmemeber> store = new HashMap<>();  //util Map import
    //Map< key, value > A = new HashMap<>(); 활용해서 save 기능을 만들거다.
    // 일단 store 라는 Map 을 만들어 둔다.
    // id 가 key 값으로 들어갈 거기 때문에 Long, 그에 따른 value 는 clientmember 객체가 들어갈거다 (id랑 name 이 있는 객체)
    private static long sequence = 0L;  // sequence 라는 변수를 만들어 이거로 id 값을 줄거다.
    // Long 은 generic 들어갈 땐, 원시 data 안되서 reference data 로 만든 Long, / 그냥 변수값 type 은 일반 long /
    // 0부터 시작해 ++ 로 1,2,3,4, id 줄거다
    // 여기까지가 save 랑 id 만들기 위한 basic 깔기

    public void clearman(){
        store.clear();}                     // 이거 여기다 만들어놓아야 test 에서 AfterEach 에 쓸수있다

    @Override
    public clientmemeber save(clientmemeber member) {
        member.setClientid(++sequence);   //객체 member 의 id (long) 를 ++sequnece 로 지정  //Set
        store.put(member.getClientid(), member);       //Map 에 id, member 다 넣어둠       //Get
        // store(Map) 기능 활용 -> id 를 key값으로 , member를 value 로 준다.
        //즉, Map(store) 에 id, member data 들을 .put(key, value) 으로 입력하면서 그게 "저장" 의 기능을 하는 것

        return member;                    // id 와 member 가 지정된 객체 member 값 반환
    }                                    // save 라는 method 니까 return 값이 있어야함

    @Override
    public Optional<clientmemeber> findByID(Long id) {     // 1. clinetmember 라는 data type을 취해야 함
                                                           // 2. Null 값이 나올수 있어서 Optional 주고 싶음
                                                           // => Optional<clinetmember> 이렇게 해주면 됨

        // return store.get(id);           // store(Map) 을 이용해 id 를 찾는다
         return Optional.ofNullable(store.get(id));  // Map 에서 .get(key) => value 값 반환 즉 여기선 member
                                                       // id(key) 로 member 찾는 메소드
         // Optional.ofNullable 로 감싸서 Null 값이 있을 때도 가능하도록 만든다. (요즘 추세)
    }

    @Override
    public Optional<clientmemeber> findByName(String name) {
        return store.values().stream()                //store(Map) 에 있는 values() <- 즉 member data 중에서 filter 돌린다
                .filter(clientmemeber -> clientmemeber.getClientname().equals(name)).findAny();
    }      //filter 돌려서 clientname 하고 parameter 인 name 이 일치하면 return 한다.
           // 즉 name 만으로 member 찾기

    @Override
    public List<clientmemeber> findAll() {
        return new ArrayList<>(store.values());   //ArrayList 로 Map 에 values, 즉 member data 쭉 반환
    }
}
