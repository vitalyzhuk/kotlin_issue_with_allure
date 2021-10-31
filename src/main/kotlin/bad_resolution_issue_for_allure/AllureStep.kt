package bad_resolution_issue_for_allure

import io.qameta.allure.Allure
import io.qameta.allure.model.Status
import io.qameta.allure.model.StepResult
import io.qameta.allure.util.ResultsUtils.getStatus
import io.qameta.allure.util.ResultsUtils.getStatusDetails
import java.util.UUID

inline fun <T> step(name: String, crossinline block: () -> T): T {
    val uuid = UUID.randomUUID().toString()
    Allure.getLifecycle().startStep(uuid, StepResult().setName(name))
    return try {
        val result: T = block.invoke()
        Allure.getLifecycle().updateStep(uuid) {
            it.status = Status.PASSED
        }
        result
    } catch (ex: Exception) {
        Allure.getLifecycle().updateStep {
            it.status = getStatus(ex).orElse(Status.BROKEN)
            it.statusDetails = getStatusDetails(ex).orElse(null)
        }
        throw ex
    } finally {
        Allure.getLifecycle().stopStep(uuid)
    }
}