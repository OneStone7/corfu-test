package com.example;

import java.util.Map;

import org.corfudb.runtime.CorfuRuntime;
import org.corfudb.runtime.collections.CorfuTable;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        CorfuRuntime rt = new CorfuRuntime("localhost:9999");

        rt.connect();

        Map<String, Integer> map = rt.getObjectsView().build().setStreamName("A").setType(CorfuTable.class).open();

        Integer previous = map.get("a");
        if (previous == null) {
            System.out.println("This is the first time we were run!");
            map.put("a", 1);
        } else {
            map.put("a", ++previous);
            System.out.println("This is the " + previous + " time we were run!");
        }
    }
}
