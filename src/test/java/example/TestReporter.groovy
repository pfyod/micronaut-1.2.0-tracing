package example

import io.jaegertracing.internal.JaegerSpan
import io.jaegertracing.spi.Reporter

class TestReporter implements Reporter {
    List<JaegerSpan> spans = []
    @Override
    void report(JaegerSpan span) {
        spans.add(span)
    }

    @Override
    void close() {
        spans.clear()
    }
}
