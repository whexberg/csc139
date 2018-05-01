package com.company;


import com.company.policies.PagePolicy;
import com.company.policies.PolicyFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Whoops, looks like you are missing something!");
            System.err.println("Usage: java proj4 input file [FIFO|LRU|OPT]");
            return;
        }

        try {
            ParsedFileObject object = initialize(args);

            PagePolicy pagePolicy = PolicyFactory.getPagePolicy(
                    args[1],
                    object.numPages,
                    object.numFrames,
                    object.numRequests
            );
            pagePolicy.processRequests(object.pageReqs);
            pagePolicy.printFaults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ParsedFileObject initialize(String[] args) throws Exception {
        List<Integer> pageReqs = new ArrayList<Integer>();
        int numPages = 0;
        int numFrames = 0;
        int numRequests = 0;

        File file = new File(args[0]);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        String[] split = line.split(" ");
        numPages = Integer.parseInt(split[0]);
        numFrames = Integer.parseInt(split[1]);
        numRequests = Integer.parseInt(split[2]);

        while ((line = bufferedReader.readLine()) != null) {
            pageReqs.add(Integer.parseInt(line));
        }
        fileReader.close();
        return new ParsedFileObject(pageReqs, numPages, numFrames, numRequests);
    }

    private static class ParsedFileObject {
        List<Integer> pageReqs;
        int numPages;
        int numFrames;
        int numRequests;

        public ParsedFileObject(List<Integer> pageReqs, int numPages, int numFrames, int numRequests) {
            this.pageReqs = pageReqs;
            this.numPages = numPages;
            this.numFrames = numFrames;
            this.numRequests = numRequests;
        }

        public List<Integer> getPageReqs() {
            return pageReqs;
        }

        public int getNumPages() {
            return numPages;
        }

        public int getNumFrames() {
            return numFrames;
        }

        public int getNumRequests() {
            return numRequests;
        }
    }
}
