import {computed, ref} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getDailyRecommendedWebtoon, getDailyWebtoon} from '@/api/webtoon'
import {extractValidData} from "@/utils/extractValidData";

const dayOfWeek = ref<string | undefined>(undefined)

const updateDayOfWeek = (newDayOfWeek: string) => {
    dayOfWeek.value = newDayOfWeek
}

export const useDailyRecommendedWebtoon = () => {
    const query = useQuery({
        queryKey: computed(() => ['dailyRecommendedWebtoon', dayOfWeek.value]),
        queryFn: async () => {
            if (!dayOfWeek.value) {
                return Promise.reject(new Error('dayOfWeek is required'))
            }
            const result = await getDailyRecommendedWebtoon({dayOfWeek: dayOfWeek.value})
            return extractValidData(result)
        },
        enabled: computed(() => !!dayOfWeek.value),
        staleTime: 1000 * 10,
    })

    return {
        updateDayOfWeek,
        ...query,
    }
}


export const useDailyWebtoon = () => {
    const query = useQuery({
        queryKey: computed(() => ['dailyWebtoon', dayOfWeek.value]),
        queryFn: async () => {
            if (!dayOfWeek.value) {
                return Promise.reject(new Error('dayOfWeek is required'))
            }
            const result = await getDailyWebtoon({dayOfWeek: dayOfWeek.value})
            return extractValidData(result)
        },
        enabled: computed(() => !!dayOfWeek.value),
        staleTime: 1000 * 10,
    })

    return {
        updateDayOfWeek,
        ...query,
    }
}