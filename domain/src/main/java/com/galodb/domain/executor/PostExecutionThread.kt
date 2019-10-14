package com.galodb.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}
