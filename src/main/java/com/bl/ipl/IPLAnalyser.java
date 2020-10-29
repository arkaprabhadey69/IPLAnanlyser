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
    List<IPLWickets> IPLCSVList2=null;

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
    public int loadIPLDataWkts(String csvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            IPLCSVList2 = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IPLWickets.class);
            return IPLCSVList2.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    public String getPlayersWithTopAverages() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingAvg.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> censusComparator = Comparator.comparing(census -> census.avg);
            this.descendingSort(censusComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithTopSR() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSR.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }
    public String getPlayersWithTopSRandBoundary() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandBoundary.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getSixes).thenComparing(ipl->ipl.fours).thenComparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }
    public String getPlayersWithTop6and4() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingBoundary.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.fours+census.sixes);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }
    public String getPlayersWithTopSRandAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingAvg.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getAvg).thenComparing(ipl->ipl.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }
    public String getPlayersWithTopRunsAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingMaxRuns.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getRuns).thenComparing(ipl->ipl.avg);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getBowlersWithTopAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestAvg.json")) {
            if (IPLCSVList2 == null || IPLCSVList2.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.avg);
            this.sort(iplComparator);
            String json = new Gson().toJson(IPLCSVList2);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList2, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }
    public String getBowlersWithTopSR() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSR.json")) {
            if (IPLCSVList2 == null || IPLCSVList2.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.strikeRate);
            this.sort(iplComparator);
            String json = new Gson().toJson(IPLCSVList2);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList2, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }
    public String getBowlersWithTopEcon() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestEconomy.json")) {
            if (IPLCSVList2 == null || IPLCSVList2.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.economy);
            this.sort(iplComparator);
            String json = new Gson().toJson(IPLCSVList2);
            Gson gson = new GsonBuilder().create();
            gson.toJson(IPLCSVList2, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    private void descendingSort(Comparator<IPLRuns> iplComparator) {
        for (int i = 0; i < IPLCSVList.size() - 1; i++) {
            for (int j = 0; j < IPLCSVList.size() - i - 1; j++) {
                IPLRuns ipl1 = IPLCSVList.get(j);
                IPLRuns ipl2 = IPLCSVList.get(j + 1);
                if (iplComparator.compare(ipl1, ipl2) < 0) {
                    IPLCSVList.set(j, ipl2);
                    IPLCSVList.set(j + 1, ipl1);
                }
            }
        }
    }
    private void sort(Comparator<IPLWickets> iplComparator) {
        for (int i = 0; i < IPLCSVList2.size() - 1; i++) {
            for (int j = 0; j < IPLCSVList2.size() - i - 1; j++) {
                IPLWickets ipl1 = IPLCSVList2.get(j);
                IPLWickets ipl2 = IPLCSVList2.get(j + 1);
                if (iplComparator.compare(ipl1, ipl2) > 0) {
                    IPLCSVList2.set(j, ipl2);
                    IPLCSVList2.set(j + 1, ipl1);
                }
            }
        }
    }
    public int getPlayerIndex(IPLWickets[] iplWickets){
        for(int i=0;i< iplWickets.length;i++){
            if(iplWickets[i].strikeRate!=0){
                System.out.println(iplWickets[i].player);
                return i;
            }
        }
        return 1;

    }
    public int getPlayerIndex2(IPLWickets[] iplWickets){
        for(int i=0;i< iplWickets.length;i++){
            if(iplWickets[i].avg!=0){
                System.out.println(iplWickets[i].player);
                return i;
            }
        }
        return 1;

    }


}
