package ada.synoptic.project.membershipsystem;

import ada.synoptic.project.membershipsystem.rest.MemberController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MembershipSystemApplicationTests {

    @Autowired
    private MemberController memberController;

    @Test
    public void contextLoads() {
        assertThat(memberController).isNotNull();
    }

}
