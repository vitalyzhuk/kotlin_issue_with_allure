import io.qameta.allure.Allure.step
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

@DisplayName("Main Suite")
class MainSuite {
    @Test
    @DisplayName("Reproduce type collision issue with allure")
    fun typeCollisionAllureIssue() {
        // in "bad_resolution_issue_for_allure" directory you can check how we fix it for now
        step("step name") {

        }
    }

    @Test
    @DisplayName("Reproduce type collision issue with AssertJ")
    fun typeCollisionAssertJIssue() {
        data class Bar(val bar1: String, val bar2: String)
        val foo = listOf("123", "456")
        val bar = listOf(Bar("qwe", "asd"), Bar("123", "fgh"))

        // this can be fixed via ".isSubsetOf(*foo.toTypedArray())"
        assertThat(bar)
            .extracting("bar1")
            .isSubsetOf(foo)
    }
}