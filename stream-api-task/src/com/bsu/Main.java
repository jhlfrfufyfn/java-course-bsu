package com.bsu;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    final private static String GUITAR_DATABASE_FILENAME = "data/database.csv";

    private static List<Guitar> guitarList;

    private static Stream findByManufacturer(String str){
        return guitarList.stream().filter(o -> o.getManufacturer().equalsIgnoreCase(str));
    }


    public static void main(String[] args) {
        guitarList = new ArrayList<Guitar>();

        try (Scanner sc = new Scanner(new File(GUITAR_DATABASE_FILENAME))) {
            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(";");
                guitarList.add(Guitar.read(line));
            }
        } catch (Exception ex) {
            System.err.println("Error in reading from \"" + GUITAR_DATABASE_FILENAME + "\"");
        }

        ///get all the guitars that were manufactured by "adams"(filter)
        findByManufacturer("adams").forEach(System.out::println);
        System.out.print("\n");

        ///find biggest price(map, reduce)
        int maxPrice = guitarList.stream().map(Guitar::getCost).reduce(Integer::max).orElse(-1);
        System.out.println("maxPrice = " + maxPrice);
        System.out.print("\n");

        ///map of manufacturers and models(toMap)
        Map<String, String> map = guitarList.stream().collect(Collectors.toMap(Guitar::getModel,Guitar::getManufacturer));
        System.out.println(map);
        System.out.print("\n");

        ///find first in stream(findFirst)
        System.out.println(guitarList.stream().findFirst().orElse(null));
        System.out.print("\n");

        ///find any in stream(findAny)
        System.out.println(guitarList.stream().findAny().orElse(null));
        System.out.print("\n");

        ///tries to find fender(anyMatch)
        if(guitarList.stream().anyMatch(o->o.getManufacturer().equals("fender"))){
            System.out.println("fender found!");
        }
        else{
            System.out.println("fender not found!");
        }
        System.out.print("\n");

        ///group guitars by manufacturer(groupingBy)
        System.out.println(guitarList.stream().collect(Collectors.groupingBy(Guitar::getManufacturer)));
        System.out.print("\n");

    }

}
