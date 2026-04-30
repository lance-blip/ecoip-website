package za.co.quikle.lexio.data.repository

import kotlinx.coroutines.flow.Flow
import za.co.quikle.lexio.data.local.dao.SavedRightsDao
import za.co.quikle.lexio.data.local.entity.SavedRightEntity
import java.util.UUID

class RightsRepository(
    private val savedRightsDao: SavedRightsDao
) {
    fun getSavedRights(): Flow<List<SavedRightEntity>> {
        return savedRightsDao.getAllSavedRights()
    }

    fun getSavedRightsByCategory(categoryId: String): Flow<List<SavedRightEntity>> {
        return savedRightsDao.getSavedRightsByCategory(categoryId)
    }

    suspend fun saveRight(categoryId: String, topicId: String) {
        val savedRight = SavedRightEntity(
            id = UUID.randomUUID().toString(),
            categoryId = categoryId,
            topicId = topicId,
            savedAt = System.currentTimeMillis()
        )
        savedRightsDao.insertSavedRight(savedRight)
    }

    suspend fun unsaveRight(id: String) {
        savedRightsDao.deleteSavedRightById(id)
    }

    suspend fun isRightSaved(topicId: String): Boolean {
        return savedRightsDao.isRightSaved(topicId)
    }
}
