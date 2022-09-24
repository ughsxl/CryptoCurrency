package com.cryptocurrency.packages.common

/**
 * @author Krupko Illa
 * Created 23.09.2022 at 17:53
 */

class ExceptionHandler(private val e: Exception) {

    private interface Abstract {
        suspend fun handle(e: Exception, block: suspend (e: Exception) -> Unit)
    }

    inner class Handle(private val block: suspend (e: Exception) -> Unit) : Abstract {

        suspend operator fun invoke() = handle(e, block)

        override suspend fun handle(e: Exception, block: suspend (e: Exception) -> Unit) {
            block.invoke(e)
        }

    }

}