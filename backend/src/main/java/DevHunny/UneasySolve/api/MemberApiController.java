package DevHunny.UneasySolve.api;

import DevHunny.UneasySolve.domain.Member;
import DevHunny.UneasySolve.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    @GetMapping("/api/members")
    public List<MemberDto> members(){
        return memberService.findAllMember().stream().map((member) -> new MemberDto(member.getId(), member.getEmail()))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/members/join")
    public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member(request);

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/members/login")
    public LoginMemberResponse loginMember(@RequestBody @Valid LoginMemberRequest request){
        Member member = new Member();
        member.setEmail(request.email); member.setPassword(request.password);
        Member result = memberService.tryLogin(member);

        if(result == null) return new LoginMemberResponse(null);
        else return new LoginMemberResponse(member.getEmail());
    }

    @Data
    @AllArgsConstructor
    class MemberDto{
        private Long id;
        private String email;
    }

    @Data
    @AllArgsConstructor
    static class LoginMemberResponse{
        private String email;
    }

    @Data
    static class LoginMemberRequest{
        private String email;
        private String password;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id){
            this.id = id;
        }

    }

    @Data
    public static class CreateMemberRequest{

        private String email;
        private String password;
        private String nickname;
        private String address;

        private Integer age;
        private Boolean sex;
        private Integer jobCode;
        private String phoneNumber;

    }


}
