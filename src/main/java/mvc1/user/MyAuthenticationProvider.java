package mvc1.user;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String loginId = authentication.getName();
		String passwd = authentication.getCredentials().toString();
		
		System.out.println("================================");
		System.out.println("test>>>>>>"+loginId+"pass>>>>>>>"+passwd);
		System.out.println("================================");
		
		return authenticate(loginId, passwd);
	}

	public Authentication authenticate(String loginId, String passwd) throws AuthenticationException {
		MemberDTO member=memberMapper.selectByLoginId(loginId);
		if (member == null) return null;
		if (member.getPassword().equals(passwd) == false) return null;

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("ROLE_"+member.getAuthor());
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" +member.getAuthor()));


		return new MyAuthenticaion(loginId, passwd, grantedAuthorities,member);

	}


	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        MemberDTO member;

        public MyAuthenticaion (String loginId, String passwd, List<GrantedAuthority> grantedAuthorities,MemberDTO member ) {
            super(loginId, passwd, grantedAuthorities);
            this.member = member;
        }

        public MemberDTO getMember() {
            return member;
        }

        public void setMember(MemberDTO member) {
            this.member = member;
        }
    }



}
