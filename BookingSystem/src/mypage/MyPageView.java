package mypage;

import sportsFacArea.BookingView;
import sportsFacArea.SelectedReserv;
import sportsFacArea.SportAreaRepository;
import userSys.UserInfo;
import userSys.UserView;

import static login.Utility.*;

public class MyPageView {

    private static userSys.UserRepository ur; // 회원정보
    private static SportAreaRepository sr; // 예약내역

    private static UserInfo myInfo;

    static {
        ur = new userSys.UserRepository();
        sr = new SportAreaRepository();

    }

    public static void start() {

        while (true) {
            showLoginSuccess();
            showMainScreen();
            selectMenu();
        }
    }
    private static void showLoginSuccess(){
        System.out.println("==========Menu==========");
        System.out.println("#1 시설 예약하기");
        System.out.println("#2 Mypage로 이동");
        System.out.println("#0. 로그아웃");

        String num;
        num=input(">>");
        switch (num){
            case "1":
                BookingView bookingView=new BookingView();
                bookingView.areaStart();
                break;
            case "2":

                break;
                case "0":
                    login.UserView userView= new login.UserView();
                    userView.inputUserinfo();
                    break;
            default:
                System.out.println("값을 정확하게 입력해주세요");
                showLoginSuccess();
        }
    }

    private static void showMainScreen() {
        System.out.println("=====회원정보 수정=====");
        System.out.println("1. 회원 정보 수정");
        System.out.println("2. 예약 내역 보기");
        makeLine();
    }


    private static void selectMenu() {
        String menuNum = input(">> ");

        switch (menuNum) {
            case "1":
                showMyInfo(); // 나의 정보 조회
                changeMyInfo(); // 나의 정보 수정
                myPageExit();
                break;

            case "2":
                seeMyBooking(); // 예약 내역 보기
                break;

            default:
                System.out.println("\n# 번호를 똑바로 입력해주세요.");
        }
    }

    private static void showMyInfo() {
        System.out.println("\n# ====기존 회원 정보====");
        try {
            System.out.println("아이디 : "+ myInfo.getUserId());
            System.out.println("패스워드 : "+ myInfo.getUserPwd());
            System.out.println("이름 : "+ myInfo.getUserName());
            System.out.println("나이 : "+ myInfo.getUserAge());
            System.out.println("거주지 : "+ myInfo.getUserArea());
            System.out.println("전화번호 : "+ myInfo.getUserPhoneNum());
        } catch (Exception e) {
            System.out.println("기존 회원 정보가 없습니다.");
            e.printStackTrace();
        }

    }

    private static void changeMyInfo() {
        System.out.println("\n# 수정할 정보를 입력하세요.");
        String changeInfo = input(">> ");

        //회원에게 수정할 정보 입력받기
        System.out.printf(changeInfo + "변경을 선택하셨습니다.\n변경 할 내용을 입력하세요.\n");
        String changedInfo = input(">> ");
        System.out.printf("%s님의 %s가 %s로 변경되었습니다. \n",myInfo.getUserName(),changeInfo, changedInfo);

        //저장하기

        //취소하기

    }

    private static void seeMyBooking() {
        System.out.println("\n# 예약 내역 보기를 선택하셨습니다.");
        //예약 내역 불러오기
        System.out.printf("\n=====%s님의 예약 내역=====", myInfo.getUserName());
        SelectedReserv selectedReserv=new SelectedReserv();
        selectedReserv.info();
//        myPageExit(); //화면 나가기

    }

    private static void myPageExit() {
        System.out.println("\n# 화면을 나가시겠습니까?[y/n]");
        String answer = input(">> ");

        switch (answer.toLowerCase().charAt(0)) {
            case 'y':
            case 'ㅛ':
                System.out.println("종료합니다.");
                break;
            case 'n':
            case 'ㅜ':
                System.out.println("예약 내역 정보로 돌아갑니다.");
                seeMyBooking();
                break;
        }

    }

    public static void loginInfo(UserInfo userInfo) {
        myInfo=userInfo;
    }

    public void viewUser(){
        System.out.println(myInfo);
        System.out.println("값 넘어갔습니다.");
    }



}
