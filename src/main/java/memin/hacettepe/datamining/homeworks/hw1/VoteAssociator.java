package memin.hacettepe.datamining.homeworks.hw1;

import weka.associations.Apriori;
import weka.associations.FPGrowth;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.InputStream;

/**
 * Data mining Assignment-1.
 * Created by Memin on 10.04.2017.
 */
public class VoteAssociator {

    public static void main(String[] args) {
        InputStream stream = VoteAssociator.class.getClassLoader().getResourceAsStream("vote.arff");

        // Read file
        Instances instances = null;
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(stream);
            instances = source.getDataSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // find rules for Apriori Algorithm
        Apriori apriori = new Apriori();
        try {
            long startTime = System.nanoTime();
            apriori.mineCARs(instances);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Apriori: " + duration / 1000000 + " ms");
            System.out.println(apriori.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // find rules for FPGrowth Algorithm
        FPGrowth fpGrowth = new FPGrowth();
        fpGrowth.setRulesMustContain("Class");
        try {
            long startTime = System.nanoTime();
            fpGrowth.buildAssociations(instances);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            System.out.println("FPGrowth: " + duration / 1000000 + " ms");
            System.out.println(fpGrowth.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
