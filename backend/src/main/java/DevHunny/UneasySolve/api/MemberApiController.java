package DevHunny.UneasySolve.api;

import DevHunny.UneasySolve.domain.Member;
import DevHunny.UneasySolve.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberApiController {

    private final MemberService memberService;

    @Autowired
    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Data
    @AllArgsConstructor
    class MemberDto{
        private Long id;
        private String email;
    }

    @GetMapping("/api/members")
    public List<MemberDto> members(){
        return memberService.findAllMember().stream().map((member) -> new MemberDto(member.getId(), member.getEmail()))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/members/join")
    public CreateMemberResponse joinMember(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setEmail(request.email);
//        member.setPassword(request.password);
//        member.setNickname(request.nickname);
//        member.setAddress(request.address);
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id){
            this.id = id;
        }

    }

    @Data
    @AllArgsConstructor
    static class CreateMemberRequest{
        private String email;
//        private String password;
//        private String nickname;
//        private String address;
    }


}
