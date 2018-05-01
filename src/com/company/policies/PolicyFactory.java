package com.company.policies;

public class PolicyFactory {
    public static PagePolicy getPagePolicy(String policyType, int numPages, int numFrames, int numRequests) {
        if (policyType.toUpperCase().equals("FIFO"))
            return new FifoPolicy(numPages, numFrames, numRequests);
        else if (policyType.toUpperCase().equals("LRU"))
            return new LruPolicy(numPages, numFrames, numRequests);
        else if (policyType.toUpperCase().equals("OPT"))
            return new OptPolicy(numPages, numFrames, numRequests);
        else
            throw new IllegalArgumentException("Allowed algorithms: FIFO, LRU, and OPT");
    }
}
