

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mvc1.user.MemberDTO;
import mvc1.user.MemberMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml"})
public class TestUserMapper {
	@Autowired MemberMapper userMapper;
	@Test
	public void test() {
        // userMapper가 autowired되었는지 확인
        assertNotNull(userMapper);

        // id=1 사용자의 이름이 "admin" 맞는지 확인
        MemberDTO user = userMapper.selectByLoginId("201132036");
        assert(user == null);
        assertEquals("조은웅", user.getName());

        // loginId 값이 "hongGilDong" 인 사용자가 없는 것 확인
        user = userMapper.selectByLoginId("hongGilDong");
        assertNull(user);

        

	}

}
