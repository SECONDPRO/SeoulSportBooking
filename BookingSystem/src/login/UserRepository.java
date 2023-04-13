package login;

import adminPage.AdminView;
import mypage.MyPageView;
import sportsFacArea.BookingView;
import userSys.UserInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {
    private static List<UserInfo> info;
    UserView uv;
    AdminView adminView;
    UserView userview;

    static {
        info = new ArrayList<>();
    }

    // userInfo load 함수
    public static void loadSaveFile() {
        try (FileInputStream fis
                     = new FileInputStream(
                "BookingSystem/src/saveFile/userInfo.sav")) {

            // 객체를 불러올 보조스트림
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<userSys.UserInfo> userInfo = (List<UserInfo>) ois.readObject();
            for (UserInfo userObj : userInfo) {
                info.add(userObj);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** 로그인시 입력한 값이 save 파일의 정보와 비교하여
     *  알맞은 회원정보인지 확인하고 맞을 경우 다른 페이지로
     *  정보와 함께 이동시키는 기능
     * @param inputId 입력한 아이디
     * @param inputPwd 입력한 비밀번호
     */
    public void loginValidate(String inputId, String inputPwd) {

        UserInfo userInfo;
        try {
            userInfo = info.stream().filter(obj -> obj.getUserId().equals(inputId)).collect(Collectors.toList()).get(0);
            uv = new UserView();
            if (userInfo.getUserId().equals("admin") && userInfo.getUserPwd().equals("admin")) {
                System.out.println("관리자 계정으로 로그인 하였습니다.");
                adminView = new AdminView();
                adminView.adminMenu();
            } else if (userInfo.getUserId().equals(inputId) && userInfo.getUserPwd().equals(inputPwd)) {
                System.out.println("");
                System.out.printf("[%s]님 환영합니다!!!\n", userInfo.getUserName());
                // 로그인한 계정의 정보를 객체화
                userInfo = new UserInfo(userInfo.getUserId(), userInfo.getUserPwd(), userInfo.getUserName(), userInfo.getUserArea(), userInfo.getUserAge(), userInfo.getUserPhoneNum());

                // 객체정보를 MyPageView 클래스로 넘김
                MyPageView.loginInfo(userInfo);
                MyPageView mv = new MyPageView();
//                // 확인용 메소드
//                mv.viewUser();

                // 객체정보를 BookingView 클래스로 넘김
                BookingView bookingView = new BookingView();
                bookingView.loginInfo(userInfo);

                // mypage로 이동하는 기능
                MyPageView mypage = new MyPageView();
                mypage.start();

            } else {
                System.out.println("아이디 비밀번호를 다시 입력하세요");
                // 로그인 실패시 다시 로그인 화면을 띄어주는 기능
                uv.loginStart();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("존재하는 회원정보가 없습니다.");
            userview = new UserView();
            // 로그인 실패시 다시 로그인 화면을 띄어주는 기능
            userview.loginStart();
        }


    }


}
