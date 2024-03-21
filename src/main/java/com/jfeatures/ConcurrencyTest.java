package com.jfeatures;


import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

// See jcstress-samples or existing tests for API introduction and testing guidelines

//Atomic integer
@JCStressTest

// These are the test outcomes.

@Outcome(id = "2, 1", expect = Expect.ACCEPTABLE, desc = "actor1 incremented, then actor2.")
@Outcome(id = "1, 2", expect = Expect.ACCEPTABLE, desc = "actor1 incremented, then actor2.")
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "actor2 incremented, then actor1.")

// This is a state object
@State()
public class ConcurrencyTest {
    int v = 0;

    @Actor
    public void actor1(II_Result r) {
        r.r1 = ++v; // record result from actor1 to field r1
    }

    @Actor
    public void actor2(II_Result r) {
        r.r2 = ++v; // record result from actor2 to field r2
    }
}


/* @JCStressTest

@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE, desc = "")

@State
public class ConcurrencyTest {

    @Actor
    public void actor1(II_Result result) {
            result.r1 = StringUtils.isJsonObject("1234") ? 1 : 0;
    }

    @Actor
    public void actor2(II_Result result) {
            result.r2 = StringUtils.isJsonObject("5678") ? 1 : 0;
    }
} */