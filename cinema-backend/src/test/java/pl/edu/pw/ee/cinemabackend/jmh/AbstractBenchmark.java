package pl.edu.pw.ee.cinemabackend.jmh;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

public abstract class AbstractBenchmark {

    //runtime ~4min 20s
    private final static Integer MEASUREMENT_ITERATIONS = 7;
    private final static Integer MEASUREMENT_TIME = 850;
    private final static Integer WARMUP_ITERATIONS = 2;
    private final static Integer WARMUP_TIME = 350;

    @Test
    public void executeJmhRunner() throws RunnerException {
        if(this.getClass().getSimpleName().contains("_")) {
            return;
        }
        Options opt = new OptionsBuilder()
                .include("\\." + this.getClass().getSimpleName() + "\\.")

                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)

                .warmupTime(TimeValue.milliseconds(WARMUP_TIME))
                .measurementTime(TimeValue.milliseconds(MEASUREMENT_TIME))

                .forks(0)
                .threads(1)
                .measurementBatchSize(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .jvmArgs("-server")

                .mode(Mode.AverageTime)
                .resultFormat(ResultFormatType.TEXT)
                .timeUnit(TimeUnit.MILLISECONDS)
                .result("jmh_wyniki/"+this.getClass().getSimpleName()+".txt")

                .build();

        new Runner(opt).run();
    }
}

