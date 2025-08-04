package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class InteractiveOrderProcessor {
    static Scanner sc = new Scanner(System.in);
    static double unitPrice;
    static int quantity;
    static boolean member;
    static String customerTier;
    static String shippingZone;
    static String discountCode;


    static double subTotal;
    static double afterTierDiscount;
    static double afterQuantityDiscount;
    static double afterPromotionalCode;
    static double smallOrderSurcharge;
    static double shippingCost;
    static double finalOrderTotal;

    public static void main(String[] args) {
        System.out.println("Welcome to the Interactive Order Processor!");
        System.out.println();


        System.out.println("--- Enter Order Details ---");
        System.out.print("Enter unit price: ");
        unitPrice = sc.nextDouble();
        System.out.print("Enter quantity: ");
        quantity = sc.nextInt();
        System.out.print("Is customer a member (true/false)?: ");
        member = sc.nextBoolean();
        if(member) {
            System.out.print("Enter customer tier (Regular, Silver, Gold): ");
            customerTier = sc.next().toUpperCase();
        } else {
            customerTier = "REGULAR";
        }

        System.out.print("Enter shipping zone (ZoneA, ZoneB, ZoneC, Unknown): ");
        shippingZone = sc.next().toUpperCase();
        System.out.print("Enter discount code (SAVE10, FREESHIP, \"\" for none): ");
        discountCode = sc.next().toUpperCase();
        System.out.println();

        System.out.println("--- Order Details ---");
        System.out.println("Unit Price: $" + unitPrice);
        System.out.println("Quantity: " + quantity);
        System.out.println("Is Member: " + member);
        System.out.println("Customer Tier: " + customerTier);
        System.out.println("Shipping Zone: " + shippingZone);
        System.out.println("Discount Code: " + discountCode);
        System.out.println();

        System.out.println("--- Calculation Steps ---");
        subTotal = unitPrice * quantity;

        System.out.println("Initial Subtotal: $" + subTotal);
        if (customerTier.equals("GOLD")) {
            afterTierDiscount = subTotal - (subTotal * 0.15);
            System.out.println("After Tier Discount (Gold - 15%): $" + afterTierDiscount);

        } else if (customerTier.equals("SILVER")) {
            afterTierDiscount = subTotal - (subTotal * 0.10);
            System.out.println("After Tier Discount (Silver - 10%): $" + afterTierDiscount);

        } else {
            afterTierDiscount = subTotal - (subTotal * 0);
            System.out.println("After Tier Discount (Regular/Unknown - 0%): $" + afterTierDiscount);
        }

        if (quantity >= 5) {
            afterQuantityDiscount = afterTierDiscount - (afterTierDiscount * 0.05);
            System.out.println("After Quantity Discount (5% for >=5 items): $" + afterQuantityDiscount);
        } else {
            afterQuantityDiscount = afterTierDiscount - (afterTierDiscount * 0);
            System.out.println("After Quantity Discount (0% for < 5 items): $" + afterQuantityDiscount);

        }

        if (discountCode.equals("SAVE10") && afterQuantityDiscount > 75.00) {
            afterPromotionalCode = afterQuantityDiscount - (10);
            System.out.println("After Promotional Code (SAVE10 for >$75): $" + afterPromotionalCode);
        } else if (discountCode.equals("FREESHIP")) {
            afterPromotionalCode = afterQuantityDiscount;
            System.out.println("After Promotional Code (FREESHIP): $" + afterPromotionalCode);
        } else {
            afterPromotionalCode = afterQuantityDiscount;
        }

        smallOrderSurcharge = afterPromotionalCode < 25.00 ? afterPromotionalCode + 3.00 : afterPromotionalCode;
        System.out.println("After Small Order Surcharge (if applicable): $" + smallOrderSurcharge);
        System.out.println();

        switch (shippingZone) {
            case "ZONEA":
                if (discountCode.equals("FREESHIP")) {
                    shippingCost = 0.0;
                } else {
                    shippingCost = 5.00;
                }
                break;
            case "ZONEB":
                if (discountCode.equals("FREESHIP")) {
                    shippingCost = 0.0;
                } else {
                    shippingCost = 12.50;
                }
                break;
            case "ZONEC":
                if (discountCode.equals("FREESHIP")) {
                    shippingCost = 0.0;
                } else {
                    shippingCost = 20.00;
                }
                break;
            default:
                if (discountCode.equals("FREESHIP")) {
                    shippingCost = 0.0;
                } else {
                    shippingCost = 25.00;
                }
        }
        System.out.println("Shipping Cost: $" + shippingCost + " (" + shippingZone + ")");
        System.out.println();
        finalOrderTotal = shippingCost + smallOrderSurcharge;
        System.out.println("Final Order Total: $" + finalOrderTotal);
        System.out.println();

        InteractiveStringEquality();
    }
    public static void InteractiveStringEquality() {
        String s1;
        String s2;
        System.out.println("--- String Equality Demo ---");
        System.out.print("Enter first string for comparison: ");
        s1 = sc.next();
        System.out.print("Enter second string for comparison: ");
        s2 = sc.next();
        sc.close();

        System.out.println();
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);
        System.out.println();

        System.out.println("String 1 == String 2: " + (s1 == s2) + " (Compares references, which are different for user input strings)");
        System.out.println("String 1 .equals() String 2: " + s1.equals(s2) + " (Content is different due to case)");
        System.out.println("String 1 .equalsIgnoreCase() String 2: " + s1.equalsIgnoreCase(s2) + " (Content is identical, ignoring case)");
    }
}