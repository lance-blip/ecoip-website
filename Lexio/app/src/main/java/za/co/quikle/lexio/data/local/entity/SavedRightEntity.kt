package za.co.quikle.lexio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_rights")
data class SavedRightEntity(
    @PrimaryKey
    val id: String,
    val categoryId: String,
    val topicId: String,
    val savedAt: Long
)
