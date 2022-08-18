package utils;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input(){
        this.scanner = new Scanner(System.in);
    }

    public String getString(){
        return this.scanner.nextLine();
    }
    public String getString(String prompt){
        System.out.println(prompt);
        return getString();
    }

    public boolean yesNo(){
        return yesNo("Enter yes or no");
    }
    public boolean yesNo(String prompt){
        System.out.println(prompt);
        String input = this.scanner.nextLine();
        return (input.trim().equalsIgnoreCase("y") ||
                input.trim().equalsIgnoreCase("yes"));
    }


    public int getInt(int min,int max,String prompt){
        while (true) {
            System.out.println(prompt);
            int number;
            try {
                String input = scanner.nextLine();
                number = Integer.parseInt(input);
            } catch (RuntimeException re) {
                System.err.println("Enter an integer.");
                return getInt(min,max,prompt);
            }
            if (number >= min && number <= max)
                return number;
        }
    }
    public int getInt(int min,int max){

        return getInt(min,max,"Please enter an integer between "+min+" and "+max);

    }

    public int getInt() {
        return getInt("Please enter an integer");
    }

    public int getInt(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine();

        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            System.err.println("Enter an integer.");
            return getInt(prompt);
        }
        return number;
    }

    public double getDouble(double min,double max) {
        return getDouble(min,max,"Enter a number between "+min+" and "+max);
    }

    public double getDouble(double min,double max,String prompt){
        while (true) {
            System.out.printf(prompt);

            String input = scanner.nextLine();
            double number;
            try {
                number = Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.err.println("Enter a number.");
                return getDouble(min,max,prompt);
            }
            if (number >= min && number <= max)
                return number;
        }
    }

    public double getDouble(){
        return getDouble("Please enter a number");
    }
    public double getDouble(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException exception) {
            System.err.println("Enter a number.");
            return getDouble(prompt);
        }
    }
    public String getBinary() { return getBinary("Please enter a String");}
    public String getBinary(String prompt) {
        return Integer.toBinaryString(getInt(prompt));
    }

    public String getHex() { return getHex("Please enter a String");}
    public String getHex(String prompt) {
        return Integer.toHexString(getInt(prompt));
    }

}