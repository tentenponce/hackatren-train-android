package com.nasaanka.train.domain.common.executor

import java.util.concurrent.Executor

/**
 *
 * Created by Exequiel Egbert V. Ponce on 6/24/2018.
 */
/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * [domain.common.base] UseCases out of the UI thread.
 *
 * Created by Exequiel Egbert V. Ponce on 3/3/2018.
 */

interface ThreadExecutor : Executor