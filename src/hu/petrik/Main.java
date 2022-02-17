package hu.petrik;

import hu.petrik.data.Fuvarok;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fuvarok fuvarok = new Fuvarok("fuvar.csv");
        Fuvarok rovidNemNullak = fuvarok.getSortedNonZeroDistancedRides();
        String megoldasok = String.format(
                "Az állományba összesen %d darab utazás került feljegyzésre.;" +
                        "A 6185-ös azonosítójú taxis %d fuvarból összesen %f dollár bevételre tett szert.;" +
                        "A taxisok összesen %s mérföldet tettek meg.;" +
                        "Az időben leghosszabb fuvar: %s;" +
                        "A legbőkezűbb fuvar: %s;" +
                        "A 4261-es azonosítójú taxis összesen %s kilómétert tett meg.;" +
                        "%d darab hibás fuvar van feljegyezve, aminek összideje %d és összértéke %f dollár.;" +
                        "1452-es azonosítójú %s szerepel a nyilvántartásban.;" +
                        "A három időben legrövidebutazás: \n%s\n%s\n%s;" +
                        "December 24-én %d fuvar volt.;" +
                        "december 31-én %f arányban adtak borravalót a fuvarok után."
                ,
                fuvarok.getfuvarCount(),
                fuvarok.countRidesById(6185), fuvarok.sumIncomeById(6185),
                Double.toString(fuvarok.getDistanceSum()).replace('.', ','),
                fuvarok.getLongestRide().toString(),
                fuvarok.getMostGenerousRide().toString(),
                Double.toString(fuvarok.getDistanceSumKMById(4261)).replace('.', ','),
                fuvarok.getInvalidsCount(), fuvarok.getInvalidTimeSum(), fuvarok.getInvalidIncome(),
                fuvarok.hasId(1452) ? "" : "nem",
                rovidNemNullak.getFuvar(0).toString(), rovidNemNullak.getFuvar(1).toString(), rovidNemNullak.getFuvar(2).toString(),
                fuvarok.getRideCountByMonthAndDay(12, 24),
                fuvarok.getTipRatioByMonthAndDay(12, 31)
        );
        megoldasKiiratas(megoldasok.split(";"));
    }

    public static void megoldasKiiratas(String[] megoldasok) {
        int feladat = 0;
        for (String megoldas : megoldasok) {
            feladat++;
            feladatMegoldas(feladat, megoldas);
        }
    }

    public static void feladatMegoldas(int feladat, String megoldas) {
        System.out.println('\n' + feladat + ". Feladat:\n" + megoldas);
    }
}
