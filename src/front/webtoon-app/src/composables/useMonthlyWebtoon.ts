import {computed} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getMonthlyRecommendedWebtoon} from '@/api/webtoon'
import {extractValidData} from "@/utils/extractValidData";


export const useMonthlyRecommendedWebtoon = () => {
    const query = useQuery({

        queryKey: computed(() => ['monthlyRecommendedWebtoon']),
        queryFn: async () => {
            const result = await getMonthlyRecommendedWebtoon()
            return extractValidData(result)
        },
        enabled: true,
        staleTime: 1000 * 10,
    })

    return {
        ...query,
    }
}