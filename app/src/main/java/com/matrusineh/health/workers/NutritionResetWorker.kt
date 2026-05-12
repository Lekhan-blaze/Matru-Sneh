package com.matrusineh.health.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.matrusineh.health.data.db.AppDatabase
import com.matrusineh.health.data.repository.NutritionRepository
import java.text.SimpleDateFormat
import java.util.*

class NutritionResetWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val db = AppDatabase.getInstance(applicationContext)
        val repo = NutritionRepository(db.nutritionDao())
        repo.deleteOldLogs(todayStr)
        return Result.success()
    }
}
