package com.bl.ipl;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCsvBuilder();
    }
}
