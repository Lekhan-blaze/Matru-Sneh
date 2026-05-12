package com.matrusineh.health.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Kick::class,
        Checkup::class,
        NutritionLog::class,
        DangerLog::class,
        MotherProfile::class,
        FoodItem::class          // ← new
    ],
    version = 2,                 // ← bumped for schema change
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun kickDao(): KickDao
    abstract fun checkupDao(): CheckupDao
    abstract fun nutritionDao(): NutritionDao
    abstract fun dangerLogDao(): DangerLogDao
    abstract fun motherProfileDao(): MotherProfileDao
    abstract fun foodItemDao(): FoodItemDao   // ← new

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        /** Seed data inserted into food_item on first DB creation */
        private val FOOD_SEED = listOf(
            FoodItem(0,  "Ragi",         "ರಾಗಿ",          "🌾"),
            FoodItem(1,  "Leafy Greens", "ಸೊಪ್ಪು",         "🥬"),
            FoodItem(2,  "Pulses / Dal", "ಬೇಳೆ",          "🫘"),
            FoodItem(3,  "Milk",         "ಹಾಲು",          "🥛"),
            FoodItem(4,  "Egg",          "ಮೊಟ್ಟೆ",         "🥚"),
            FoodItem(5,  "Banana",       "ಬಾಳೆಹಣ್ಣು",      "🍌"),
            FoodItem(6,  "Water (8 glasses)", "ನೀರು (8 ಲೋಟ)", "💧"),
            FoodItem(7,  "Iron Tablet",  "ಕಬ್ಬಿಣದ ಮಾತ್ರೆ",  "💊"),
            FoodItem(8,  "Jaggery",      "ಬೆಲ್ಲ",          "🍯"),
            FoodItem(9,  "Groundnuts",   "ಕಡಲೆಕಾಯಿ",      "🥜")
        )

        /** Callback — runs only when DB is created for the very first time */
        private val seedCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        database.foodItemDao().insertAll(FOOD_SEED)
                    }
                }
            }
        }

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "matru_sneh_db"
                )
                    .fallbackToDestructiveMigration()  // dev-only; replace with Migration in prod
                    .addCallback(seedCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
