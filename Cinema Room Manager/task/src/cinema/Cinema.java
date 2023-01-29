package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        /* variable for calculating the profit earned from selling tickets */
        int totalIncome = 0;

        /* variable to store row and seat number */
        int rowNumber = 0;
        int seatNumber = 0;

        /* variable to store whether the user is still buying a ticket */
        boolean isBuying = true;

        /* to store the option chosen */
        int choice = 0;

        /* variables to store */
        int firstHalf = 0;
        int secondHalf = 0;

        /* variable to store the total seats*/
        int totalSeats = 0;

        /* variable to store the first half row and second half row */
        int firstHalfRows = 0;
        int secondHalfRows = 0;

        /* variable to store the ticket price, percentage, current income */
        int ticketPrice = 0;
        double percentage = 0.0;
        int currentIncome = 0;

        /* variable to store the number of purchased tickets */
        int numTickets = 0;

        /* read two positive integers , the number of rows and the number of seats */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();
        System.out.println();

        /* determine the price of each ticket using if-statements */
        if (numberOfRows <= 9 && numberOfSeats <= 9) {
            totalIncome = calcProfit(numberOfRows, numberOfSeats);
        }

        /* create a 7 by 8 array: 7 rows and 8 seats */
        char[][] cinema = new char[numberOfRows][numberOfSeats];

        /* store the characters in the 2D array */
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }

        while (isBuying) {
            /* output a menu */
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    /* exit for loop */
                    isBuying = false;
                    break;
                case 1:
                    System.out.println();

                    /* output cinema: */
                    System.out.println("Cinema:");

                    /* output the seat arrangement array */
                    System.out.print("  ");
                    for(int i = 1; i <= numberOfSeats; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println();

                    /* display seats */
                    displaySeats(cinema);

                    System.out.println();

                    break;
                case 2:
                    System.out.println();
                    /* read in a row number and seat number in order to book */
                    System.out.println("Enter a row number:");
                    rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNumber = scanner.nextInt();

                    while (((rowNumber < 1) || (rowNumber > numberOfRows)) || ((seatNumber < 1) || (seatNumber > numberOfSeats))) {
                        System.out.println("Wrong input!");
                        System.out.println();
                        /* read in a row number and seat number in order to book */
                        System.out.println("Enter a row number:");
                        rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = scanner.nextInt();
                    }

                    int tempRow = rowNumber - 1;
                    int tempSeat = seatNumber - 1;

                    while (cinema[tempRow][tempSeat] == 'B') {
                        System.out.println("That ticket has already been purchased!");
                        System.out.println();
                        /* read in a row number and seat number in order to book */
                        System.out.println("Enter a row number:");
                        rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = scanner.nextInt();
                        tempRow = rowNumber - 1;
                        tempSeat = numberOfSeats - 1;
                    }



                    /* calculate and store the first half and second number of rows */
                    if (numberOfRows * numberOfSeats <= 60) {
                        ticketPrice = 10;
                        rowNumber--;
                        seatNumber--;
                        cinema[rowNumber][seatNumber] = 'B';
                    } else {
                        if (numberOfRows % 2 == 0) {
                            firstHalfRows = numberOfRows / 2;
                            secondHalfRows = numberOfRows / 2;
                            if (rowNumber >= 1 && rowNumber <= firstHalfRows) {
                                rowNumber--;
                                seatNumber--;
                                ticketPrice = 10;
                                cinema[rowNumber][seatNumber] = 'B';
                            } else if (rowNumber >= secondHalfRows && rowNumber <= numberOfRows) {
                                rowNumber--;
                                seatNumber--;
                                ticketPrice = 8;
                                cinema[rowNumber][seatNumber] = 'B';
                            }
                        } else {
                            firstHalfRows = numberOfRows / 2;
                            secondHalfRows = (numberOfRows / 2) + 1;
                            if (rowNumber >= 1 && rowNumber <= firstHalfRows) {
                                rowNumber--;
                                seatNumber--;
                                ticketPrice = 10;
                                cinema[rowNumber][seatNumber] = 'B';
                            } else if (rowNumber >= secondHalfRows && rowNumber <= numberOfRows) {
                                rowNumber--;
                                seatNumber--;
                                ticketPrice = 8;
                                cinema[rowNumber][seatNumber] = 'B';
                            }
                        }
                    }

                    System.out.println("Ticket price: $%d".formatted(ticketPrice));

                    /* increment by 1 everytime someone buys a ticket */
                    numTickets++;

                    /* calculate percentage */
                    percentage = ((double)numTickets / (numberOfSeats * numberOfRows)) * 100;

                    System.out.println();

                    /* calculate current income by summing up the ticket prices */
                    currentIncome += ticketPrice;

                    /* output cinema: */
                    System.out.println("Cinema:");

                    /* output the seat arrangement array */
                    System.out.print("  ");
                    for(int i = 1; i <= numberOfSeats; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println();

                    displaySeats(cinema); // display seats

                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Number of purchased tickets: %d".formatted(numTickets));
                    System.out.println("Percentage: %.2f".formatted(percentage) + "%");
                    System.out.println("Current income: $%d".formatted(currentIncome));
                    System.out.println("Total income: $%d".formatted(totalIncome));

                    System.out.println();
                    break;
            }
        }

    }

    private static void displaySeats(char[][] cinema) {
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
    }


    /***
     *
     * @param numberOfRows
     * @param numberOfSeats
     * @return
     */
    private static int calcProfit(int numberOfRows, int numberOfSeats) {
        int profit;
        int numberOfBack;
        int numberOfFront;
        int profitFirstHalf;
        int profitSecondHalf;
        int totalSeats = numberOfSeats * numberOfRows;
        int firstHalf;
        int secondHalf;

        if (totalSeats <= 60) {
            if (numberOfRows % 2 == 0) {
                profit = numberOfRows * numberOfSeats * 10;
            } else {
                if (numberOfRows == numberOfSeats) {
                    profit = numberOfRows * numberOfSeats * 10;
                } else {

                    /* if the number of rows is odd */
                    numberOfBack = numberOfRows / 2 + 1; // back half
                    numberOfFront = numberOfRows / 2; // front half
                    profitFirstHalf = numberOfFront * numberOfSeats * 10;
                    profitSecondHalf = numberOfBack * numberOfSeats * 8;
                    profit = profitFirstHalf + profitSecondHalf;
                }
            }
        } else {
            if (numberOfRows % 2 == 0) {
                firstHalf = totalSeats / 2;
                secondHalf = totalSeats / 2;
                profit = firstHalf * 10 + secondHalf * 8;
            } else {
                /* if the number of rows is odd */
                numberOfBack = numberOfRows / 2 + 1; // back half
                numberOfFront = numberOfRows / 2; // front half
                profitFirstHalf = numberOfFront * numberOfSeats * 10;
                profitSecondHalf = numberOfBack * numberOfSeats * 8;
                profit = profitFirstHalf + profitSecondHalf;
            }
        }
        return profit;
    }
}