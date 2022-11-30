package com.cryptocurrency.packages.core

import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

/**
 * @author Krupko Illa
 * @since 28.10.2022 is created
 */
internal class TestAbstract {

	private class AbstractMapper : Abstract.Mapper.Data<Int, String> {

		override fun map(data: Int): String = data.toString()

		override fun map(exception: Exception): String = exception.message ?: ""
	}


	private val abstractMapper = AbstractMapper()

	private val abstractObject = mockk<Abstract.Object<String, AbstractMapper>>()


	@Test
	fun `object map(S) returns required result`() {
		every { abstractObject.map(abstractMapper) } returns abstractMapper.map(34)

		assertEquals(abstractObject.map(abstractMapper), "34")

		verifySequence {
			abstractMapper.map(34)
			abstractObject.map(abstractMapper)
		}
	}

	@Test
	fun `object map(S) returns required type`() {
		every { abstractObject.map(abstractMapper) } returns abstractMapper.map(34)

		assertTrue(abstractObject.map(abstractMapper) is String)

		verifySequence {
			abstractMapper.map(34)
			abstractObject.map(abstractMapper)
		}

	}

	@Test
	fun `object map(Exception) returns required result`() {
		val exception = IOException("no connection")

		every { abstractObject.map(abstractMapper) } returns abstractMapper.map(exception)

		assertEquals(abstractObject.map(abstractMapper), "no connection")

		verifySequence {
			abstractMapper.map(exception)
			abstractObject.map(abstractMapper)
		}
	}

	@Test
	fun `object map(Exception) returns required type`() {
		val exception = IOException()

		every { abstractObject.map(abstractMapper) } returns abstractMapper.map(exception)

		assertTrue(abstractObject.map(abstractMapper) is String)

		verifySequence {
			abstractMapper.map(exception)
			abstractObject.map(abstractMapper)
		}
	}

}