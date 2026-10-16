import java.util.Scanner;

class MonthlyBudget {
    private double budget;
    private String[] categories;
    private double[] expenses;
    private int count;

    // Parameterized constructor
    public MonthlyBudget(double budget, int capacity) {
        this.budget = budget;
        categories = new String[capacity];
        expenses = new double[capacity];
        count = 0;
    }

    // Encapsulation: getter and validated setter
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        if (budget >= 0) {
            this.budget = budget;
        } else {
            System.out.println("Budget cannot be negative.");
        }
    }

    public boolean addExpense(String category, double amount) {
        if (count >= expenses.length || amount < 0) {
            return false;
        }
        categories[count] = category;
        expenses[count] = amount;
        count++;
        return true;
    }

    // Method overloading: add an expense with a default category
    public boolean addExpense(double amount) {
        return addExpense("Other", amount);
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += expenses[i];
        }
        return total;
    }

    public void displayReport() {
        double total = calculateTotal();
        System.out.println("\n===== MONTHLY EXPENSE REPORT =====");
        System.out.printf("%-18s %10s%n", "Category", "Amount (Rs.)");
        System.out.println("----------------------------------");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-18s %10.2f%n", categories[i], expenses[i]);
        }
        System.out.println("----------------------------------");
        System.out.printf("Monthly Budget:     Rs. %.2f%n", budget);
        System.out.printf("Total Expenses:     Rs. %.2f%n", total);
        System.out.printf("Remaining Balance:  Rs. %.2f%n", budget - total);

        // if-else decision making
        if (total > budget) {
            System.out.println("Status: OVER BUDGET");
        } else if (total == budget) {
            System.out.println("Status: BUDGET FULLY USED");
        } else {
            System.out.println("Status: WITHIN BUDGET");
        }
    }
}

public class MonthlyExpenseBudgetAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("MONTHLY EXPENSE & BUDGET ANALYZER");
        System.out.print("Enter monthly budget (Rs.): ");
        double budget = sc.nextDouble();

        System.out.print("How many expenses will you enter? ");
        int n = sc.nextInt();
        sc.nextLine();

        if (budget < 0 || n < 1) {
            System.out.println("Please enter a non-negative budget and at least 1 expense.");
            sc.close();
            return;
        }

        MonthlyBudget tracker = new MonthlyBudget(budget, n);

        for (int i = 0; i < n; i++) {
            System.out.print("Enter category for expense " + (i + 1) + ": ");
            String category = sc.nextLine();

            System.out.print("Enter amount (Rs.): ");
            double amount = sc.nextDouble();
            sc.nextLine();

            if (!tracker.addExpense(category, amount)) {
                System.out.println("Invalid amount. Expense was not added.");
            }
        }

        tracker.displayReport();
        sc.close();
    }
}
