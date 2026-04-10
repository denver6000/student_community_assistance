package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import xyz.denprog.studentcommunityassitance.database.entity.Announcement
import xyz.denprog.studentcommunityassitance.utils.toLocalDateTime

data class AnnouncementView(
    val announcementId: Long,
    val title: String,
    val content: String,
    val publishedAt: LocalDateTime,
    val formattedDate: String,
    val formattedTime: String
) {
    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.getDefault())
        private val timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault())

        fun from(announcement: Announcement): AnnouncementView {
            val publishedAt = announcement.timestamp.toLocalDateTime()
            return AnnouncementView(
                announcementId = announcement.announcementId,
                title = announcement.title,
                content = announcement.content,
                publishedAt = publishedAt,
                formattedDate = publishedAt.format(dateFormatter),
                formattedTime = publishedAt.format(timeFormatter)
            )
        }
    }
}
