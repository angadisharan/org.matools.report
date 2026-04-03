package org.matools.report.generator;

public interface ReportGenerator<T> {
    byte[] generate(T input);
}