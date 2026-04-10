package xyz.denprog.studentcommunityassitance.constants

class ProjectConstants {

    companion object {
        const val PENDING = 1
        const val IN_PROGRESS = 2
        const val RESOLVED = 3

        val STATUS_LIST = intArrayOf(
            PENDING,
            IN_PROGRESS,
            RESOLVED
        )
    }

}