package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

import java.util.Scanner;

public class InputCustomerView {

    public static Scanner sc = new Scanner(System.in);

    public Customer inputCustomer() {
        Customer customer = new Customer();
        System.out.println("===고객정보 입력===");
        System.out.println("고객ID 입력: ");
        String customerId = sc.nextLine();
        System.out.println("고객이름 입력: ");
        String customerName = sc.nextLine();
        System.out.println("고객나이 입력: ");
        int customerAge = sc.nextInt();
        sc.nextLine();
        System.out.println("고객등급 입력: ");
        String customerLevel = sc.nextLine();
        System.out.println("고객직업 입력: ");
        String customerJob = sc.nextLine();
        System.out.println("고객적립금 입력: ");
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
