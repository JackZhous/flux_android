package com.jack.retrofitrxjava2.model;

/**
 * Created by jackzhous on 2017/4/25.
 */

public class BoxDetailRecieve {
    private int noramlCount;
    private int changeCount;
    private int totalCount;
    private int openCount;
    private int lostCount;
    private int unChangeCount;
    private int lowPowerCount;

    public int getNoramlCount() {
        return noramlCount;
    }

    public void setNoramlCount(int noramlCount) {
        this.noramlCount = noramlCount;
    }

    public int getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(int changeCount) {
        this.changeCount = changeCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getOpenCount() {
        return openCount;
    }

    public void setOpenCount(int openCount) {
        this.openCount = openCount;
    }

    public int getLostCount() {
        return lostCount;
    }

    public void setLostCount(int lostCount) {
        this.lostCount = lostCount;
    }

    public int getUnChangeCount() {
        return unChangeCount;
    }

    public void setUnChangeCount(int unChangeCount) {
        this.unChangeCount = unChangeCount;
    }

    public int getLowPowerCount() {
        return lowPowerCount;
    }

    public void setLowPowerCount(int lowPowerCount) {
        this.lowPowerCount = lowPowerCount;
    }
}
