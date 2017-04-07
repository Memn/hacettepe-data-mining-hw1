package memin.hacettepe.datamining.homeworks.hw1;

import weka.associations.Apriori;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Memin on 6.04.2017.
 * For assignment 1 of Data-Mining class.
 */
public class Main {

    public static void main(String[] args) {

        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(classLoader.getResource("mushrooms/agaricus-lepiota.data").getFile());
//        String[] files = dir.list();
//        for (int i = 0; i < files.length; i++) {
//            if (files[i].endsWith(".txt"))

        Instances data = getInstances(file);

        try {
            Apriori apriori = new Apriori();
//            apriori.setLowerBoundMinSupport(3);
            printInstance(data.get(0));
            System.out.println("size: " + data.size());
            apriori.buildAssociations(data);
            System.out.println("num rules:" + apriori.getNumRules());
            System.out.println("rule metric names: " + Arrays.toString(apriori.getRuleMetricNames()));
            System.out.println("technical info: " + apriori.getTechnicalInformation());
            System.out.println("metric str: " + apriori.metricString());
            System.out.println("verbose: " + apriori.verboseTipText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Instances getInstances(File atlanta) {
        Instances data = null;
        CSVLoader loader = new CSVLoader();
        loader.setNoHeaderRowPresent(true);
        try {
            loader.setSource(atlanta);
            data = loader.getDataSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void printInstance(Instance instance) {
        System.out.println(instance.toString());
    }
}
