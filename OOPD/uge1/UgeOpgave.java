import java.util.Scanner;

public class UgeOpgave
{

    public static void P112()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter an integer between 1,000 and 999,999: ");
        String number = in.next();
        number = number.replace(",", "");
        System.out.println(number);

    }

    public static void P113()
    {

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter an integer between 1000 and 999999: ");
        String number = in.next();
        if(number.length() < 4)
        {
            System.out.print(number);
        }
        else
        {
            number = number.substring(0, number.length()-3)
                + ","
                + number.substring(number.length()-3, number.length());
            System.out.println(number);
        }
    }

    public static void main(String[] arg)
    {
        P112();
        P113();
    }
}
