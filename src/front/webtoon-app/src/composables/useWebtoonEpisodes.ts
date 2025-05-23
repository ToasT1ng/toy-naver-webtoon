import {computed, ref} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getPagingWebtoonEpisodes} from "@/api/webtoonEpisode";

export const useWebtoonEpisodes = () => {
    const pageNo = ref(0)
    const pageSize = ref(10)
    const webtoonId = ref<number | undefined>(undefined)

    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    const query = useQuery({
        queryKey: computed(() => ['webtoonEpisodes', webtoonId.value]),
        queryFn: async () => {
            if (!webtoonId.value) {
                return Promise.reject(new Error('webtoonId is required'))
            }
            return await getPagingWebtoonEpisodes({
                pageNo: pageNo.value,
                pageSize: pageSize.value,
                webtoonId: webtoonId.value
            })
        },
        enabled: () => !!webtoonId.value,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        ...query,
    }
}