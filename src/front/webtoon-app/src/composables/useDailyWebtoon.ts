import {computed, ref} from 'vue'
import {useQuery, useQueryClient} from '@tanstack/vue-query'
import {getDailyRecommendedWebtoon, getDailyWebtoon} from '@/api/webtoon'
import {extractValidData} from "@/utils/extractValidData";
import {TDailyWebtoonsSortOption} from "@/features/webtoon/types/sortOptions";

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
    const queryClient = useQueryClient()
    function refetchItems() {
        queryClient.invalidateQueries({ queryKey: ['dailyWebtoon'] })
    }
    const sortOption = ref<TDailyWebtoonsSortOption>('POPULAR')
    function updateSortOption(newSortOption: TDailyWebtoonsSortOption){
        sortOption.value = newSortOption
    }
    const query = useQuery({
        queryKey: computed(() => ['dailyWebtoon', dayOfWeek.value]),
        queryFn: async () => {
            if (!dayOfWeek.value) {
                return Promise.reject(new Error('dayOfWeek is required'))
            }
            const result = await getDailyWebtoon({dayOfWeek: dayOfWeek.value, orderBy: sortOption.value})
            return extractValidData(result)
        },
        enabled: computed(() => !!dayOfWeek.value),
        staleTime: 1000 * 10,
    })

    return {
        updateDayOfWeek,
        updateSortOption,
        refetchItems,
        ...query,
    }
}