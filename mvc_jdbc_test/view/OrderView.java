package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderView {
    public String title = "주문 정보";
    public void printOrder(Order order) {
        Date orderdate = order.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(orderdate);

        System.out.printf("%s\t %s\t %s\t\t %d\t %s\t %s\n",
                            order.getCustomername(), order.getCustomerid(), order.getProductname(),order.getAmount(), date, order.getAddress());
//        System.out.printf("* 고객명: %s\n", order.getCustomername());
//        System.out.printf("* 고객아이디: %s\n", order.getCustomerid());
//        System.out.printf("* 배송지: %s\n", order.getAddress());
//        System.out.printf("* 수량: %s\n", order.getAmount());
//        System.out.printf("* 주문일자: %s\n", order.getDate());
//        System.out.printf("* 제품명: %s\n", order.getProductname());
    }

    public void printHead() {
        System.out.println("----------------------------------------");
        System.out.println("--------------" + title + "---------------");
        System.out.println("----------------------------------------");
        System.out.println("고객명\t 고객아이디\t 제품명\t 수량\t 주문일자\t 배송지");
    }

    public void printFooter() {
        System.out.println("----------------------------------------");
        System.out.println("--------------Print Done---------------");
        System.out.println("----------------------------------------");
    }
}
