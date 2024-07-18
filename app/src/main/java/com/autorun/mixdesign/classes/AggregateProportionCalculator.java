package com.autorun.mixdesign.classes;

public class AggregateProportionCalculator {

    public static double calculateProportionOfFineAggregate(int maximumAggregateSize, int slump, double freeWaterCementRatio, double gradingOfFineAggregate) {

        double Pfa = 0;
        int Az = maximumAggregateSize;
        double Fwcr = freeWaterCementRatio;
        double zone = gradingOfFineAggregate;


        if (Az == 40)
        {
            if (slump == 4)
            {
                if (zone == 4)
                {
                    Pfa = 14.7 * Fwcr + 16.25;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 19.5;
                }
                else if (zone == 2)
                {
                    Pfa = 19.17 * Fwcr + 27.25;
                }
                else if (zone == 1)
                {
                    Pfa = 21.67 * Fwcr + 30;
                }
                else if (zone == 0)
                {
                    Pfa = 27.5*Fwcr + 35.75;
                }

            }

            if (slump == 3)
            {
                if (zone == 4)
                {
                    Pfa = 13.33 * Fwcr + 13.5;
                }
                else if (zone == 3)
                {
                    Pfa = 14.17 * Fwcr + 16.25;
                }
                else if (zone == 2)
                {
                    Pfa = 17.5 * Fwcr + 19.25;
                }
                else if (zone == 1)
                {
                    Pfa = 21.67 * Fwcr + 24.5;
                }
                else if (zone == 0)
                {
                    Pfa = 27.5 *Fwcr + 28.75;
                }

            }

            if (slump == 2)
            {
                if (zone == 4)
                {
                    Pfa = 11.67 * Fwcr + 14.5;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 13.5;
                }
                else if (zone == 2)
                {
                    Pfa = 17.5 * Fwcr + 16.25;
                }
                else if (zone == 1)
                {
                    Pfa = 22.5 * Fwcr + 20.25;
                }
                else if (zone == 0)
                {
                    Pfa = 28.33 * Fwcr + 24.5 ;
                }
            }

            if (slump == 1)
            {
                if (zone == 4)
                {
                    Pfa = 14.17 * Fwcr + 9.25;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 12;
                }
                else if (zone == 2)
                {
                    Pfa = 19.33 * Fwcr + 14;
                }
                else if (zone == 1)
                {
                    Pfa = 22.5 * Fwcr + 17.75;
                }
                else if (zone == 0)
                {
                    Pfa = 26.67 * Fwcr + 23;
                }
            }
        }

        if (Az == 20)
        {
            if (slump == 4)
            {
                if (zone == 4)
                {
                    Pfa = 13.33 * Fwcr + 20;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 23.5;
                }
                else if (zone == 2)
                {
                    Pfa = 19.17 * Fwcr + 27.25;
                }
                else if (zone == 1)
                {
                    Pfa = 22.5 * Fwcr + 33.25;
                }
                else if (zone == 0){
                    Pfa = 26.67 * Fwcr + 43.5;
                }
            }

            if (slump == 3)
            {
                if (zone == 4)
                {
                    Pfa = 11.67 * Fwcr + 17.99;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 19.5;
                }
                else if (zone == 2)
                {
                    Pfa = 16.67 * Fwcr + 24.499;
                }
                else if (zone == 1)
                {
                    Pfa = 22.5 * Fwcr + 29.75;
                }
                else if (zone == 0)
                {
                    Pfa = 27.5 * Fwcr + 35.75;
                }
            }

            if (slump == 2)
            {

                if (zone == 4)
                {
                    Pfa = 10.83 * Fwcr + 16.251;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 18;
                }
                else if (zone == 2)
                {
                    Pfa = 18.33 * Fwcr + 21;
                }
                else if (zone == 1)
                {
                    Pfa = 22.5 * Fwcr + 26.25;
                }
                else if (zone == 0)
                {
                    Pfa = 27.5 * Fwcr + 31.75;
                }
            }

            if (slump == 1)
            {
                if (zone == 4)
                {
                    Pfa = 12.5 * Fwcr + 14.25;
                }
                else if (zone == 3)
                {
                    Pfa = 13.33 * Fwcr + 17;
                }
                else if (zone == 2)
                {
                    Pfa = 16.67 * Fwcr + 19.99;
                }
                else if (zone == 1)
                {
                    Pfa = 20.83 * Fwcr + 24.751;
                }
                else if (zone == 0)
                {
                    Pfa = 26.67 * Fwcr + 29.499;
                }

            }
        }

        if (Az == 10)
        {
            if (slump == 4)
            {
                if (zone == 4)
                {
                    Pfa = 12.5 * Fwcr + 27;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 31;
                }
                else if (zone == 2)
                {
                    Pfa = 17.5 * Fwcr + 37;
                }
                else if (zone == 1)
                {
                    Pfa = 25 * Fwcr + 45;
                }
                else if (zone == 0)
                {
                    Pfa = 27.5 * Fwcr + 56;
                }
            }

            if (slump == 3)
            {
                if (zone == 4)
                {
                    Pfa = 12.5 * Fwcr + 22;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 27;
                }
                else if (zone == 2)
                {
                    Pfa = 15 * Fwcr + 34;
                }
                else if (zone == 1)
                {
                    Pfa = 20 * Fwcr + 41;
                }
                else if (zone == 0)
                {
                    Pfa = 30 * Fwcr + 48;
                }
            }

            if (slump == 2)
            {
                if (zone == 4)
                {
                    Pfa = 15 * Fwcr + 20;
                }
                else if (zone == 3)
                {
                    Pfa = 12.5 * Fwcr + 27;
                }
                else if (zone == 2)
                {
                    Pfa = 15 * Fwcr + 34;
                }
                else if (zone == 1)
                {
                    Pfa = 22.5 * Fwcr + 37;
                }
                else if (zone == 0)
                {
                    Pfa = 22.5 * Fwcr + 47;
                }
            }

            if (slump == 1)
            {
                if (zone == 4)
                {
                    Pfa = 12.5 * Fwcr + 20;
                }
                else if (zone == 3)
                {
                    Pfa = 15 * Fwcr + 23;
                }
                else if (zone == 2)
                {
                    Pfa = 17.5 * Fwcr + 28;
                }
                else if (zone == 1)
                {
                    Pfa = 22.5 * Fwcr + 35;
                }
                else if (zone == 0)
                {
                    Pfa = 22.5 * Fwcr + 47;
                }
            }
        }

        return Math.ceil(Pfa);
    }

    public static double calculateFineAggregateContent(double totalAggregateContent, double proportionOfFineAggregate) {
        return totalAggregateContent * (proportionOfFineAggregate / 100);
    }

    public static double calculateCoarseAggregateContent(double totalAggregateContent, double fineAggregateContent) {
        return totalAggregateContent - fineAggregateContent;
    }
}
