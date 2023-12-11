package hello.core.singleton;

public class SingleTonService {

    // 싱글톤 서비스 인스턴스 1개 생성
    private static final SingleTonService instance = new SingleTonService();

    // 2개 이상 싱글톤 인스턴스 객체 생성 막기 (Private + 생성자)
    private SingleTonService() {

    }

    // 싱글톤 서비스 호출
    public static SingleTonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
