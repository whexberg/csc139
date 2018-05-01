package com.company.policies;

public class FifoPolicy extends PagePolicy {

    FifoPolicy(int numPages, int numFrames, int numRequests) {
        super(numPages, numFrames, numRequests);
    }

    @Override
    protected void nextFrame() {
        int nextFrame = (getCurrentFrame() + 1) % getNumFrames();
        setCurrentFrame(nextFrame);
    }
}
