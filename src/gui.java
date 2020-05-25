import java.util.Scanner;

public class gui
{
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initialize();
        loop();
    }

    private static int Health;
    private static int HealthMax;
    private static int Karma;
    private static int KarmaMax;
    private static int Astral;
    private static int AstralMax;

    private static void initialize()
    {
        System.out.println("Enter current Health: ");
        Health = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter maximum Health: ");
        HealthMax = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter current Karma Energy: ");
        Karma = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter maximum Karma Energy: ");
        KarmaMax = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter current Astral Energy: ");
        Astral = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter maximum Astral Energy: ");
        AstralMax = sc.nextInt();
        sc.nextLine();
    }

    private static void loop()
    {
        while(true)
        {
            System.out.println("Health: " + Health + "\nKarma: " + Karma + "\nAstral: " + Astral);
            System.out.println("What do you want to do?");
            System.out.println("1. Change Health");
            System.out.println("2. Change Karma");
            System.out.println("3. Change Astral");
            System.out.println("4. Quit");
            int response = 0;
            response = sc.nextInt();
            sc.nextLine();
            int value = 0;
            switch (response)
            {
                case 1:
                    System.out.println("How much?");
                    value = sc.nextInt();
                    sc.nextLine();
                    Health += value;
                    if(Health > HealthMax)Health = HealthMax;
                    break;
                case 2:
                    System.out.println("How much?");
                    value = sc.nextInt();
                    sc.nextLine();
                    Karma += value;
                    if(Karma > KarmaMax)Karma = KarmaMax;
                    break;
                case 3:
                    System.out.println("How much?");
                    value = sc.nextInt();
                    sc.nextLine();
                    Astral += value;
                    if(Astral > AstralMax)Astral = AstralMax;
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid.");
            }
        }
    }
}
