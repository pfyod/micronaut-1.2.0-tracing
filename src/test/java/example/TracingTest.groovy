package example

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Specification

class TracingTest extends Specification {
    void "test tracing"() {
        given:
        ApplicationContext context = buildContext()
        TestReporter reporter = context.getBean(TestReporter)
        EmbeddedServer embeddedServer = context.getBean(EmbeddedServer).start()
        HttpClient client = context.createBean(HttpClient, embeddedServer.getURL())

        when:
        URL healthUrl = new URL(embeddedServer.getURL().toString() + '/health')
        HttpURLConnection healthCon = (HttpURLConnection) healthUrl.openConnection();
        int healthResponseCode = healthCon.getResponseCode();

        URL pingUrl = new URL(embeddedServer.getURL().toString() + '/ping')
        HttpURLConnection pingCon = (HttpURLConnection) pingUrl.openConnection();
        int pingResponseCode = pingCon.getResponseCode();

        then:
        healthResponseCode == 200
        pingResponseCode == 200
        reporter.spans.size() == 2
        reporter.spans[0].references.size() == 0
        reporter.spans[1].references.size() == 0

        cleanup:
        context.close()
    }

    ApplicationContext buildContext() {
        def reporter = new TestReporter()
        ApplicationContext.build()
        .singletons(reporter)
        .start()
    }
}
