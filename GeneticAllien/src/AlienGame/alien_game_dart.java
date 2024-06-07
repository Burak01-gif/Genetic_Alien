import java.util.Random;
import java.util.Scanner;

public class alien_game_dart {
    public static void main(String[] args) {
        System.out.println("Enter the size of the population of compAlien species: ");
        Scanner input = new Scanner(System.in);
        int s = input.nextInt();

        String[][] mainCodes = generate_genetic_code(s);

        for (int i = 0; i < s; i++) {
            System.out.println("ID" + i + "," + find_gender(mainCodes)[i] + ", Health" + calculate_hp(mainCodes)[i]);
        }

        int op = input.nextInt();
        System.out.println("Choose an options:\n" +
                "(1) Mate two compAliens\n" +
                "(2) Randomly mate a set of compAliens\n" +
                "(3) Show statistics\n" +
                "(4) Your other option-1\n" +
                "(5) Your other option-2\n");
        if (op == 1) {
            System.out.println("Enter first ID");
            int id1 = input.nextInt();
            System.out.println("Enter second ID");
            int id2 = input.nextInt();
            mate_allien(mainCodes, id1, id2);

        } else if (op == 2) {
            Random randomly_mate = new Random();
            mate_allien(mainCodes, randomly_mate.nextInt(s), randomly_mate.nextInt(s));

        } else if (op == 3)
            calculate_statistics(mainCodes);

        else if (op == 4) {
            System.out.println();
            hp(mainCodes);
        } else if (op == 5) {
            System.out.println();
            irregular(mainCodes);
        }
    }

    public static String[][] generate_genetic_code(int s) {
        String[] dna = {"X", "Y", "Z"};
        Random Rdna = new Random();
        String[][] dnaS = new String[s][128];

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < 128; j++) {
                dnaS[i][j] = dna[Rdna.nextInt(3)];
            }
        }
        return dnaS;
    }

    public static String[] find_gender(String[][] rna) {
        String[] genders = new String[rna.length];

        for (int i = 0; i < rna.length; i++) {
            if (rna[i][127].equals("X") || rna[i][127].equals("Z")) {
                genders[i] = "Female";
            } else {
                genders[i] = "Male";
            }
        }
        return genders;
    }

    public static int[] calculate_hp(String[][] rna) {
        int[] hp = new int[rna.length];

        for (int i = 0; i < rna.length; i++) {
            int rhp = 0;
            String sekil = "";

            for (int j = 0; j < rna[i].length - 1; j++) {
                if (rna[i][j].equals("Y")) {
                    sekil += "Y";
                } else if (rna[i][j].equals("Z")) {
                    sekil += "Z";
                } else {
                    sekil += "X";
                }
            }
            if (sekil.equals("YXZ")) {
                rhp++;
            }
            hp[i] = rhp;
        }
        return hp;
    }

    public static void mate_allien(String[][] rna, int a1, int a2) {
        int[] takeHP = calculate_hp(rna);
        String[] takeGen = find_gender(rna);
        int chance_produce = (takeHP[a1] + takeHP[a2] * 25) / 4;
        if (takeHP[a1] == (takeHP[a2])) {
            System.out.println("No mate");
        } else if (chance_produce < 50) {
            System.out.println("Offspring chance: " + chance_produce + "%. They have 0 offspring");
        } else
            System.out.println("Offspring chance: " + chance_produce + "%. They have 1 offspring");
    }

    public static void calculate_statistics(String[][] rna) {
        int totalHealth = 0;
        int maleCount = 0;
        int femaleCount = 0;
        int[] hp = calculate_hp(rna);
        String[] genders = find_gender(rna);

        for (int i = 0; i < rna.length; i++) {
            totalHealth += hp[i];
            if (genders[i].equals("Male")) {
                maleCount++;
            } else {
                femaleCount++;
            }
        }

        double averageHealth = (double) totalHealth / rna.length;
        double malePercentage = (double) maleCount / rna.length * 100;
        double femalePercentage = (double) femaleCount / rna.length * 100;

        System.out.println("Statistics:");
        System.out.println("Average Health: " + averageHealth);
        System.out.println("Male Percentage: " + malePercentage + "%");
        System.out.println("Female Percentage: " + femalePercentage + "%");
    }

    public static void hp(String[][] rna) {
        int[] health = calculate_hp(rna);

        System.out.println("Health status of the population:");
        for (int i = 0; i < health.length; i++) {
            System.out.println("ID" + i + ": " + health[i]);
        }
    }

    public static void irregular(String[][] rna) {
        for (int i = 0; i < rna.length; i++) {
            String[] compAlien = rna[i];
            boolean irregularPattern = false;

            for (int j = 0; j < compAlien.length - 2; j++) {
                if (compAlien[j].equals("Y") && compAlien[j + 1].equals("X") && compAlien[j + 2].equals("Z")) {
                    irregularPattern = true;
                    break;
                }
            }
            if (irregularPattern) {
                System.out.println("CompAlien with irregular pattern found! ID: " + i);
            }
        }
    }

}
