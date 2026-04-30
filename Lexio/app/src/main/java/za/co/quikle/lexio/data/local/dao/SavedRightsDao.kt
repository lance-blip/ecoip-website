package za.co.quikle.lexio.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import za.co.quikle.lexio.data.local.entity.SavedRightEntity

@Dao
interface SavedRightsDao {

    @Query("SELECT * FROM saved_rights ORDER BY savedAt DESC")
    fun getAllSavedRights(): Flow<List<SavedRightEntity>>

    @Query("SELECT * FROM saved_rights WHERE categoryId = :categoryId")
    fun getSavedRightsByCategory(categoryId: String): Flow<List<SavedRightEntity>>

    @Query("SELECT * FROM saved_rights WHERE id = :id")
    suspend fun getSavedRightById(id: String): SavedRightEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedRight(savedRight: SavedRightEntity)

    @Delete
    suspend fun deleteSavedRight(savedRight: SavedRightEntity)

    @Query("DELETE FROM saved_rights WHERE id = :id")
    suspend fun deleteSavedRightById(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_rights WHERE topicId = :topicId)")
    suspend fun isRightSaved(topicId: String): Boolean
}
