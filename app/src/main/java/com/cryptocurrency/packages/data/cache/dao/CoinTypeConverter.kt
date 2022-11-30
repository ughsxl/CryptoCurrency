package com.cryptocurrency.packages.data.cache.dao

import androidx.room.TypeConverter
import com.cryptocurrency.packages.data.cloud.model.common.Tag
import com.google.gson.Gson

/**
 * @author Krupko Illa
 * @since 17.11.2022 is created
 */
class CoinTypeConverter {

	@TypeConverter
	fun fromTags(tags: List<Tag>): String = Gson().toJson(tags)

	@TypeConverter
	fun toTags(names: String): List<Tag> = Gson().fromJson(names, Array<Tag>::class.java).toList()


	@TypeConverter
	fun fromTeamMembers(members: List<TeamMemberDao>): String = Gson().toJson(members)

	@TypeConverter
	fun toTeamMembers(json: String): List<TeamMemberDao> = Gson().fromJson(json, Array<TeamMemberDao>::class.java).toList()

}