package utils;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.opencsv.CSVReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class Utilities {
    public static Integer getUniqueCount(Integer[] sequence){
        return Integer.valueOf(String.valueOf(Arrays.stream(sequence).distinct().count()));
    }

    public static Integer[] generateRandomizedSequence(Integer a, Integer size){
        if(a>size){
            throw new IllegalArgumentException("A is larger than sequence size");
        }
        List<Integer> randSequence = new LinkedList<>();
        Random random = new Random();
        for(int i = 0; i<=size-1; i++){
            randSequence.add(giveRandomInteger(a-1,0,random));
        }
       return randSequence.toArray(new Integer[randSequence.size()]);
    }

    private static Integer giveRandomInteger(int upperBound, int lowerBound, Random aRandom){
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long)upperBound- (long)lowerBound+ 1;
        long fraction = (long)(range * aRandom.nextDouble());
        return (int)(fraction + lowerBound);
    }

    public static Integer[] getCutPositions(Integer[] sequence){
        ListMultimap positionCount= getPositionCount(sequence);
        List<Integer> cutPositions = new LinkedList<>();
        Integer size= positionCount.entries().size();
        for(int i = 0; i<size; i++){
            int count = positionCount.get(i).size();
            if(count!=0){
                cutPositions.add(count);
            }
        }
        return cutPositions.toArray(new Integer[cutPositions.size()]);
    }

    private static ListMultimap getPositionCount(Integer[] sequence){
        ListMultimap<Integer,Integer> positionCount = LinkedListMultimap.create();
        Arrays.stream(sequence).forEach(e->positionCount.put(e,e));
        return positionCount;
    }

    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    public static Integer[] readCSV(String path){
        String csvFile = path;
        CSVReader reader = null;
        List<Integer> row = new LinkedList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                for(String e: line){
                    if(isInteger(e)){
                        row.add(Integer.valueOf(e));
                    }else {
                        throw new IllegalArgumentException("The input is not valid.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row.toArray(new Integer[row.size()]);
    }

    public static void writeToCSV(Integer[] array,String outputDirectory) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(outputDirectory));
        StringBuilder sb = new StringBuilder();
        for (Integer element : array) {
            sb.append(element);
            sb.append(",");
        }

        br.write(String.valueOf(sb.subSequence(0,sb.length()-1)));
        br.close();
    }
}

