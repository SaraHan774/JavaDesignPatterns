package multithread.immutable;


final class UserInfo{
    private final StringBuffer info;
    public UserInfo(String name, String address){
        this.info = new StringBuffer("info name = " + name + " address = " + address);
    }

    public StringBuffer getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "info=" + info +
                '}';
    }
}

public class NoSetterEx {

    public static void main(String[] args) {
        //User Info 클래스는 왜 Immutable 이 아닌가?
        UserInfo userInfo = new UserInfo("Sara", "Huam-dong");
        System.out.println(userInfo);

        System.out.println(userInfo.getInfo().replace(0, 2, "CHANGED"));
        //User Info 의 필드가 StringBuffer 로 선언되어 있기 때문에 여전히 수정 될 수 있으므로.
        System.out.println(userInfo);
    }
}
