package com.example.jmhsample;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Fork(value = 3, warmups = 2)
@Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
public class JmhSampleApplication {

  public static void main(String[] args) throws Exception {
    org.openjdk.jmh.Main.main(args);
  }

  @Benchmark
  @Fork(value = 3, warmups = 2)
  @Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @Measurement(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
  @BenchmarkMode(Mode.Throughput)
//  @BenchmarkMode(Mode.AverageTime)
  public void someMethod() {
    //code
  }

  @State(Scope.Benchmark)
  public static class Params {

    public double a = 1;
    public double b = 1;
  }

  @Benchmark
  public static double add(Params params) {
    new Object(); //dead code elimination
    return params.a + params.b;
  }

  @Benchmark
  public static double addBH(Params params, Blackhole blackhole) {
    blackhole.consume(new Object());
    return params.a + params.b;
  }

  @Benchmark
  public static double addCC(Params params) {
    return params.a + params.b + someOtherMethod(params);
  }

  @CompilerControl(CompilerControl.Mode.INLINE)
  private static double someOtherMethod(Params params) {
    return params.a * params.b;
  }

}
