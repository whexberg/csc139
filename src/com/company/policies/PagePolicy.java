package com.company.policies;

import java.util.List;

public abstract class PagePolicy {
    private final int mNumPages;
    private final int mNumFrames;
    private final int mNumRequests;
    private int mCurrentFrame;
    private int mCurrentRequest;
    private int faults;
    private int[] mFrames;
    private List<Integer> mRequests;

    PagePolicy(int numPages, int numFrames, int numRequests) {
        mNumPages = numPages;
        mNumFrames = numFrames;
        mNumRequests = numRequests;
        mFrames = new int[mNumFrames];
        for (int i = 0; i < mFrames.length; i++) {
            mFrames[i] = -1;
        }
        faults = 0;

    }

    public int getCurrentRequestNumber() {
        return mCurrentRequest;
    }

    public int getNumPages() {
        return mNumPages;
    }

    public int getNumFrames() {
        return mNumFrames;
    }

    public int getNumRequests() {
        return mNumRequests;
    }

    public int getCurrentFrame() {
        return mCurrentFrame;
    }

    public void setCurrentFrame(int mCurrentFrame) {
        this.mCurrentFrame = mCurrentFrame;
    }

    public int[] getFrames() {
        return mFrames;
    }

    public void setFrames(int[] mFrames) {
        this.mFrames = mFrames;
    }

    public int getFaults() {
        return faults;
    }

    public void setFaults(int faults) {
        this.faults = faults;
    }

    public void addFault() {
        this.faults++;
    }

    public boolean frameIsEmpty(int frame) {
        return mFrames[frame] == -1;
    }

    public int getFramePage(int frame) {
        return mFrames[frame];
    }

    public void setFramePage(int frame, int page) {
        mFrames[frame] = page;
    }

    public List<Integer> getRequests() {
        return mRequests;
    }

    public void setRequests(List<Integer> mRequests) {
        this.mRequests = mRequests;
    }

    public void printFaults() {
        String output = String.format("%d page faults", getFaults());
        System.out.println(output);
    }


    public void processRequests(List<Integer> requests) {
        mRequests = requests;
        mCurrentRequest = 1;

        for (Integer page : mRequests) {
            if (page < getNumPages()) {
                processRequest(page, getCurrentFrame());
            }
            mCurrentRequest++;
        }
    }

    public void processRequest(int page, int frame) {
        int loaded = alreadyLoaded(page);

        if (loaded != -1) {
            String output = String.format("Page %d already in Frame %d", page, loaded);
            System.out.println(output);
            return;
        } else {
            addFault();
        }

        if (frameIsEmpty(frame)) {
            loadPage(page, frame);
        } else {
            unloadPage(frame);
            loadPage(page, frame);
        }
    }

    void unloadPage(int frame) {
        String output = String.format("Page %d unloaded from Frame %d, ", getFramePage(frame), frame);
        System.out.print(output);
    }

    void loadPage(int page, int frame) {
        setFramePage(frame, page);
        String output = String.format("Page %d loaded into Frame %d", page, frame);
        System.out.println(output);
        nextFrame();
    }

    protected boolean isFull() {
        boolean full = false;
        for (int i = 0; i < getNumFrames(); i++) {
            full = mFrames[i] != -1;
        }
        return full;
    }

    protected abstract void nextFrame();

    int alreadyLoaded(int page) {
        for (int i = 0; i < getNumFrames(); i++) {
            if (getFramePage(i) == page)
                return i;
        }
        return -1;
    }
}
