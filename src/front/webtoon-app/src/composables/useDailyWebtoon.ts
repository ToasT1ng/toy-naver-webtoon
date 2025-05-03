import {computed, ref} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getDailyWebtoon} from '@/api/webtoon'

const dayOfWeek = ref<string | undefined>(undefined)

export const useDailyWebtoon = () => {
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
        enabled: computed(() => !!dayOfWeek.value),
        staleTime: 1000 * 10,
    })

    return {
        updateDayOfWeek,
        ...query,
    }
}