package com.bl.ipl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class IPLAnalyser {
    List<IPLRuns> IPLCSVList = null;

    public int loadIPLData(String csvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            IPLCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IPLRuns.class);
            return IPLCSVList.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }


}
