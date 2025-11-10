package mvc_jdbc_test.controller;

import jdbc_test.JDBCConnector;
import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.view.CustomerView;
import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.view.InputCustomerView;
import mvc_jdbc_test.view.OrderView;
import mvc_jdbc_test.view.UpdateCustomerView;
import mvc_jdbc_test.view.DeleteCustomerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Connection con = JDBCConnector.getConnection();
        deleteCustomerAndView(con);
//        Connection con = JDBCConnector.getConnection();
//        updateCustomerAndView(con);
//        Connection con = JDBCConnector.getConnection();
//        InputcustomerAndView(con);
//        Connection conn = JDBCConnector.getConnection();
//        customerListAndView(conn);
//        Connection con = JDBCConnector.getConnection();
//        orderListAndView(con);
    }

    public static void customerListAndView(Connection conn) {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        try {
            String sql = "select * from 고객";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Customer customer = null;

            while (rs.next()) {
                customer = new Customer();
                customer.setCustomerid(rs.getString("고객아이디"));
                customer.setCustomername(rs.getString("고객이름"));
                customer.setAge(rs.getInt("나이"));
                customer.setLevel(rs.getString("등급"));
                customer.setJob(rs.getString("직업"));
                customer.setReward(rs.getInt("적립금"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
        }

        //CustomerView를 사용해서 customerList에 저장된 Customer Entity의 정보를 출력
        CustomerView customerView = new CustomerView();
        customerView.printHead();
        for (Customer customer : customerList) {
            customerView.printCustomer(customer);
            System.out.println();
        }
        customerView.printFooter();
    }

    public static void orderListAndView(Connection con) {
        ArrayList<Order> orderList = new ArrayList<Order>();
        try {
            String sql = "select 주문번호, 고객이름, 고객아이디, 배송지, 수량, 주문일자, 제품명 from 주문, 고객, 제품 where 주문.주문제품=제품.제품번호 and 주문.주문고객=고객.고객아이디";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Order order = null;

            while (rs.next()) {
                order = new Order();
                order.setorderid(rs.getString("주문번호"));
                order.setCustomername(rs.getString("고객이름"));
                order.setCustomerid(rs.getString("고객아이디"));
                order.setAddress(rs.getString("배송지"));
                order.setAmount(rs.getInt("수량"));
                order.setDate(rs.getDate("주문일자"));
                order.setProductname(rs.getString("제품명"));
                orderList.add(order);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
        }

        //OrderView를 사용해서 orderList에 저장된 Order Entity의 정보를 출력
        OrderView orderView = new OrderView();
        orderView.printHead();
        for (Order order : orderList) {
            orderView.printOrder(order);
            System.out.println();
        }
        orderView.printFooter();
    }


    public static void InputcustomerAndView(Connection con) {
        Scanner sc = InputCustomerView.sc;
        InputCustomerView inputCustomer = new InputCustomerView();
        while (true) {
            Customer customer = inputCustomer.inputCustomer();
            CustomerView customerView = new CustomerView();
            customerView.printHead();
            customerView.printCustomer(customer);
            customerView.printFooter();

            String sql = "insert into 고객 values(?,?,?,?,?,?)";
//            String sql = "insert into 고객 values('"+customer.getCustomerid()+"')"; ? 안 쓰면 이렇게 문자열끼리 다 연결해줘야해서 매우 번거로움
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, customer.getCustomerid());
                pst.setString(2, customer.getCustomername());
                pst.setInt(3, customer.getAge());
                pst.setString(4, customer.getLevel());
                pst.setString(5, customer.getJob());
                pst.setInt(6, customer.getReward());
                pst.executeUpdate();
                pst.close();
                System.out.println("고객정보 1건 추가 완료");
            } catch (SQLException e) {
                System.out.println("Statement or SQL Error");
            }
            System.out.println("추가 입력(임의의 문자) / 종료(e) =>");
            String choice = sc.nextLine();
            if (choice.equals("e")) {
                break;
            }
            System.out.println("프로그램이 종료되었습니다.");
        }
    }

    public static void updateCustomerAndView(Connection con) {
        // 수정용 View
        UpdateCustomerView updateView = new UpdateCustomerView();
        Scanner sc = UpdateCustomerView.sc;

        while (true) {
            // 어떤 고객을 수정할지 + 수정할 값들 받기
            Customer customer = updateView.UpdateCustomer();

            CustomerView customerView = new CustomerView();
            customerView.title = "수정할 고객 정보";
            customerView.printHead();
            customerView.printCustomer(customer);
            customerView.printFooter();

            String sql = "update 고객 set 고객이름 = ?, 나이 = ?, 등급 = ?, 직업 = ?, 적립금 = ? where 고객아이디 = ?";

            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, customer.getCustomername());
                pst.setInt(2, customer.getAge());
                pst.setString(3, customer.getLevel());
                pst.setString(4, customer.getJob());
                pst.setInt(5, customer.getReward());
                pst.setString(6, customer.getCustomerid());

                int result = pst.executeUpdate();
                pst.close();

                if (result > 0) {
                    System.out.println("고객정보 1건 수정 완료");
                } else {
                    System.out.println("해당 고객아이디가 없습니다.");
                }
            } catch (SQLException e) {
                System.out.println("수정 오류: " + e.getMessage());
            }

            System.out.println("추가 수정(임의의 문자) / 종료(e) =>");
            String choice = sc.nextLine();
            if (choice.equals("e")) {
                break;
            }
        }
        System.out.println("프로그램이 종료되었습니다.");
    }


    public static void deleteCustomerAndView(Connection con) {
        DeleteCustomerView deleteView = new DeleteCustomerView();
        Scanner sc = DeleteCustomerView.sc;

        while (true) {
            String targetId = deleteView.deleteCustomer();

            System.out.print("정말 삭제하시겠습니까? (y/n): ");
            String yn = sc.nextLine().trim().toLowerCase();

            if (!yn.equals("y")) {
                System.out.println("삭제를 취소하였습니다.");
            } else {
                try {
                    String delSql = "DELETE FROM 고객 WHERE 고객아이디 = ?";
                    PreparedStatement ps = con.prepareStatement(delSql);
                    ps.setString(1, targetId);
                    int rows = ps.executeUpdate();
                    ps.close();

                    if (rows > 0) {
                        System.out.println("고객정보 1건 삭제 완료");
                    } else {
                        System.out.println("입력한 아이디가 존재하지 않습니다.");
                    }
                } catch (SQLException e) {
                    System.out.println("삭제 오류: " + e.getMessage());
                }
            }

            System.out.print("추가 삭제(임의의 문자) / 종료(e) => ");
            String choice = sc.nextLine();
            if (choice.equals("e")) {
                break;
            }
        }
        System.out.println("프로그램이 종료되었습니다.");
    }
}

