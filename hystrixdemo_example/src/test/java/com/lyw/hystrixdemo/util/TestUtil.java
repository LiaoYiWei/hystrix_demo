package com.lyw.hystrixdemo.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public class TestUtil {

    public static void infiniteExecute(Supplier<Object> objectSupplier) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            CompletableFuture.supplyAsync(objectSupplier, Executors.newFixedThreadPool(20))
                    .whenComplete((v, e) -> {
                        if (e != null) System.out.println("exception occurs" + e);
                        System.out.println(atomicInteger.incrementAndGet() + "---" + v);

                    });
            Thread.sleep(500L);
        }
    }
}
