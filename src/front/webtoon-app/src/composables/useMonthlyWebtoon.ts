import {computed} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getMonthlyRecommendedWebtoon} from '@/api/webtoon'


export const useMonthlyRecommendedWebtoon = () => {
    const query = useQuery({

        queryKey: computed(() => ['monthlyRecommendedWebtoon']),
        queryFn: async () => {
            const res = await getMonthlyRecommendedWebtoon()
            return res.result
        },
        enabled: true,
        staleTime: 1000 * 10,
    })

    return {
        ...query,
    }
}