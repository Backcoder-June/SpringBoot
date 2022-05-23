package June.Spring.demo.clientservice;

import June.Spring.demo.clientrepository.implementrepository;
import June.Spring.demo.clientrepository.memberrepository;
import June.Spring.demo.clientdomain.clientmemeber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

//@Service                                     //class memberservice 를 Spring container 에 등록시키기
public class memberservice {                 // Component Scanning 말고, Spring Config 로 직접 등록시키는 방법 => Config파일
    // 회원가입 business logic 만들기
    // join

    /**   여기부터 depencency injection  **/
    private final memberrepository A;          // interface repository 를 연결시키는데 -> 이걸 구현체 repository 로 해주는듯
    //private final <<                         // class repository 로 해도 돌아가긴 한다.
 //   @Autowired
    public memberservice(memberrepository A) {
        this.A = A;
    }



    public void clearman(){

    }

    public Long join(clientmemeber member) {     //domain type 의 member data(id,Name) 를 주기,
//Long 은 domain 에서 client id datatype 이였고, 여기서 임의로 id 뽑을거라 Long,

        //중복이름 가입불가 business logic 추가

/*        Optional<clientmemeber> result = A.findByName(member.getClientname());
         result.ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
*/
        // A.findByName(member.getClientname()) 에서 값이 반환이 되는거니까 여기 바로 .ifPresent 해주기
/*        A.findByName(member.getClientname()).ifPresent
                (m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
*/
        // logic 이 이렇게 꼬리를물고 너무 길어질 경우 => method 로 만들어주는게 가독성 좋다 -> ctr alt M
        denyingsamename(member);         //중복이름 가입불가
        //이런식으로 주석 달아주기.
        // 만든 메소드에 ctl 클릭(B) 하면 해당 method 로 이동

        A.save(member);                     // A (전체 repository 의 객체)에 save 메소드로 store map에 putin 하겠다.

        return member.getClientid();                                 //임의로 id 만 가져오겠다.
    }

    private void denyingsamename(clientmemeber member) {
        A.findByName(member.getClientname()).ifPresent
                (m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
// 중복이름 join 막는 business logic 만들기 - 게임 id 만들때 쓰일 logic

    //전체 회원 조회하는 기능
    public List<clientmemeber> findmembers() {
        // List<clientmemeber> check = A.findAll();  반환 type 확인 => list clientmember 구나 => return 으로 뽑자
        return A.findAll();

    }

    // ID 로 회원 조회 기능
    public Optional<clientmemeber> findone(Long memberid) {
        return A.findByID(memberid);
    }


}
