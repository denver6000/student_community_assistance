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

        fun normalizeStatus(status: Int): Int {
            return if (STATUS_LIST.contains(status)) status else PENDING
        }

        fun statusLabel(status: Int): String {
            return when (normalizeStatus(status)) {
                PENDING -> "Pending"
                IN_PROGRESS -> "In Progress"
                RESOLVED -> "Resolved"
                else -> "Pending"
            }
        }
    }

}
