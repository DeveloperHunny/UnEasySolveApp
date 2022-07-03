package DevHunny.UneasySolve.domain;

import DevHunny.UneasySolve.api.MemberApiController;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter @Setter
public class Member {

    public Member(){}

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;


    @NotBlank @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank @Column(unique = true)
    private String nickname;

    @NotBlank
    private String address;

    //선택속성
    private Integer age;
    private Boolean sex; //남자가 1 여자가 0
    private Integer jobCode;
    private String phoneNumber;


    public Member(MemberApiController.CreateMemberRequest request) {
        email = request.getEmail();
        password = request.getPassword();
        nickname = request.getNickname();
        address = request.getAddress();

        age = request.getAge();
        sex = request.getSex();
        jobCode = request.getJobCode();
        phoneNumber = request.getPhoneNumber();

    }
}
