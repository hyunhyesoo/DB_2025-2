package mvc_jdbc_test.view;

import java.util.Scanner;

public class DeleteCustomerView {
    public static Scanner sc = new Scanner(System.in);

    public String deleteCustomer() {
        System.out.println("=== 고객정보 삭제 ===");
        System.out.print("삭제할 고객아이디 입력: ");
        String customerId = sc.nextLine();

        return customerId;
    }
}
