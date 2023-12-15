package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)
        public void setNoBean1(Member NoBean1){
            System.out.println("NoBean1 = " + NoBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member NoBean2){
            System.out.println("NoBean2 = " + NoBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> NoBean3){
            System.out.println("NoBean3 = " + NoBean3);
        }

    }
}
