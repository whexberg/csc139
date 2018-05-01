package com.company.policies;

import java.util.List;

public class OptPolicy extends PagePolicy {

    OptPolicy(int numPages, int numFrames, int numRequests) {
        super(numPages, numFrames, numRequests);
    }

    @Override
    protected void nextFrame() {
        int currentFrame = getCurrentFrame();
        if (isFull()) {
            int[] frames = getFrames();
            List<Integer> requests = getRequests();

            int last = 0;

            for (int requestPage : requests) {
                for (int framePage : frames) {
                    if (requestPage == framePage)
                        last = requestPage;
                }
            }
            setCurrentFrame(last);
        } else {
            currentFrame = (currentFrame + 1) % getNumFrames();
            setCurrentFrame(currentFrame);
        }
    }
}
