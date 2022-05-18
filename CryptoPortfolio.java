
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CryptoPortfolio {
    public static void main(String[] args) {
        String filename, source;

        if(args.length==2){
            filename=args[1];
            source=args[0];
        }else if(args.length==1){
            filename="instrument.csv";
            source=args[0];
        }else{
            System.out.println("Invalid argument/s");
            return;
        }


        //Do not modify the code in the try/catch block below.
        try {
            HashMap<String, Double> counts = aggregateCounts(filename);
            Graph g = new Graph(counts);
            Map<String, Double> flows = g.findFlows(source);
            System.out.println(g.sortFlows(flows));
        } catch (FileNotFoundException e) {
            System.out.println(filename + " does not exist");
            System.exit(1);
        }
    }

    /**
     * aggregateCounts opens the csv file using the filename parameter and aggregate
     * trade (transaction) counts over the same cryptocurrency Base Asset and Quote Asset pair.
     * @param String filename represents the csv file name
     * @returns HashMap<String, Double> list of "Base Asset, Quote Asset" and total trade counts.
     * @throws FileNotFoundException if the file does not exist.
     */
    private static HashMap<String, Double> aggregateCounts(String filename) throws FileNotFoundException {
        HashMap<String, Double> counts = new HashMap<>();
        Scanner scanner=new Scanner(new FileReader(filename));
        scanner.nextLine();
        while (scanner.hasNextLine()){
            String[] data=scanner.nextLine().split(",");
            String toFrom=data[3]+","+data[5];
            Double tradeCounts=Double.parseDouble(data[12]);
            if(counts.containsKey(toFrom)){
                counts.replace(toFrom,counts.get(toFrom)+tradeCounts);
            }else{
                counts.put(toFrom,tradeCounts);
            }
        }

        return counts;
    }
}
