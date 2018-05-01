package com.company.policies;

import java.util.List;

public class LruPolicy extends PagePolicy {

    LruPolicy(int numPages, int numFrames, int numRequests) {
        super(numPages, numFrames, numRequests);
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void nextFrame() {
        int currentFrame = getCurrentFrame();
        if (isFull()) {

            int[] frames = getFrames();
            int[] order = new int[frames.length];
            List<Integer> requests = getRequests();

            int lru = Integer.MAX_VALUE;

            for (int outer = 0; outer < getCurrentRequestNumber(); outer++) {
                int requestPage = requests.get(outer);
                for (int inner = 0; inner < frames.length; inner++) {
                    if (requestPage == frames[inner]) {
                        order[inner] = outer;
                    }
                }
            }
            for (int i = 0; i < order.length; i++) {
                if (order[i] < lru) {
                    lru = order[i];
                    setCurrentFrame(i);
                }
            }
        } else {
            currentFrame = (currentFrame + 1) % getNumFrames();
            setCurrentFrame(currentFrame);
        }
    }
}
