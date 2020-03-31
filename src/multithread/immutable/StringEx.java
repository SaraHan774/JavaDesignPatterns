package multithread.immutable;

public class StringEx {

    public static void main(String[] args) {

        String string = "BAT";
        System.out.println(string.replace('B', 'C')); //CAT 출력
        System.out.println(string); //BAT 출력

        //새로운 스트링에 대입해야 한다.
        String newString = string.replace('B', 'C');
        //각자 스트링은 다른 해시코드 값을 출력한다. 서로 다른 객체이다.
        System.out.println(string.hashCode());
        // 65525
        System.out.println(newString.hashCode());
        //66486
    }
}
