## Reproducer for Java 9 DateFormat.format() different behaviour compared to Java 8.

[![Build Status](https://travis-ci.org/davido/date_format_java9_produces_different_result_as_java8.svg?branch=master)](https://travis-ci.org/davido/date_format_java9_produces_different_result_as_java8)

Because of Internationalization Enhancements in JDK 9, `DateFormatformat()`
produces different results on Java 9 and Java 8:

```
  @Test
  public void dateFormatResultInJava9DiffersFromJava8() {
    DateFormat enUsFormat =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.US);
    assertEquals("Jan 1, 1970 12:00:00 AM", enUsFormat.format(new Date(0)));
  }
```

While on Java 8 this tests passes, on java 9 it is failing with:

```
ComparisonFailure: expected:<Jan 1, 1970[] 12:00:00 AM> but was:<Jan 1, 1970[,] 12:00:00 AM>
```

To test with Bazel@HEAD, apply this Bazel 9 patch
https://github.com/hhclam/bazel/commit/64212c8026df9fc6361d5af8414acc373e221955
build custom Bazel version and run:

```
bazel test :date_test --host_java_toolchain=@bazel_tools//tools/jdk:jdk9 --java_toolchain=@bazel_tools//tools/jdk:jdk9
```

To enforce JDK 8 compaibility, `-Djava.locale.providers=COMPAT,CLDR,SPI` must
be passed.
