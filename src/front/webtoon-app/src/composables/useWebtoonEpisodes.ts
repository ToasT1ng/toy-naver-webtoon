import {computed, ref} from 'vue'
import {useQuery} from '@tanstack/vue-query'
import {getPagingWebtoonEpisodes, getWebtoonEpisode, getWebtoonEpisodeNavigation} from "@/api/webtoonEpisode";
import {SortDirection} from "@/types/common";
import {extractValidData} from "@/utils/extractValidData";

//TODO webtoonId를 store에서 가져오도록 변경?

export const useWebtoonEpisode = () => {
    const webtoonId = ref<number | undefined>(undefined)
    const episodeId = ref<number | undefined>(undefined)
    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }
    function updateEpisodeId(newEpisodeId: number){
        episodeId.value = newEpisodeId
    }
    const query = useQuery({
        queryKey: computed(() => ['webtoonEpisode', webtoonId.value, episodeId.value]),
        queryFn: async () => {
            if (!webtoonId.value || !episodeId.value) {
                return Promise.reject(new Error('webtoonId and episodeId are required'))
            }
            const result = await getWebtoonEpisode({
                webtoonId: webtoonId.value,
                episodeId: episodeId.value
            })
            //TODO 오류 발생 시 Vue에서 Handling 하도록 수정
            return extractValidData(result)
        },
        enabled: () => !!webtoonId.value || !!episodeId.value,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        updateEpisodeId,
        ...query,
    }
}

export const useWebtoonEpisodes = () => {
    const pageNo = ref(1)
    const pageSize = ref(10)
    const webtoonId = ref<number | undefined>(undefined)
    const sortDirection = ref<SortDirection>('DESC')

    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    function updatePageNo(newPageNo: number){
        pageNo.value = newPageNo
    }

    function updateSortDirection(newSortDirection: SortDirection){
        sortDirection.value = newSortDirection
    }

    const query = useQuery({
        queryKey: computed(() => ['webtoonEpisodes', webtoonId.value, pageNo.value, sortDirection.value]),
        queryFn: async () => {
            if (!webtoonId.value) {
                return Promise.reject(new Error('webtoonId is required'))
            }
            const result = await getPagingWebtoonEpisodes({
                pageNo: pageNo.value,
                pageSize: pageSize.value,
                webtoonId: webtoonId.value,
                sortDirection: sortDirection.value,
            })
            return extractValidData(result)
        },
        enabled: () => !!webtoonId.value,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        updatePageNo,
        updateSortDirection,
        ...query,
    }
}

export const useWebtoonEpisodeNavigation = () => {
    const webtoonId = ref<number | undefined>(undefined)
    const episodeId = ref<number | undefined>(undefined)
    function updateWebtoonId(newWebtoonId: number) {
        webtoonId.value = newWebtoonId
    }

    function updateEpisodeId(newEpisodeId: number){
        episodeId.value = newEpisodeId
    }
    const query = useQuery({
        queryKey: computed(() => ['webtoonEpisodeNavigation', webtoonId.value, episodeId.value]),
        queryFn: async () => {
            if (!webtoonId.value || !episodeId.value) {
                return Promise.reject(new Error('webtoonId and episodeId are required'))
            }
            const result = await getWebtoonEpisodeNavigation({
                webtoonId: webtoonId.value,
                episodeId: episodeId.value
            })
            return extractValidData(result)
        },
        enabled: () => !!webtoonId.value || !!episodeId.value,
        staleTime: 1000 * 10,
    })

    return {
        updateWebtoonId,
        updateEpisodeId,
        ...query,
    }
}