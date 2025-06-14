package com.toast1ng.webtoon.common

import com.toast1ng.webtoon.common.utils.getLogger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Aspect
@Component
class LoggingAspect {
    private val log = getLogger()

    private enum class LogLevel {
        INFO, TRACE
    }

    @Around("execution(* *(..)) && (within(@org.springframework.stereotype.Service *) || within(@com.toast1ng.webtoon.common.domain.PersistenceAdapter *))")
    fun logMethodExecution(joinPoint: ProceedingJoinPoint): Any? {
        val targetClass = joinPoint.target::class
        val methodName = joinPoint.signature.name

        val logLevel = determineLogLevelByAnnotation(targetClass)

        logWithLevel(logLevel, "== START ==  ${targetClass.simpleName}.$methodName(${joinPoint.args.joinToString(", ")})")

        val result = try {
            joinPoint.proceed()
        } catch (e: Throwable) {
            log.error("== ERROR OCCUR == ${e.message}", e)
            throw e
        }

        logWithLevel(logLevel, "==  END  ==  ${targetClass.simpleName}.$methodName => $result")
        return result
    }

    //TODO Profile에 따라 로그 레벨을 조정할 수 있도록 개선 필요
    private fun determineLogLevelByAnnotation(kClass: KClass<*>): LogLevel {
        return when {
            kClass.hasAnnotation<Service>() -> LogLevel.INFO
            else -> LogLevel.TRACE
        }
    }

    private inline fun <reified T : Annotation> KClass<*>.hasAnnotation(): Boolean {
        return this.annotations.any { it.annotationClass == T::class }
    }

    private fun logWithLevel(level: LogLevel, message: String) {
        when (level) {
            LogLevel.INFO -> log.info(message)
            LogLevel.TRACE -> log.trace(message)
        }
    }
}