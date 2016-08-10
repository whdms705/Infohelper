package mvc1.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired MemberMapper memberMapper;

   

    public static MemberDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticationProvider.MyAuthenticaion)
            return ((MyAuthenticationProvider.MyAuthenticaion) authentication).getMember();
        return null;
    }

    public static void setCurrentUser(MemberDTO member) {
        ((MyAuthenticationProvider.MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setMember(member);
    }
}
