package June.Spring.demo.clientdomain;

import javax.persistence.*;

@Entity
public class clientmemeber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long clientid;             //system 이 지정하는 임의의 id 만들거임
    private String clientname;       //business needs data 선언 해주고

    public Long getClientid() {
        return clientid;
    }

    public void setClientid(Long clientid) {
        this.clientid = clientid;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }
}