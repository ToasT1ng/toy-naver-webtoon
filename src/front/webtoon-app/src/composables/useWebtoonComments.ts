import {useQuery} from "@tanstack/vue-query";
import {computed, ref} from "vue";
import {extractValidData} from "@/utils/extractValidData";
import {getPagingWebtoonEpisodeComments} from "@/api/webtoonComments";

export const useWebtoonComments = () => {
    const webtoonId = ref<number | undefined>(undefined)
    const episodeId = ref<number | undefined>(undefined)
    const pageNo = ref(1)
    const pageSize = ref(15)

    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    function updateEpisodeId(newEpisodeId: number) {
        episodeId.value = newEpisodeId
    }

    const query = useQuery({
        queryKey: computed(() => ['webtoonComments', webtoonId.value, episodeId.value]),
        queryFn: async () => {
            if (!webtoonId.value || !episodeId.value) {
                return Promise.reject(new Error('webtoonId and episodeId are required'))
            }
            const result = await getPagingWebtoonEpisodeComments({
                pageNo: pageNo.value,
                pageSize: pageSize.value,
                webtoonId: webtoonId.value,
                episodeId: episodeId.value
            })
            return extractValidData(result)
        },
        enabled: true,
        staleTime: 1000 * 10,
        retry: 0,
    })

    return {
        updateWebtoonId,
        updateEpisodeId,
        ...query,
    }
}