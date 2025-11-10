package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

import java.sql.*;
import java.util.Scanner;

public class UpdateCustomerView {
    public static Scanner sc = new Scanner(System.in);
    public Customer UpdateCustomer() {
        Customer customer = new Customer();

        System.out.println("=== 고객정보 수정 (ID는 수정할 수 없습니다) ===");
        System.out.print("수정할 고객의 ID 입력: ");
        String customerId = sc.nextLine();

        System.out.print("고객이름(변경 후) 입력: ");
        String customerName = sc.nextLine();

        System.out.print("고객나이(변경 후) 입력: ");
        int customerAge = sc.nextInt();

        System.out.print("고객등급(변경 후) 입력: ");
        String customerLevel = sc.nextLine();

        System.out.print("고객직업(변경 후) 입력: ");
        String customerJob = sc.nextLine();

        System.out.print("고객적립금(변경 후) 입력: ");
        int customerReward = sc.nextInt();
        sc.nextLine();

        customer.setCustomerid(customerId);
        customer.setCustomername(customerName);
        customer.setAge(customerAge);
        customer.setLevel(customerLevel);
        customer.setJob(customerJob);
        customer.setReward(customerReward);

        return customer;
    }
}
