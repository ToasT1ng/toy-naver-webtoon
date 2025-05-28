export type SortDirection = 'ASC' | 'DESC';

export type WeekPageTab = 'all' | 'mon' | 'tue' | 'wed' | 'thu' | 'fri' | 'sat' | 'sun';

export function isWeekPageTab(value: string): value is WeekPageTab {
    return ['all', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'].includes(value)
}