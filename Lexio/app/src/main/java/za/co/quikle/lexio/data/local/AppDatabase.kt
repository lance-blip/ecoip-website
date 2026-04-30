package za.co.quikle.lexio.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import za.co.quikle.lexio.data.local.dao.ConversationDao
import za.co.quikle.lexio.data.local.dao.SavedRightsDao
import za.co.quikle.lexio.data.local.entity.ConversationEntity
import za.co.quikle.lexio.data.local.entity.MessageEntity
import za.co.quikle.lexio.data.local.entity.SavedRightEntity

@Database(
    entities = [
        ConversationEntity::class,
        MessageEntity::class,
        SavedRightEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun conversationDao(): ConversationDao
    abstract fun savedRightsDao(): SavedRightsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lexio_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
