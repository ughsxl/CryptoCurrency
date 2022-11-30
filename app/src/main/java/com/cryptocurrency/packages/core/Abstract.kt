package com.cryptocurrency.packages.core


/**
 * @author Krupko Illa
 * @since 26.10.2022 is created
 */
abstract class Abstract {

	abstract class Object<R, M : Mapper> {

		abstract fun map(mapper: M): R

		abstract class Final

	}


	interface Mapper {

		interface Data<S, R> : Mapper {

			fun map(data: S): R

			fun map(exception: kotlin.Exception): R

		}

		interface Exception<S : kotlin.Exception, R> : Mapper {
			fun map(exception: S): R
		}

		interface Empty
	}

}