package memin.hacettepe.datamining.homeworks.hw1;

import weka.associations.Apriori;
import weka.associations.FPGrowth;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Data mining Assignment-1.
 * Created by Memin on 10.04.2017.
 */
public class VoteAssociator {

    private ClassLoader classLoader = VoteAssociator.class.getClassLoader();
    private File dataFile = new File(classLoader.getResource("vote.arff").getFile());

    public static void main(String[] args) {
        VoteAssociator associator = new VoteAssociator();

        // Read file
        Instances instances = null;
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(new FileInputStream(associator.dataFile));
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
