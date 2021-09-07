package com.github.romahat.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadResource extends LoadGrpc.LoadImplBase {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoadResource.class);

    @Override
    public void load(com.github.romahat.load.Parameters request,
                     io.grpc.stub.StreamObserver<com.github.romahat.load.Reply> responseObserver) {
        LOGGER.info("Starting load by request: {} times of {} ms",
                request.getIterationCount(),
                request.getIterationLength());
        try {
            long start = System.nanoTime();
            for (int i = 0; i < request.getIterationCount(); i++) {
                spin(request.getIterationLength());
            }
            responseObserver.onNext(Reply.newBuilder()
                    .setMessage("Took " + (System.nanoTime()-start)/1000000 +
                            "ms (expected " + (request.getIterationCount()*500) + ")")
                    .build()
            );
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    private static void spin(int milliseconds) {
        long sleepTime = milliseconds*1000000L; // convert to nanoseconds
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime) {}
    }


}
