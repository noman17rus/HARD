import java.math.BigDecimal;
import java.math.RoundingMode;

public class Hardness {

    public static void main (String args[]){

        int volume=50; //объём пробы
        double concentration=0.00971; // концентрация трилона Б
        float volimeHARD1 = (float) 12.9;
        float volimeHARD2 = (float) 13.0;
        float volumecalcium1 = (float) 6.5;
        float volumecalcium2 = (float) 6.7;
        float hardRES = Concentrations.hardness(volimeHARD1,volimeHARD2,concentration,volume);
        float calciumRES = Concentrations.calcium(volumecalcium1,volumecalcium2,concentration,volume);
        float magnesiumRES = Concentrations.magnesium(volimeHARD1,volumecalcium1,volumecalcium2,volimeHARD2,concentration,volume);
        float normHARD = (float) (hardRES * 0.06); //норматив для жесткости
        //норматив для кальция
        float normCalc;
        if (calciumRES <= 2) {
            normCalc = (float) (calciumRES*0.22);
        } else {
            if (calciumRES <= 10){
                normCalc = (float) (calciumRES*0.14);
            } else {
                normCalc = (float) (calciumRES*0.08);
            }
        }
        float normMagnesium = (float) (magnesiumRES*0.02);
        float deltaHard = Math.abs(volimeHARD1-volimeHARD2);
        float deltaCalc = Math.abs(volumecalcium1 - volumecalcium2);
        float deltaMagnesium = (float)Math.abs((((volimeHARD1-volumecalcium1)*concentration*24.32*1000)/volume)-(((volimeHARD2-volumecalcium2)*concentration*24.32*1000)/volume));

        //проверка на условие сходимости
        boolean truecalc;
        boolean trueHard;
        boolean truMagnesium;
        if (deltaHard <= normHARD){
            trueHard = true;
        } else trueHard = false;

        if (deltaCalc <= normCalc){
            truecalc = true;
        } else truecalc = false;

        if (deltaMagnesium <= normMagnesium){
            truMagnesium = true;
        } else truMagnesium = false;

        //погрешности
        float pogrCalc;
        float pogrHard = (float) (hardRES*0.09);
        float pogrMagn = (float) (magnesiumRES*0.02);
        if (calciumRES <= 2) {
            pogrCalc = (float)(calciumRES*0.25);
        } else if (calciumRES <= 10) {
            pogrCalc = (float)(calciumRES*0.15);
        } else pogrCalc = (float)(calciumRES *0.11);

        if (trueHard == true && truecalc == true && truMagnesium == true) {
            System.out.print("Жесткость ");
            Hardness.znakiposlezapyatoy(hardRES,pogrHard);
            System.out.print("Кальций ");
            Hardness.znakiposlezapyatoy(calciumRES,pogrCalc);
            System.out.print("Магний ");
            Hardness.znakiposlezapyatoy(magnesiumRES,pogrMagn);
        } else System.out.println("не удовлетворяет условию сходимости");
    }

     public static void znakiposlezapyatoy (float result, float pogreshnost) {
         BigDecimal resPOGR = new BigDecimal(pogreshnost);
         BigDecimal res = new BigDecimal(result);
         if (pogreshnost >= 3) {
             resPOGR = resPOGR.setScale(0, RoundingMode.HALF_EVEN);
             res = res.setScale(0, RoundingMode.HALF_EVEN);
             System.out.println(res + " ± " + resPOGR);
         }
         if (pogreshnost <= 3 & pogreshnost >= 0.3) {
             resPOGR = resPOGR.setScale(1, RoundingMode.HALF_EVEN);
             res = res.setScale(1, RoundingMode.HALF_EVEN);
             System.out.println(res + " ± " + resPOGR);
         }
         if (pogreshnost <= 0.3 & pogreshnost >= 0.03) {
             resPOGR = resPOGR.setScale(2, RoundingMode.HALF_EVEN);
             res = res.setScale(2, RoundingMode.HALF_EVEN);
             System.out.println(res + " ± " + resPOGR);
         }
         if (pogreshnost <= 0.03 & pogreshnost >= 0.003) {
             resPOGR = resPOGR.setScale(3, RoundingMode.HALF_EVEN);
             res = res.setScale(3, RoundingMode.HALF_EVEN);
             System.out.println(res + " ± " + resPOGR);
         }
         if (pogreshnost < 0.003 & pogreshnost >= 0.0003) {
             resPOGR = resPOGR.setScale(4, RoundingMode.HALF_EVEN);
             res = res.setScale(4, RoundingMode.HALF_EVEN);
             System.out.println(res + " ± " + resPOGR);
         };
    }
}
