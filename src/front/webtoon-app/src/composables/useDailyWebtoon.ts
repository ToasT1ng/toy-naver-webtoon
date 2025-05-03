import {computed, ref} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getDailyWebtoon} from '@/api/webtoon'

export const useDailyWebtoon = () => {
    const dayOfWeek = ref<string | undefined>(undefined)

    const updateDayOfWeek = (newDayOfWeek: string) => {
        dayOfWeek.value = newDayOfWeek
    }

    const query = useQuery({
        queryKey: computed(() => ['dailyWebtoon', dayOfWeek.value]),
        queryFn: async () => {
            if (!dayOfWeek.value) {
                return Promise.reject(new Error('dayOfWeek is required'))
            }
            const res = await getDailyWebtoon({dayOfWeek: dayOfWeek.value})
            return res.result
        },
        enabled: computed(() => !!dayOfWeek.value), // dayOfWeek가 있을 때만 실행
        staleTime: 1000 * 60 * 5,
    })

    return {
        updateDayOfWeek,
        ...query,
    }
}