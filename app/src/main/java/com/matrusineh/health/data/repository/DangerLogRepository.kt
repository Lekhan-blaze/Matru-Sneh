package com.matrusineh.health.data.repository

import com.matrusineh.health.data.db.DangerLog
import com.matrusineh.health.data.db.DangerLogDao

class DangerLogRepository(private val dangerLogDao: DangerLogDao) {
    suspend fun logDangerSign(signs: String) {
        dangerLogDao.insert(DangerLog(signs = signs))
    }
}
