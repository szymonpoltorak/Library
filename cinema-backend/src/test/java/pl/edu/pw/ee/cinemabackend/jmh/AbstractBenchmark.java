package pl.edu.pw.ee.cinemabackend.jmh;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

abstract public class AbstractBenchmark {

    private final static Integer MEASUREMENT_ITERATIONS = 1;
    private final static Integer MEASUREMENT_TIME = 5;
    private final static Integer WARMUP_ITERATIONS = 1;
    private final static Integer WARMUP_TIME = 1;


    @Test
    public void executeJmhRunner() throws RunnerException {
        if(this.getClass().getSimpleName().contains("_")) {
            return;
        }
        Options opt = new OptionsBuilder()
                .include("\\." + this.getClass().getSimpleName() + "\\.")
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .warmupTime(TimeValue.seconds(WARMUP_TIME))
                .measurementTime(TimeValue.seconds(MEASUREMENT_TIME))
                .forks(0)
                .mode(Mode.AverageTime)
                .threads(1)
                .measurementBatchSize(1)
                .measurementIterations(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.JSON)
                .result("jmh_wyniki/"+this.getClass().getSimpleName()+".json")
                .shouldFailOnError(true)
                .jvmArgs("-server")
                .build();

        new Runner(opt).run();
    }
}

