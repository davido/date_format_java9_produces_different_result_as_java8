java_test(
    name = "date_test",
    srcs = glob(["src/test/java/**/*.java"]),
    test_class = "org.ostrovsky.date.DateFormatTest",
    deps = ["@junit//jar"],
    jvm_flags = ["-Djava.locale.providers=COMPAT,CLDR,SPI"],
)
