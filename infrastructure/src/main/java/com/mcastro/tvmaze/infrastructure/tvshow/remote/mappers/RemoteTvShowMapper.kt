package com.mcastro.tvmaze.infrastructure.tvshow.remote.mappers

import com.mcastro.tvmaze.domain.schedule.DayOfWeek
import com.mcastro.tvmaze.domain.schedule.Schedule
import com.mcastro.tvmaze.domain.schedule.Time
import com.mcastro.tvmaze.domain.tvshow.Genre
import com.mcastro.tvmaze.domain.tvshow.TvShow
import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.ScheduleRDTO
import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowRDTO

class RemoteTvShowMapper {
    fun toDomainModel(
        tvShowRDTO: TvShowRDTO
    ) = TvShow(
        tvShowRDTO.id,
        tvShowRDTO.name,
        tvShowRDTO.posterUrl,
        mapSchedule(tvShowRDTO.schedule),
        mapGenres(tvShowRDTO.genres),
        tvShowRDTO.summary
    )

    private fun mapSchedule(scheduleRDTO: ScheduleRDTO) : Schedule {
        return Schedule(
            Time(scheduleRDTO.time),
            scheduleRDTO.days.map {
                DayOfWeek.valueOf(it)
            }
        )
    }

    private fun mapGenres(genres: List<String>) = genres.map { Genre(it) }

}