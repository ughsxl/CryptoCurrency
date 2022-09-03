package com.cryptocurrency.packages.domain.mapper

/**
 * @author Krupko Illa
 * Created 02.09.2022 at 17:08
 */

interface Mapper<DataModel, DomainModel> {
    fun map(model: DataModel): DomainModel
}