public class Concentrations {

    public static float hardness(float volimeHARD1, float volimeHARD2, double trillonB, int volume) {
        float hardness = (float)(((2*volimeHARD1*trillonB*1000)/volume)+((2*volimeHARD2*trillonB*1000)/volume))/2;
        return hardness;
    };
    public static float calcium (float volumecalcium1, float volumecalcium2, double trillonB, int volume){
        float calcium = (float)(((40.08*trillonB*volumecalcium1*1000)/volume)+((40.08*trillonB*volumecalcium2*1000)/volume))/2;
        return calcium;
    };
    public static float magnesium (float volimeHARD1, float volumecalcium1, float volumecalcium2, float volimeHARD2,double trillonB, int volume){
        float magnesium = (float)((((volimeHARD1-volumecalcium1)*trillonB*24.32*1000)/volume)+(((volimeHARD2-volumecalcium2)*trillonB*24.32*1000)/volume))/2;
        return magnesium;
    };
}