package com.ganht.algorithm.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@Warmup(iterations = 1)
@Measurement(iterations = 2, time = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2)
@Threads(8)
@State(Scope.Benchmark)
@OperationsPerInvocation
public class TestOptional {

    @Param({"10000000"})
    private int n;

    @Setup
    public void setup() {
    }

    @TearDown
    public void tearDown() {
    }

    public void withOptional(String type, int srcServerId){
        Optional.ofNullable(type)
                .filter("2"::equals)
                .filter(t -> srcServerId > 0)
                .map(t -> true)
                .orElse(false);
    }

    @Benchmark
    public void testOptional(Blackhole blackhole) {
        withOptional("1", 100);
    }

    public boolean withoutOptional(String type, int srcServerId){
        if ("1".equals(type)) {
            return true;
        } else {
            return false;
        }
    }

    @Benchmark
    public void test(Blackhole blackhole) {
        withoutOptional("1", 100);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestOptional.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
