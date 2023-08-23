package no.kristiania.orderservice.extensions

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class TestContainerExtensions: BeforeAllCallback, AfterAllCallback {
    private val rabbitDockerImage = DockerImageName.parse("rabbitmq:3.11.3-alpine")

    @Container
    private val rabbitContainer = GenericContainer(rabbitDockerImage).withExposedPorts(5672)

    override fun beforeAll(p0: ExtensionContext?) {
        rabbitContainer.start()
        println(rabbitContainer.host)
        println(rabbitContainer.firstMappedPort)
    }

    override fun afterAll(p0: ExtensionContext?) {
        rabbitContainer.stop()
    }
}