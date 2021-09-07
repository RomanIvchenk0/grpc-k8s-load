package com.github.romahat.load;

public class Status {
    private Integer iterationCount;
    private Integer iterationLength;
    private int queueSize;

    public Integer getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(Integer iterationCount) {
        this.iterationCount = iterationCount;
    }

    public Integer getIterationLength() {
        return iterationLength;
    }

    public void setIterationLength(Integer iterationLength) {
        this.iterationLength = iterationLength;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public static final class StatusBuilder {
        private Integer iterationCount;
        private Integer iterationLength;
        private int queueSize;

        private StatusBuilder() {
        }

        public static StatusBuilder aStatus() {
            return new StatusBuilder();
        }

        public StatusBuilder withIterationCount(Integer iterationCount) {
            this.iterationCount = iterationCount;
            return this;
        }

        public StatusBuilder withIterationLength(Integer iterationLength) {
            this.iterationLength = iterationLength;
            return this;
        }

        public StatusBuilder withQueueSize(int queueSize) {
            this.queueSize = queueSize;
            return this;
        }

        public Status build() {
            Status status = new Status();
            status.iterationCount = this.iterationCount;
            status.iterationLength = this.iterationLength;
            status.queueSize = this.queueSize;
            return status;
        }
    }
}
