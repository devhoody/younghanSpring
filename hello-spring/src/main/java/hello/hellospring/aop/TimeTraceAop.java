package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop사용 애노테이션
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..)) &&  !target(hello.hellospring.SpringConfig)") // 패키지 하위에 모두 적용하라는 뜻 (hello.hellostrping 하위 클래스 전부)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START :" + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long tiemMs = finish - start;
            System.out.println("FINISH :" + joinPoint.toString() + " " + tiemMs + "ms");

        }
    }

}
