package me.rozkmin.testing.strikt

import me.rozkmin.testing.application.DistanceConverter
import me.rozkmin.testing.application.Kilometer
import me.rozkmin.testing.application.Meter
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import strikt.api.expect
import strikt.assertions.isEqualTo
import java.util.stream.Stream

class DistanceConverterParameterizedTest {
    @ParameterizedTest
    @ArgumentsSource(TestInputProvider::class)
    fun `check distance parser`(input: Input) {
        val distanceConverter = DistanceConverter()

        val actual = distanceConverter.parse(input.parameter)
        val expected = input.expected

        expect {
            that(actual).isEqualTo(expected)
        }
    }


    data class Input(val parameter: Meter, val expected: Kilometer)


    class TestInputProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                    Input(500, 0.5),
                    Input(750, 0.8)
            ).map { Arguments.of(it) }
        }
    }
}
